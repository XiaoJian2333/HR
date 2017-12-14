package com.xiaojian.service;

import com.xiaojian.dao.UserDao;
import com.xiaojian.model.User;

public class UserService {

	UserDao dao=new UserDao();
	public User login(User user) throws Exception{
		return dao.login(user);
	}
}
