package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xiaojian.model.SalaryStandardDetail;
import com.xiaojian.model.Staff;;

public class SalaryStandardDetailDao {

	public ResultSet searchByPositionid(Connection con,SalaryStandardDetail salaryStandardDetail ) throws Exception{
		String sql = "select * from t_salaryStandardDetail where positionid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, salaryStandardDetail.getPositionId());
		return pstmt.executeQuery();
	}
	public int deleteByPositionId(Connection con,SalaryStandardDetail salaryStandardDetail) throws Exception {
		
		String sql="delete from t_salaryStandardDetail where positionid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, salaryStandardDetail.getPositionId());
		return pstmt.executeUpdate();
	}
	public int addByPosidAndItemId(Connection con,SalaryStandardDetail salaryStandardDetail,String[] str)throws Exception{
		String sql="INSERT INTO t_salaryStandardDetail(positionid,itemid) VALUES(?,?);";
		PreparedStatement pstmt = con.prepareStatement(sql);
		int all = 0;
		for(int i = 0;i<str.length;i++){
			pstmt.setInt(1, salaryStandardDetail.getPositionId());
			pstmt.setInt(2, Integer.parseInt(str[i]));
			all+=pstmt.executeUpdate();
		}
		return all;
	}
	public int addSalary(Connection con,SalaryStandardDetail salaryStandardDetail)throws Exception{
		String sql="UPDATE t_SalaryStandardDetail set salary=? where positionid=? and itemid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setDouble(1, salaryStandardDetail.getSalary());
		pstmt.setInt(2, salaryStandardDetail.getPositionId());
		pstmt.setInt(3, salaryStandardDetail.getItemId());
		return pstmt.executeUpdate();
	}
}
