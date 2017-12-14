package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaojian.model.PageBean;
import com.xiaojian.model.Staff;
import com.xiaojian.util.DateUtil;
import com.xiaojian.util.StringUtil;

public class StaffDao {
	
	public ResultSet staffList(Connection con,PageBean pageBean,Staff staff,String InsIds,String PosIds,String btime,String etime)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_staff");
		System.out.println("qqq"+PosIds);
		if(StringUtil.isNotEmpty(InsIds)){
			sb.append(" and institutionId in("+InsIds+")");
		}
		if(StringUtil.isNotEmpty(PosIds)){
			sb.append(" and positionNameId in("+PosIds+")");
		}
		if(StringUtil.isNotEmpty(staff.getState())){
			sb.append(" and state ='"+staff.getState()+"'");
		}
		if(StringUtil.isNotEmpty(btime)){
			sb.append(" and DATE_FORMAT(registrationTime,'%Y-%m-%d')>=DATE_FORMAT('"+btime+"','%Y-%m-%d')");
		}
		if(StringUtil.isNotEmpty(etime)){
			sb.append(" and DATE_FORMAT(registrationTime,'%Y-%m-%d')<=DATE_FORMAT('"+etime+"','%Y-%m-%d')");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		System.out.println(sb.toString().replaceFirst("and", "where"));
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	
	public int staffCount(Connection con,Staff staff,String InsIds,String PosIds,String btime,String etime) throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_staff");
		if(StringUtil.isNotEmpty(InsIds)){
			sb.append(" and institutionId in("+InsIds+")");
		}
		if(StringUtil.isNotEmpty(PosIds)){
			sb.append(" and positionNameId in("+PosIds+")");
		}
		if(StringUtil.isNotEmpty(staff.getState())){
			sb.append(" and state ='"+staff.getState()+"'");
		}
		if(StringUtil.isNotEmpty(btime)){
			sb.append(" and DATE_FORMAT(registrationTime,'%Y-%m-%d')>=DATE_FORMAT('"+btime+"','%Y-%m-%d')");
		}
		if(StringUtil.isNotEmpty(etime)){
			sb.append(" and DATE_FORMAT(registrationTime,'%Y-%m-%d')<=DATE_FORMAT('"+etime+"','%Y-%m-%d')");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public ResultSet staffCountByInsId(Connection con,Staff staff) throws Exception{
		String sql="select * from t_staff where institutionId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, staff.getInstitutionId());
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	public int staffAdd(Connection con,Staff staff)throws Exception{
		String sql="insert into t_staff values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,staff.getStaffNum());
		pstmt.setInt(2,staff.getPositionNameId());
		pstmt.setInt(3,staff.getTitleId());
		pstmt.setString(4,staff.getName());
		pstmt.setString(5,staff.getSex());
		pstmt.setString(6,staff.getEmail());
		pstmt.setString(7,staff.getPhone());
		pstmt.setString(8,staff.getQq());
		pstmt.setString(9,staff.getMobile());
		pstmt.setString(10,staff.getAddress());
		pstmt.setString(11,staff.getZipCode());
		pstmt.setString(12,staff.getNationality());
		pstmt.setString(13,staff.getBirthPlace());
		if(staff.getBirthDate()==null){
			pstmt.setDate(14, (Date)staff.getBirthDate());
		}else{
			pstmt.setString(14,DateUtil.formatDate(staff.getBirthDate(), "yyyy-MM-dd"));	
		}
		pstmt.setString(15,staff.getEthnicity());
		pstmt.setString(16,staff.getReligiousBelief());
		pstmt.setString(17,staff.getPoliticalAffiliation());
		pstmt.setString(18,staff.getIdCard());
		pstmt.setString(19,staff.getSocialSecurityNumber());
		pstmt.setInt(20,staff.getAge());
		pstmt.setString(21,staff.getEducation());
		pstmt.setInt(22,staff.getEducationYear());
		pstmt.setString(23,staff.getEducationMajor());
		pstmt.setInt(24,staff.getInstitutionId());
		pstmt.setString(25,staff.getBank());
		pstmt.setString(26,staff.getBankNum());
		pstmt.setString(27,staff.getRegistrant());
		pstmt.setString(28,DateUtil.formatDate(staff.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"));
		pstmt.setString(29,staff.getSpecialty());
		pstmt.setString(30,staff.getHobby());
		pstmt.setString(31,staff.getPersonalResume());
		pstmt.setString(32,staff.getFamilyRelationships());
		pstmt.setString(33,staff.getRemark());
		pstmt.setString(34,staff.getState());
		pstmt.setString(35,staff.getImgUrl());
		return pstmt.executeUpdate();
	}
	public int staffUpdate(Connection con,Staff staff)throws Exception{
		String sql="update t_staff set  imgUrl=?,name=?, sex=?, email=?, phone=?, qq=?, mobile=?, address=?, zipCode=?, nationality=?, birthPlace=?, birthDate=?, ethnicity=?, religiousBelief=?, politicalAffiliation=?, idCard=?, socialSecurityNumber=?,age=?, education=?, educationYear=?, educationMajor=?,bank=?, bankNum=?, registrant=?, registrationTime=?,specialty=?, hobby=?, personalResume=?, familyRelationships=?, remark=?, state=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,staff.getImgUrl());
		pstmt.setString(2,staff.getName());
		pstmt.setString(3,staff.getSex());
		pstmt.setString(4,staff.getEmail());
		pstmt.setString(5,staff.getPhone());
		pstmt.setString(6,staff.getQq());
		pstmt.setString(7,staff.getMobile());
		pstmt.setString(8,staff.getAddress());
		pstmt.setString(9,staff.getZipCode());
		pstmt.setString(10,staff.getNationality());
		pstmt.setString(11,staff.getBirthPlace());
		if(staff.getBirthDate()==null){
			pstmt.setDate(12, (Date)staff.getBirthDate());
		}else{
			pstmt.setString(12,DateUtil.formatDate(staff.getBirthDate(), "yyyy-MM-dd"));	
		}
		pstmt.setString(13,staff.getEthnicity());
		pstmt.setString(14,staff.getReligiousBelief());
		pstmt.setString(15,staff.getPoliticalAffiliation());
		pstmt.setString(16,staff.getIdCard());
		pstmt.setString(17,staff.getSocialSecurityNumber());
		pstmt.setInt(18,staff.getAge());
		pstmt.setString(19,staff.getEducation());
		pstmt.setInt(20,staff.getEducationYear());
		pstmt.setString(21,staff.getEducationMajor());
		pstmt.setString(22,staff.getBank());
		pstmt.setString(23,staff.getBankNum());
		pstmt.setString(24,staff.getRegistrant());
		pstmt.setString(25,DateUtil.formatDate(staff.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"));
		pstmt.setString(26,staff.getSpecialty());
		pstmt.setString(27,staff.getHobby());
		pstmt.setString(28,staff.getPersonalResume());
		pstmt.setString(29,staff.getFamilyRelationships());
		pstmt.setString(30,staff.getRemark());
		pstmt.setString(31,staff.getState());
		pstmt.setInt(32,staff.getId());
		return pstmt.executeUpdate();
	}
	public int staffDelete(Connection con,String delIds,Staff staff)throws Exception{
		String sql="UPDATE t_staff set state='"+staff.getState()+"' where id in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
