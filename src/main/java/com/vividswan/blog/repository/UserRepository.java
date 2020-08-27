package com.vividswan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vividswan.blog.model.User;

// DAO
// 자동으로 bean에 등록됨(스프링 IoC)
// @Repository 생략가능!!
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA Naming 쿼리
	// SELECT * FROM user WHERE username = ?1 AND password = ?2;
	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2;", nativeQuery = true )
//	User login(String username, String password);
}