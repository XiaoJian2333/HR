package com.xiaojian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xiaojian.model.Auth;
import com.xiaojian.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AuthDao {

	public JSONArray getAuthByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId=? and authId in ("+authIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rs.getInt("authId"));
			jsonObject.put("text", rs.getString("authName"));
			jsonObject.put("state", rs.getString("state"));
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath", rs.getString("authPath"));
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	
	public JSONArray getAuthsByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=this.getAuthByParentId(con, parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getAuthsByParentId(con,jsonObject.getString("id"),authIds));
			}
		}
		return jsonArray;
	}
	
	
}
