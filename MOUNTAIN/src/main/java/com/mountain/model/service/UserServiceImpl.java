package com.mountain.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountain.model.dao.UserDao;
import com.mountain.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUserList() {
    	System.out.println(1);
        return userDao.userList();
    }

    @Override
    public void signup(User user) {
        userDao.createUser(user);
    }

    @Override
    public List<User> searchById(String id) {
        return userDao.selectUserById(id);
    }

    @Override
    public List<User> searchByName(String name) {
        return userDao.selectUserByName(name);
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



}
