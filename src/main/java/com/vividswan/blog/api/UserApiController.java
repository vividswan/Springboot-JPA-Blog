package com.vividswan.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	UserService userService;
	
	@PutMapping("/uers/update")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.update(user);
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
