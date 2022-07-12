package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { //username, password, email
		System.out.println("UserApiController:save호출");
		//실제 DB에 insert하고 아래에서 return
		user.setRole(RoleType.USER);
//		int result = userservice.회원가입(user);
		userservice.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);//result
	}
	
	//스피링 시큐리티 이용해서 로그인 
	@PostMapping("api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		System.out.println("UserApiController:login호출");
		
		User principal = userservice.로그인(user);
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);//result

	}
	
}
