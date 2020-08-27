package com.vividswan.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		user.setRole(RoleType.USER);
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK,result);
	}
}
