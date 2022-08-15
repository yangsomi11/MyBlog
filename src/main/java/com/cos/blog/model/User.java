package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴 
//@DynamicInsert  // insert 시에 null인 필드 제외
//ORM ->  Java object - > 테이블로 맵핑해주는 기술 
@Entity // user 클레스가 MySQL에 테이블이 생성
public class User {
	
	@Id  //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스,auto_increment
	
	@Column(unique = true ,nullable = false, length = 100)
	private String username; //ID
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//DB는 RoleType이 없다
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role;  //Enum을 쓰는게 좋다. ADMIN, USER, manager ->도메인 (범위) 정해진다.
	
	private String oauth; //kakao, google
	
	@CreationTimestamp   //시간 자동 입력
	private Timestamp cratedate;
	
}
