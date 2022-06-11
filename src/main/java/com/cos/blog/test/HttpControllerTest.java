package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//사용자가 요청 -> 응답(Html 파일)

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest(){
//		Member m = new Member( "smyang","8945","email");
		Member m = Member.builder().username("sar").password("1234").email("sm.yang").build();
		System.out.println(TAG + "getter : "+ m.getUsername());
		m.setUsername("coosss");
		System.out.println(TAG + "getter : "+ m.getUsername());
		
		return "lombok Test 완료";
	}
	// 인터넷 브라우저 요청은 무조건 get요청 
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {  //?id=1&username=sar&password= 1234&email= sal@naver.com

		return "get 요청 :" +m.getId()+"," +m.getUsername()+"," +m.getPassword()+"," +m.getEmail();
	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")// text/plain  , app/json
	public String postTest(@RequestBody Member m) {
		
		return "post 요청 :" +m.getId()+"," +m.getUsername()+"," +m.getPassword()+"," +m.getEmail();
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		
		return "put 요청 :" +m.getId()+"," +m.getUsername()+"," +m.getPassword()+"," +m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		
		return "delete 요청";
	}
	
}
