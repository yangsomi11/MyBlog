package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"","/"})  //@AuthenticationPrincipal PrincipalDetail principal
	public String index() { //컨트롤러에서 세션을 어떻게 찾는가?
//		System.out.println("로그인사용자 아이다 : "+ principal.getUsername());
		return "index";
	}
	
	//USER권한이 필요
	@GetMapping({"/board/saveForm"})  //@AuthenticationPrincipal PrincipalDetail principal
	public String saveFrom() { //컨트롤러에서 세션을 어떻게 찾는가?
		return "board/saveForm";
	}
}
