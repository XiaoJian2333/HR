package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.xiaojian.model.Institution;

public class InstitutionDao {

	public int addIns(Connection con,Institution institution) throws Exception{
		String sql="insert into t_institution values(null,?,-1)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, institution.getInsName());
		return pstmt.executeUpdate();
	}
	
	public ResultSet firstInsList(Connection con) throws Exception{
		String sql = "select * from t_institution where pid='-1'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public int addIns2(Connection con,Institution institution) throws Exception{
		String sql="insert into t_institution values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, institution.getInsName());
		pstmt.setInt(2, institution.getPid());
		return pstmt.executeUpdate();
	}
	public ResultSet secondInsList(Connection con,Institution institution) throws Exception{
		String sql = "select * from t_institution where pid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, institution.getPid());
		return pstmt.executeQuery();
	}
	public ResultSet InsById(Connection con,Institution institution) throws Exception{
		String sql = "select * from t_institution where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, institution.getId());
		return pstmt.executeQuery();
	}
	public ResultSet listByIns1(Connection con,int i) throws Exception{
		String sql = "SELECT* from t_institution where pid in(select id from t_institution where pid = ?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, i);
		return pstmt.executeQuery();
	}
}
