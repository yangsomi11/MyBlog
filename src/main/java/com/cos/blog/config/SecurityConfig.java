package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//빈등록 : 스프링 컨테이너에서 객체 관리 가능

@Configuration //빈등록(ioc관리)
@EnableWebSecurity //시큐리티 필터가 등록된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소 접근 권한 인증 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean //IOC 
	public BCryptPasswordEncoder encodePWD() {
		return new  BCryptPasswordEncoder();
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
				.loginPage("/auth/loginForm");
	}		
	
}
