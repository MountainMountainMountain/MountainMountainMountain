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
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<?> getUserList() {
		List<User> userList = userService.getUserList();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	// 사용자 등록
	@PostMapping("/user/join")
	public ResponseEntity<?> signup(@RequestBody User user) {
		userService.signup(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	// 사용자 수정
	@PutMapping("/user/{userSerial}")
	public ResponseEntity<?> putMethodName(@PathVariable("userSerial") int userSerial, @RequestBody User user) {
		user.setSerial(userSerial);
		userService.modifyUser(user);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 사용자 아이디 검색
	@GetMapping("/user/search/id/{id}")
	public ResponseEntity<?> searchById(@PathVariable("id") String id) {
		User user = userService.searchById(id);
		if (user == null) {
			// 검색했는데 없다면
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	// 사용자 이름 검색
	@GetMapping("/user/search/name/{name}/{serial}")
	public ResponseEntity<?> searchByName(@PathVariable("name") String name, @PathVariable("serial") int serial) {
		List<User> userList = userService.searchByName(name, serial);
		if (userList == null) {
			// 검색했는데 없다면
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		}
	}

	// 사용자 삭제
	@DeleteMapping("/user/{serial}")
	public ResponseEntity<?> deleteUser(@PathVariable("serial") int serial) {
		userService.deleteUser(serial);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 사용자 아이디 확인
	@GetMapping("/user/join/check/id/{id}")
	public ResponseEntity<?> checkId(@PathVariable("id") String id) {
		// 아이디가 존재합니다.
		if (userService.checkId(id)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			// 아이디가 존재하지 않다면,
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 사용자 이메일 중복 체크
	@GetMapping("/user/join/check/email/{email}")
	public ResponseEntity<?> checkEmail(@PathVariable("email") String email) {
		// 이메일이 존재 -> 회원이 존재한다.
		if (userService.checkEmail(email)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			// 이메일 존재하지 않으면,
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 로그인
	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody User user) {

		HttpStatus status = null;
		Map<String, Object> result = new HashMap<>();
		User loginUser = userService.login(user.getId(), user.getPassword());

		if (loginUser.getSerial() != 0) {

			result.put("message", SUCCESS);
			result.put("access-token", jwtUtil.createToken(loginUser));

			status = HttpStatus.ACCEPTED;
		} else {
			result.put("message", FAIL);
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<>(result, status);
	}

	// 포인트 업데이트
	@GetMapping("/user/point/{userSerial}/{mountainSerial}")
	public ResponseEntity<Void> updateUserPoint(@PathVariable("userSerial") int userSerial,
			@PathVariable("mountainSerial") int mountainSerial) {
		userService.updateUserPoint(userSerial, mountainSerial);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 팔로우 하기
	@GetMapping("/follow/{fromFollow}/{toFollow}")
	public ResponseEntity<?> createFollow(@PathVariable("fromFollow") int fromFollow,
			@PathVariable("toFollow") int toFollow) {
		System.out.println(fromFollow);
		System.out.println(toFollow);

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
