package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaojian.model.SalaryItem;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SalaryItemDao {

	public int addSalaryItem(Connection con,SalaryItem salaryItem) throws Exception{
		String sql="insert into t_salaryItem values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, salaryItem.getItemName());
		pstmt.setInt(2, salaryItem.getisBasic());
		return pstmt.executeUpdate();
	}
	public JSONArray itemList(Connection con) throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql = "select * from t_salaryitem";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rs.getInt("id"));
			jsonObject.put("itemName", rs.getString("itemName"));
			if(rs.getInt("isBasic")==1){
				jsonObject.put("isBasic", "是");
			}else {
				jsonObject.put("isBasic", "否");
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	public ResultSet itemList2(Connection con) throws Exception{
		String sql = "select * from t_salaryitem";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public ResultSet itemById(Connection con,SalaryItem salaryItem) throws Exception{
		String sql = "select * from t_salaryitem where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, salaryItem.getId());
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
}
