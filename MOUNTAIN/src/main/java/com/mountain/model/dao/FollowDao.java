package com.mountain.model.dao;

import java.util.List;

import com.mountain.model.dto.User;

public interface FollowDao {

	// 팔로우 리스트
	List<User> followList(String userId);

	// 팔로우 하기
	void insertFollow(int fromFollow, int toFollow);

	// 팔로우 취소
	void deleteFollow(int fromFollow, int toFollow);
	
	// 팔로잉 리스트(나의 기준 내가 팔로잉 하고 있는 리스트)
	List<User> followingList(int toFollow);
	
	// 팔로워 리스트(나의 기준 나를 팔로잉 하는 리스트)
	List<User> followerList(int fromFollow);
	
}
