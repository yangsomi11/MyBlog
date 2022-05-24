package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //특정 어노테이션이 붙어있는 클레스 파일을  new ioc -> 스프링 컨트롤러 관리
public class BlogControllerTest {
	
	//http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot </h1>"; 
	}
}
