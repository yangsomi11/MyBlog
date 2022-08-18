package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	@Modifying
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) values (?1,?2,?3,now())",nativeQuery = true)
	public int mSave(int userID, int boardId, String content); //업데이트된 행의 개수 리턴
	
	
}
