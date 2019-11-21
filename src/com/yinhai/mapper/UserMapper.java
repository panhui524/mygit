package com.yinhai.mapper;

import java.util.ArrayList;

import com.yinhai.bean.User;

public interface UserMapper {

	int insertUser(User user);

	ArrayList<User> findUser(User user);
}