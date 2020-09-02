package com.vividswan.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vividswan.blog.dto.ResponseDto;
import com.vividswan.blog.model.RoleType;
import com.vividswan.blog.model.User;
import com.vividswan.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PutMapping("/uers/update")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.update(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 직접 세션값을 변경해줘야 함
		// 세션등록
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
//	@PostMapping("/api/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession httpSession;){ // username, password
//		User principal = userService.login(user);
//		if(principal != null) httpSession.setAttribute("principal", principal);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}
}
