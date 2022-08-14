package com.cos.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.auth.PrincipalDetail;
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
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code){ //데이터를 리턴해주는 컨트롤러 함수
		
		//POST방식으로 key=value 데이터를 요청(카카오로 전달)
		//OkHttp
		//Retrofit2
		//RestTemplate
		
		RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오프젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오프젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id",	"27c8219b397b3c14c7ae75f393ddef25");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 HttpEntity 오브젝트에 담기 
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params ,headers);
		
		//Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		
		return "카카오 인증 완료 : 토큰 요청에 대한 응답 : "+response;
	}
}


