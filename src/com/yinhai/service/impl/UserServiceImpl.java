package com.yinhai.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinhai.bean.User;
import com.yinhai.mapper.UserMapper;
import com.yinhai.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper UserMapper;
	
	public UserMapper getUserMapper() {
		return UserMapper;
	}
	public void setUserMapper(UserMapper UserMapper) {
		this.UserMapper = UserMapper;
	}

	@Override
	public boolean register(User user) {
		int i=UserMapper.insertUser(user);
		return i==1?true:false;
	}
	@Override
	public ArrayList<User> login(User user) {
		return UserMapper.findUser(user);
		
	}

}
