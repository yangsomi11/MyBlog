package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//자동으로 bean등록 가능
//@Repositoty 생략 가능 
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//select * from user where username = 1?
	Optional<User> findByUsername(String username);
	
}




// JPA Naming 전략
// select * from user where username = ? and password = ? 
// User findByUsernameAndPassword(String username, String password);

// @Query(value="select * from user where username = ?1 and password = ?2 ", nativeQuery = true)
// User login(String username,String password);