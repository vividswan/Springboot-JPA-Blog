package com.vividswan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vividswan.blog.model.User;

// DAO
// 자동으로 bean에 등록됨(스프링 IoC)
// @Repository 생략가능!!
public interface UserRepository extends JpaRepository<User, Integer>{
	

}
