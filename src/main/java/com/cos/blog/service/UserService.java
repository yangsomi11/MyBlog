package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 -> Ioc 해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	
	@Transactional  //트렌젝션을 묶어서 성공, 실패 commit; rollback;
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword(); // 1234원문
		String encPassword = encoder.encode(rawPassword); //해쉬
		
		user.setPassword(encPassword);
		//실제 DB에 insert하고 아래에서 return
		user.setRole(RoleType.USER);
		userRepository.save(user);

	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 jpa 영속성 컨텍스트에 User 오브젝트를 영속화시킨후 영속화된 User 오브젝트를 수정
		// select를해서 User 오프젝트를 DB로 부터 가져온는 이유는 영속화하기 위함
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문 
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		//Validate체크 -> oauth값이 없으면 수정 가능
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}

		//회원 수정 합수 종료시 = 서비스 종료 ==> 트렌젝션 종료 commit 자동 
		//영속화된 persistance객체의 변화감지 시(더티체킹)되어 update 문 날림
	}

	
}
