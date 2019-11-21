package com.yinhai.service;

import java.util.ArrayList;

import com.yinhai.bean.User;

public interface UserService {
	
	boolean register(User user);
	
	ArrayList<User> login(User user);

}
