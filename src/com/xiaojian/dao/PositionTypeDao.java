package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaojian.model.PositionType;

public class PositionTypeDao {

	public int addPosType(Connection con,PositionType positionType) throws Exception{
		String sql="insert into t_positionType values(null,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, positionType.getName());
		return pstmt.executeUpdate();
	}
	
	public ResultSet posTypeList(Connection con) throws Exception{
		String sql = "select * from t_PositionType";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	public ResultSet posTypeById(Connection con,PositionType positionType) throws Exception{
		String sql = "select * from t_PositionType where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, positionType.getId());
		return pstmt.executeQuery();
	}
}
