package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaojian.model.PageBean;
import com.xiaojian.model.SalaryStandard;
import com.xiaojian.model.Staff;
import com.xiaojian.util.DateUtil;
import com.xiaojian.util.StringUtil;


public class SalaryStandardDao {

	public int addStandard(Connection con,SalaryStandard salaryStandard)throws Exception{
		String sql="INSERT INTO t_SalaryStandard VALUES(null,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, salaryStandard.getName());
		pstmt.setString(2, salaryStandard.getMaker());
		pstmt.setString(3, salaryStandard.getRegistrant());
		pstmt.setString(4,DateUtil.formatDate(salaryStandard.getRegistrationTime(), "yyyy-MM-dd"));
		pstmt.setDouble(5, salaryStandard.getTotalSalary());
		pstmt.setString(6, salaryStandard.getState());
		pstmt.setInt(7, salaryStandard.getPositionid());
		pstmt.setString(8, salaryStandard.getDivice());
		return pstmt.executeUpdate();
	}
	public int updateStandard(Connection con,SalaryStandard salaryStandard)throws Exception{
		String sql="update t_salaryStandard set name=?,maker=?,registrant=?,registrationTime=?,totalSalary=?,state=?,positionId=?,divice=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, salaryStandard.getName());
		pstmt.setString(2, salaryStandard.getMaker());
		pstmt.setString(3, salaryStandard.getRegistrant());
		pstmt.setString(4,DateUtil.formatDate(salaryStandard.getRegistrationTime(), "yyyy-MM-dd"));
		pstmt.setDouble(5, salaryStandard.getTotalSalary());
		pstmt.setString(6, salaryStandard.getState());
		pstmt.setInt(7, salaryStandard.getPositionid());
		pstmt.setString(8, salaryStandard.getDivice());
		pstmt.setInt(9, salaryStandard.getId());
		return pstmt.executeUpdate();
	}
	public ResultSet salaryStandard(Connection con,PageBean pageBean)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_salaryStandard");
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int salaryStandardCount(Connection con) throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_salaryStandard");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public ResultSet searchByPositionId(Connection con,SalaryStandard salaryStandard) throws Exception{
		String sql = "select * from t_salaryStandard where positionId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, salaryStandard.getPositionid());
		return pstmt.executeQuery();
	}
}
