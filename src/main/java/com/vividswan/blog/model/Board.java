package com.vividswan.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 (<html> 태그가 섞여서 디자인 됨.)
	
	private int count; // 조회수
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy-> 연관관계의 주인이 아니므로, DB에 FK키를 만들지 말라는 의미, EAGER 전략 -> 무조건 가져오기
	private List<Reply> reply;
	
	@ManyToOne // Many = Board, One = User
	@JoinColumn(name = "userId")
	private User user; // 원래 DB는 오브젝트를 저장할 수 없어서 FK를 사용, 자바는 오브젝트를 저장할 수 있다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
