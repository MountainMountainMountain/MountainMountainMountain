package com.mountain.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.model.dto.User;
import com.mountain.model.service.UserService;

@RestController
@RequestMapping("/api-user")
public class UserRESTController {
	private final UserService userService;

	@Autowired
	public UserRESTController(UserService userService) {
		this.userService = userService;
	}

	// 사용자 전체 목록
	@GetMapping("/user")
	public ResponseEntity<List<User>> getUserList() {
		List<User> userList = userService.getUserList();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	// 사용자 등록
	@PostMapping("/user/join")
	public ResponseEntity<Void> signup(@RequestBody User user) {
		System.out.println(user);
		userService.signup(user);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	// 사용자 아이디 검색
	@GetMapping("/user/search/id/{id}")
	public ResponseEntity<List<User>> searchById(@PathVariable("id") String id) {
		List<User> userList = userService.searchById(id);
		if (userList == null) {
			// 검색했는데 없다면
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		}
	}

	// 사용자 이름 검색
	@GetMapping("/user/search/name/{name}")
	public ResponseEntity<List<User>> searchByName(@PathVariable("name") String name) {
		List<User> userList = userService.searchByName(name);
		if (userList == null) {
			// 검색했는데 없다면
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		}
	}

	// 사용자 삭제
	@DeleteMapping("/user/{serial}")
	public ResponseEntity<Void> deleteUser(@PathVariable("serial") int serial) {
		userService.deleteUser(serial);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 사용자 아이디 확인
	@GetMapping("/user/join/check/id/{id}")
	public ResponseEntity<?> checkId(@PathVariable("id") String id) {
		// 아이디가 존재합니다.
		if (userService.checkId(id)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			// 아이디가 존재하지 않다면,
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	// 사용자 이메일 중복 체크
	@GetMapping("/user/join/check/email/{email}")
	public ResponseEntity<?> checkEmail(@PathVariable("email") String email) {
		// 이메일이 존재 -> 회원이 존재한다.
		if (userService.checkEmail(email)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			// 이메일 존재하지 않으면,
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	// 로그인
	// 수정해야 해 jwt 해야 해
	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
