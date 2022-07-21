package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//인증이 안된 사용자들이 출입가능한 경록 /auth/**허용
//그냥 주소가  / 이면 index.jsp 허용
//static 이하 /js/** ,/css/**, /image/**
@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
}


