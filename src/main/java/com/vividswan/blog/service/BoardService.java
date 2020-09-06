package com.vividswan.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vividswan.blog.dto.ReplySaveDto;
import com.vividswan.blog.model.Board;
import com.vividswan.blog.model.Reply;
import com.vividswan.blog.model.User;
import com.vividswan.blog.repository.BoardRepository;
import com.vividswan.blog.repository.ReplyRepository;
import com.vividswan.blog.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void saveBoard(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> getBoards(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board getBoard(int id) {
		return boardRepository.findById(id)
					.orElseThrow(()->{
						return new IllegalArgumentException("해당 하는 번호의 게시물이 없습니다.");
					});
	}
	
	@Transactional
	public void deleteBoard(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void updateBoard(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("해당 하는 번호의 게시물이 없습니다.");
				});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}

	@Transactional
	public void saveReply(ReplySaveDto replySaveDto) {
		User user = userRepository.findById(replySaveDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 작성 실패 : 일치하는 유저가 없습니다.");
		});
		Board board = boardRepository.findById(replySaveDto.getBoardId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 작성 실패 : 일치하는 게시판이 없습니다.");
		});
		String content=replySaveDto.getContent();
		Reply reply = new Reply(user,board,content);
		replyRepository.save(reply);
	}
	
//	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
