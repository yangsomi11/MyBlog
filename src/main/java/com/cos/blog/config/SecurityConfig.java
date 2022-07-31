package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;
//빈등록 : 스프링 컨테이너에서 객체 관리 가능

@Configuration //빈등록(ioc관리)
@EnableWebSecurity //시큐리티 필터가 등록된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소 접근 권한 인증 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //IOC 
	public BCryptPasswordEncoder encodePWD() {
		return new  BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해주는데 password를 가로챌때
	//해당 password가 뭘로 해쉬가 되서 회원가입이 되었는지 알아야 같은 해쉬로 암호화
	//같은 해쉬로 암호화 후 DB의 해쉬랑 비교 가능
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf토큰 비활성화 (테스트시 걸어둠)
			.authorizeRequests()
				.antMatchers("/","/auth/**", "/js/**", "/css/**","/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") //인증이 필요하지 않은 페이지
				.loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소로 로그인 가로채서 대신 로그인  
				.defaultSuccessUrl("/");
	}		
	
}
