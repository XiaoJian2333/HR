package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaojian.model.Institution;
import com.xiaojian.model.PositionName;
import com.xiaojian.model.PositionType;

public class PositionNameDao {

	public int addPosition(Connection con, PositionName positionName) throws Exception{
		String sql="insert into t_positionName values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, positionName.getName());
		pstmt.setInt(2, positionName.getParentId());
		return pstmt.executeUpdate();
	}
	public ResultSet searchByPid(Connection con,PositionName positionName) throws Exception{
		String sql = "select * from t_positionName where parentid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, positionName.getParentId());
		return pstmt.executeQuery();
	}
	public ResultSet searchById(Connection con,PositionName positionName) throws Exception{
		String sql = "select * from t_positionName where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, positionName.getId());
		return pstmt.executeQuery();
	}
	public ResultSet positionNameList(Connection con) throws Exception{
		String sql = "select * from t_positionName";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
