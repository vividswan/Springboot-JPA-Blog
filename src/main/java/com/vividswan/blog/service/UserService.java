package com.vividswan.blog.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividswan.blog.model.User;
import com.vividswan.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public int saveUser(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			System.out.println("회원가입에 실패했습니다. "+e);
		}
		return -1;
	}
}
