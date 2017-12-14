package com.xiaojian.service;

import java.sql.Connection;
import java.sql.ResultSet;

import com.xiaojian.dao.InstitutionDao;
import com.xiaojian.dao.PositionNameDao;
import com.xiaojian.dao.PositionTypeDao;
import com.xiaojian.dao.StaffDao;
import com.xiaojian.dao.TitleDao;
import com.xiaojian.model.Institution;
import com.xiaojian.model.PageBean;
import com.xiaojian.model.PositionName;
import com.xiaojian.model.PositionType;
import com.xiaojian.model.Staff;
import com.xiaojian.model.Title;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StaffService {

	TitleDao titleDao = new TitleDao();
	StaffDao staffDao = new StaffDao();
	InstitutionDao institutionDao = new InstitutionDao();
	PositionNameDao positionNameDao = new PositionNameDao();
	PositionTypeDao positionTypeDao = new PositionTypeDao();
	public JSONArray currentItemList(Connection con,PageBean pageBean,Staff staff,String InsIds,String PosIds,String btime,String etime) throws Exception {
		JSONArray jsonArray = new JSONArray();
		ResultSet rs = staffDao.staffList(con, pageBean,staff,InsIds,PosIds,btime,etime);
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rs.getInt("id"));
			jsonObject.put("staffNum", rs.getString("staffNum"));
			jsonObject.put("positionNameId", rs.getInt("positionNameId"));
			jsonObject.put("titleId", rs.getInt("titleId"));
			jsonObject.put("staffName", rs.getString("name"));
			jsonObject.put("sex", rs.getString("sex"));
			jsonObject.put("email", rs.getString("email"));
			jsonObject.put("phone", rs.getString("phone"));
			jsonObject.put("qq", rs.getString("qq"));
			jsonObject.put("mobile", rs.getString("mobile"));
			jsonObject.put("address", rs.getString("address"));
			jsonObject.put("zipCode", rs.getString("zipCode"));
			jsonObject.put("nationality", rs.getString("nationality"));
			jsonObject.put("birthPlace", rs.getString("birthPlace"));
			jsonObject.put("birthDate", rs.getString("birthDate"));
			jsonObject.put("ethnicity", rs.getString("ethnicity"));
			jsonObject.put("religiousBelief", rs.getString("religiousBelief"));
			jsonObject.put("politicalAffiliation", rs.getString("politicalAffiliation"));
			jsonObject.put("idCard", rs.getString("idCard"));
			jsonObject.put("socialSecurityNumber", rs.getString("socialSecurityNumber"));
			jsonObject.put("age", rs.getInt("age"));
			jsonObject.put("education", rs.getString("education"));
			jsonObject.put("educationYear", rs.getInt("educationYear"));
			jsonObject.put("educationMajor", rs.getString("educationMajor"));
			jsonObject.put("institutionId", rs.getInt("institutionId"));
			jsonObject.put("bank", rs.getString("bank"));
			jsonObject.put("bankNum", rs.getString("bankNum"));
			jsonObject.put("registrant", rs.getString("registrant"));
			jsonObject.put("registrationTime", rs.getString("registrationTime"));
			jsonObject.put("specialty", rs.getString("specialty"));
			jsonObject.put("hobby", rs.getString("hobby"));
			jsonObject.put("personalResume", rs.getString("personalResume"));
			jsonObject.put("familyRelationships", rs.getString("familyRelationships"));
			jsonObject.put("remark", rs.getString("remark"));
			jsonObject.put("state", rs.getString("state"));
			jsonObject.put("imgUrl", rs.getString("imgUrl"));
			Title title = new Title();
			title.setId(rs.getInt("titleId"));
			ResultSet titlers = titleDao.titleName(con, title);
			if(titlers.next()){
				jsonObject.put("TitleId", titlers.getString("name"));
			}
			
			PositionName positionName = new PositionName();
			positionName.setId(rs.getInt("positionNameId"));
			ResultSet PosNameRs = positionNameDao.searchById(con, positionName);
			if(PosNameRs.next()){
				jsonObject.put("Pos2Id",PosNameRs.getString("name"));
				PositionType positionType = new PositionType();
				positionType.setId(PosNameRs.getInt("parentId"));
				ResultSet PosTypeRs = positionTypeDao.posTypeById(con, positionType);
				if(PosTypeRs.next()){
					jsonObject.put("Pos1Id",PosTypeRs.getString("name"));
				}
			}
			String[] InsName= new String[3];
			int insId = rs.getInt("institutionId");
			for(int i=0;i<3;i++){
				Institution institution = new Institution();
				institution.setId(insId);
				ResultSet InsRs = institutionDao.InsById(con, institution);
				if(InsRs.next()){
					InsName[i] = InsRs.getString("insName");
					insId = InsRs.getInt("pid");
				}else{
					break;
				}
			}
			if(InsName[2]!=null){
				jsonObject.put("Ins1Id", InsName[2]);
				jsonObject.put("Ins2Id", InsName[1]);
				jsonObject.put("Ins3Id", InsName[0]);
			}else{
				if(InsName[1]!=null){
					jsonObject.put("Ins1Id", InsName[1]);
					jsonObject.put("Ins2Id", InsName[0]);
					jsonObject.put("Ins3Id", "");
				}else{
					jsonObject.put("Ins1Id", InsName[0]);
					jsonObject.put("Ins2Id", "");
					jsonObject.put("Ins3Id", "");
				}
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
