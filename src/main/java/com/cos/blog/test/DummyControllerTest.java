package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
//	http://localhost:8080/blog/dummy/user
	//한페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> paginUser = userRepository.findAll(pageable);
		
		List<User> users = paginUser.getContent();
		return users;
	}
	 
//	http://localhost:8080/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		
		return userRepository.findAll();
	}
	
	
	//{id}
	//http://localhost:8080/blog/dummy/user/3 (요청)
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//user/4 찾으면 데이터베이스에서 못찾는 경우 user가 null
		// retrurn 이 null ==? optional로 User객체로 감싸서 가져와야함 null여부 판단
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 user는 없습니다. id : "+id);
			}
		});
		
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 (웹브라우저가 이해할 수 있는 데이터 -> json )
		//스프링부트 = MassageConverter 응답시 자동 작동
		//자바 오브텍트 리턴 시 MassageConverter가 Jackson 라이브러리 호출
		//user 오브젝트를 json으로 변환시켜서 브라우저에게 던져줌. 
		return user;
		
	}
	
	
	//http://localhost:8080/blog/dummy/join (요청)
	//http의 body의 username password  email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : "+ user.getId());
		System.out.println("username : "+ user.getUsername());
		System.out.println("password : "+ user.getPassword());
		System.out.println("email : "+  user.getEmail());
//		System.out.println("role : "+ user.getRole());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
