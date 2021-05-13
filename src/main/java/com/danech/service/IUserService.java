package com.danech.service;

import com.danech.model.User;

public interface IUserService {
	User findByUserName(String userName);
	User insertUser(User user);
}