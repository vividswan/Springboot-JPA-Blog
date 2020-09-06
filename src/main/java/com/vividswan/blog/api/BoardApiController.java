package com.vividswan.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vividswan.blog.config.auth.PrincipalDetail;
import com.vividswan.blog.dto.ReplySaveDto;
import com.vividswan.blog.dto.ResponseDto;
import com.vividswan.blog.model.Board;
import com.vividswan.blog.model.Reply;
import com.vividswan.blog.model.User;
import com.vividswan.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	
	@PostMapping("/api/saveProc")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) { // title, content
		boardService.saveBoard(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/delete/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id){
		boardService.deleteBoard(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/putProc/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.updateBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	// 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	@PostMapping("/api/board/{id}/reply")
	public ResponseDto<Integer> SaveReply(@RequestBody ReplySaveDto replySaveDto){
		boardService.saveReply(replySaveDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
