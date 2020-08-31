package com.vividswan.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vividswan.blog.config.auth.PrincipalDetail;
import com.vividswan.blog.dto.ResponseDto;
import com.vividswan.blog.model.Board;
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
	
}
