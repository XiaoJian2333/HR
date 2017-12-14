package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaojian.model.User;
import com.xiaojian.util.DbUtil;

public class UserDao {
	DbUtil dbUtil = new DbUtil();
	public User login(User user) throws Exception {
		String sql = "select * from t_user where userName=? and password=?";
		Connection con = dbUtil.getCon();
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, user.getUserName());
		preparedStatement.setString(2, user.getPassword());
		ResultSet rs = preparedStatement.executeQuery();
		User rsUser = null;
		if(rs.next()){
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			String roleId = rs.getString("roleId");
			 rsUser = new User(userName, password);
			 rsUser.setroleId(Integer.parseInt(roleId));
		}
		return rsUser;
	}
}
