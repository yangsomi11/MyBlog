package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료 후 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 세선 저장소에 저장
@Data
public class PrincipalDetail implements UserDetails{

	private User user; //컴포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴 (true 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠겨있지 않는지 리턴 (true 만료안됨)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 만료되지 않았는지 리턴 (true 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 활성화(사용가능)인지 리턴 (true 만료안됨)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 권한 목록을 리턴 (권한이 여러개 있을 수 있어서 루프를 돌아야하함(test 한개 권한))
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors =  new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}
}
