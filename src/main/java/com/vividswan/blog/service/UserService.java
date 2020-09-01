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
	
	@Transactional
	public void update(User requestUser) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화 된 User 오브젝트를 수정
		// select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속하를 하기 위함
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
		User user = userRepository.findById(requestUser.getId())
				.orElseThrow(()-> {
					return new IllegalArgumentException("사용자를 찾을 수 없습니다.");
				});
		String rawPassword = requestUser.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setEmail(requestUser.getEmail());
		// 회원수정 함수 종료 시 -> 서비스 종료 -> 트랜잭션 종료 -> commit이 자동으로 됨
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
	}
	
//	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
