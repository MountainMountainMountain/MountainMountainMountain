package com.mountain.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mountain.model.dao.FollowDao;
import com.mountain.model.dao.UserDao;
import com.mountain.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final FollowDao followDao;

	public UserServiceImpl(UserDao userDao, FollowDao followDao) {
		this.userDao = userDao;
		this.followDao = followDao;
	}

	@Override
	public List<User> getUserList() {
		return userDao.userList();
	}

	@Override
	public void signup(User user) {
		userDao.createUser(user);
	}

	@Override
	public void modifyUser(User user) {
		userDao.modifyUser(user);
	}

	@Override
	public User searchById(String id) {
		return userDao.selectUserById(id);
	}

	@Override
	public List<User> searchByName(String name, int mySerial) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userName", name);
		paramMap.put("mySerial", mySerial);

		return userDao.selectUserByName(paramMap);
	}

	@Override
	public void deleteUser(int serial) {
		userDao.deleteUser(serial);
	}

	@Override
	public boolean checkId(String id) {
		// 존재하면 1
		return userDao.isIdExist(id) == 1;
	}

	@Override
	public boolean checkEmail(String email) {
		// 존재하면 1
		return userDao.isEmailExist(email) == 1;
	}

	@Override
	public User login(String id, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("id", id);
		info.put("password", password);
		return userDao.login(info);
	}
	
	@Override
	public void updateUserPoint(int serial, int mountainPoint) {
		userDao.modifyUserPoint(serial, mountainPoint);
	}

	@Override
	public boolean checkAlreadyFollowing(int fromFollow, int toFollow) {
		System.out.println(followDao.checkAlreadyFollowing(fromFollow, toFollow));
		return followDao.checkAlreadyFollowing(fromFollow, toFollow) == 1;
	}

	@Override
	public void createFollow(int fromFollow, int toFollow) {
		followDao.createFollow(fromFollow, toFollow);
	}

	@Override
	public void deleteFollow(int fromFollow, int toFollow) {
		followDao.deleteFollow(fromFollow, toFollow);
	}

	@Override
	public List<User> followingList(int toFollow) {
		return followDao.followingList(toFollow);
	}

	@Override
	public List<User> followerList(int fromFollow) {
		return followDao.followerList(fromFollow);
	}

}
