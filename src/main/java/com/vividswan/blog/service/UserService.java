package com.vividswan.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vividswan.blog.model.RoleType;
import com.vividswan.blog.model.User;
import com.vividswan.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void saveUser(User user) {
		user.setRole(RoleType.USER);
		String rawPassword = user.getPassword();
		String encPassword= encoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);
	}
	
//	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
