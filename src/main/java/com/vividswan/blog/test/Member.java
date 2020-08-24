package com.vividswan.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Getter
// @Setter
// @RequiredArgsConstructor

@NoArgsConstructor
@Data
public class Member {
	private int id;
	private String email;
	private String username;
	private String password;
	
	@Builder
	public Member(int id, String email, String username, String password) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	
	
}
