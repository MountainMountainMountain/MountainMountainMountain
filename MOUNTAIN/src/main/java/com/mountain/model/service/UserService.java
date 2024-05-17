package com.mountain.model.service;

import java.util.List;

import com.mountain.model.dto.User;

public interface UserService {
    // 사용자 전체 목록 조회
    public List<User> getUserList();
    
    // 사용자 등록
    public void signup(User user);
    
    // 사용자 아이디 검색
    public List<User> searchById(String id);
    
    // 사용자 이름 검색
    public List<User> searchByName(String name);
    
    // 사용자 삭제
    public void deleteUser(int serial);
    
    // 사용자 아이디?
    public boolean checkId(String id);
    
    // 사용자 이메일 중복 체크
    public boolean checkEmail(String email);
    
    // 로그인
    public User login(String id, String password);
}
