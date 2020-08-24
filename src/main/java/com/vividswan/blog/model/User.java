package com.vividswan.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴!!
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL 테이블에 생성된다.
// @DynamicInsert  // insert 시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 제작된 DB의 넘버링을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬 (비밀번호 암호화, 넉넉하게 크기 잡아두기)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'") // 양 옆으로 작은 따옴표를 줘서 문자라는 것을 알려줘야함
	
	@Enumerated(EnumType.STRING) // 해당 enum이 String이라고 알려줌
	private RoleType role; // Enum을 쓰는게 좋다. // admin, user, manager -> 도메인(프로그래밍에서 도메인은 범위를 의미)
	
	@CreationTimestamp // 값을 비워두고 insert해도 시간이 자동 입력
	private Timestamp createDate;
	
}
