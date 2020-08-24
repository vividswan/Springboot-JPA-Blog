package com.vividswan.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일) -> @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String Tag = "httpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member().builder().username("vividswan").email("vividswan@naver.com").password("1234").build();
		System.out.println(Tag + " getId : "+m.getId());
		m.setId(5000);
		System.out.println(Tag + " getId : "+m.getId());
		return "lombokTest 완료!";
	}
	
	// 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { // id=1&username=vividswan&password=1234&email=vividswan@naver.com messageConverter (스프링부트)
		return "get request : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { // messageConverter (스프링부트)
		return "post request : " +m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put request : " +m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete request";
	}
}
