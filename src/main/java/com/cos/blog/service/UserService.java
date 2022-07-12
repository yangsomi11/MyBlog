package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 -> Ioc 해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional  //트렌젝션을 묶어서 성공, 실패 commit; rollback;
	public void 회원가입(User user) {
		
		userRepository.save(user);

//		try {
//			userRepository.save(user);
//			return 1;
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("UserServic 회원가입() : "+e.getMessage());
//		
//		}
//		return -1;
	}

	
	@Transactional(readOnly = true)// select할때 트랜젝션 시작, 해당 서비스 종료시 트렌젝션 종료 (정합성 유지)
	public User 로그인(User user) {
		
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

	}
	
}
