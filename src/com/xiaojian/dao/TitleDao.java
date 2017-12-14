package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xiaojian.model.Title;

public class TitleDao {

	public ResultSet titleList(Connection con) throws SQLException {
		String sql = "select * from t_title";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	public ResultSet titleName(Connection con,Title title) throws SQLException {
		String sql = "select * from t_title where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, title.getId());
		return pstmt.executeQuery();
	}
	
}
