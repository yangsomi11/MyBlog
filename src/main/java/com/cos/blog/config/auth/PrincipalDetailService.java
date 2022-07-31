package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	//스프링 로그인 요청 가로챌때 username , password 변수 2개 가로채는데 
	//passwrord 부분은 알아서 처리
	//username이 DB에 있는지만 확인해주면됨.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
			.orElseThrow(()->{
				return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :"+username);
			});
		return new PrincipalDetail(principal); //시큐리티 세션에 유저정보 저장 id: user, password : 콘솔창
	}
	
}
