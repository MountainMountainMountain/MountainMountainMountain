package com.mountain.model.dao;

import java.util.List;
import java.util.Map;

import com.mountain.model.dto.User;

public interface UserDao {

	// 사용자 전체 목록
	List<User> userList();

	// 사용자 등록
	void createUser(User user);

	// 사용자 아이디 검색
	User selectUserById(String userId);

	// 사용자 이름 검색
	List<User> selectUserByName(String userName);

	// 사용자 수정
	void modifyUser(User user);

	// 사용자 삭제
	void deleteUser(int userSerial);

	// 사용자 아이디
	int isIdExist(String userId);

	// 이메일 중복체크
	int isEmailExist(String userEmail);

	// 로그인
	User login(Map<String, String> info);

}
