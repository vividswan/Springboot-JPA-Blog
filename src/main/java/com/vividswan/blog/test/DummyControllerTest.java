package com.vividswan.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vividswan.blog.model.RoleType;
import com.vividswan.blog.model.User;
import com.vividswan.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> page = userRepository.findAll(pageable) ;
		List<User> users = page.getContent();
		return users;
	}
	
	// save 함수 -> id를 전달하지 않으면 insert,
	// id를 전달하면 해당 id에 대한 데이터가 있으면 update,
	// id를 전달하면 해당 id에 대한 데이터가 없으면 insert!!
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch (Exception e) {
			return "해당 하는 id가 없습니다. id : "+id;
		}
		
		return id+"번 id 회원 삭제에 성공했습니다.";
	}
	
	
	@Transactional // 함수 종료 시에 자동 commit이 됌.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		// @RequestBody => json 데이터를 요청 => Java Object(MessageConverter의 Jackson이 변환해서 받아줌)
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 ID의 user가 존재하지 않습니다. id : "+id);
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// userRepository.save(user); 
		// @Transactional 추가!! -> 더티 체킹(save를 안해도 update가 된다.)
		return user;
		
	}
	
	// {id} -> 파라미터로 전달
	// http://localhost:8000/blog/dummy/user/{id}
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// findById(id)의 타입은 Optional ->null을 리턴하지않기 위해 finById(id) 뒤에 메서드 사용
		// finById(id).get() -> 무조건 데이터가 있는 것이 보장될 때 사용 (바로 return) , null 이 와버리면 null이 return
		// Optinal로 User 객체를 감싸서 가져오므로 null인지 아닌지 판단해서 return
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 ID의 user가 존재하지 않습니다. id : "+id);
			}
		});
		
		// 람다식
		/*		User user = userRepository.findById(id).orElseThrow(() -> {
		 		return new IllegalArgumentException("해당 ID의 user가 존재하지 않습니다. id : "+id);
		});*/
		
		
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConver가 응답시에 자동 작동
		// 만약에 java object를 return 하게 되면 MessageConver가 Jackson 라이브러리를 호출
		// user 오브젝트를 json으로 변환해서 브라우저에게 보내줌
		return user;
	}
	
	/*
	// http://localhost:8000/blog/dummy/join (post 요청)
	// http의 body에 username, password, email 데이터를 가지고 요청!
	@PostMapping("/dummy/join")
	public String join(String username, String email, String password) { // key=value 규칙!!! 스프링의 함수의 파라미터로 파싱해서 연결해줌
		System.out.println("userName : "+userName);
		System.out.println("email : "+email);
		System.out.println("password : "+password);
		return "회원가입이 완료되었습니다.";
	}
	*/
	@PostMapping("/dummy/join")
	public String join(User user) { // // object로 받아서 처리할 수 있음
		System.out.println("id : "+user.getId());
		System.out.println("username: "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("emal: "+user.getUsername());
		System.out.println("role: "+user.getRole());
		System.out.println("createDate: "+user.getCreateDate());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
}
