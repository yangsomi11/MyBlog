package com.cos.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;


//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 -> Ioc 해줌
@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepositoy;
	
	@Transactional 
	public void 글쓰기(Board board, User user) { //title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);

	}
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
			.orElseThrow(()->{
				return new IllegalArgumentException("글쓰기 상세보기 실패: 아이디를 찾을 수 없습니다.");
			});
	}
	
	@Transactional
	public void 삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
				}); //영속화 시키는것 
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수로 종료시에 Service가 종료될때 트렌젝션 종료 ->이떄 더티체킹 - 자동 업데이트 DB flush
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		
//		User user = userRepository.findById(replySaveRequestDto.getUserId())
//				.orElseThrow(()->{
//					return new IllegalArgumentException("댓글쓰기 실패: 유저 아이디를 찾을 수 없습니다.");
//		}); //영속화 시키는것 
//		
//		Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
//			.orElseThrow(()->{
//				return new IllegalArgumentException("댓글쓰기 실패: 게시글 아이디를 찾을 수 없습니다.");
//		}); //영속화 시키는것
//		
//		Reply reply = Reply.builder()
//				.user(user)
//				.board(board)
//				.content(replySaveRequestDto.getContent())
//				.build();
		
		int result = replyRepositoy.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println("BoardService : "+result);
	}
}
