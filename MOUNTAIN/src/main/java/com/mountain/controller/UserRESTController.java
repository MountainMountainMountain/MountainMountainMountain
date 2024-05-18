package com.mountain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.mountain.util.JwtUtil;

@RestController
@RequestMapping("/api-user")
public class UserRESTController {
	private final UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

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
		userService.signup(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
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

		HttpStatus status = null;
		Map<String, Object> result = new HashMap<>();
		System.out.println(user);
		// 서비스 -> 다오 -> DB
		// 엄청난 검증을 끝내고 온거다.
		if (user.getId() != null) {
			// 토큰 만들어서 줘야되는데?
			result.put("message", SUCCESS);
			result.put("access-token", jwtUtil.createToken(user.getId()));
			// id도 같이 넘겨주면 번거로운 작업을 할 필요는 없어
			status = HttpStatus.ACCEPTED;
		} else {
			result.put("message", FAIL);
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
//		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 팔로우 하기
	@GetMapping("/follow/{fromFollow}/{toFollow}")
	public ResponseEntity<?> createFollow(@PathVariable("fromFollow") int fromFollow,
			@PathVariable("toFollow") int toFollow) {

		if (userService.checkAlreadyFollowing(fromFollow, toFollow)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userService.createFollow(fromFollow, toFollow);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 팔로우 취소
	@DeleteMapping("/follow/{fromFollow}/{toFollow}")
	public ResponseEntity<?> deleteFollow(@PathVariable("fromFollow") int fromFollow,
			@PathVariable("toFollow") int toFollow) {
		if (!userService.checkAlreadyFollowing(fromFollow, toFollow)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userService.deleteFollow(fromFollow, toFollow);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 팔로잉 리스트
	@GetMapping("/follow/followingList/{toFollow}")
	public ResponseEntity<?> followingList(@PathVariable("toFollow") int toFollow) {
		List<User> list = userService.followingList(toFollow);

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// 팔로워 리스트
	@GetMapping("/follow/followerList/{fromFollow}")
	public ResponseEntity<?> followerList(@PathVariable("fromFollow") int fromFollow) {
		List<User> list = userService.followerList(fromFollow);

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
}
