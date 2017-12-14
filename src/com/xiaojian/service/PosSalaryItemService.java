package com.xiaojian.service;

import java.sql.Connection;
import java.sql.ResultSet;

import com.xiaojian.dao.SalaryItemDao;
import com.xiaojian.dao.SalaryStandardDetailDao;
import com.xiaojian.model.SalaryItem;
import com.xiaojian.model.SalaryStandardDetail;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PosSalaryItemService {

	SalaryItemDao salaryItemDao = new SalaryItemDao();
	SalaryStandardDetailDao salaryStandardDetailDao = new SalaryStandardDetailDao();
	public JSONArray currentItemList(Connection con,SalaryStandardDetail salaryStandardDetail) throws Exception {
		JSONArray jsonArray = new JSONArray();
		ResultSet rsItem = salaryItemDao.itemList2(con);
		ResultSet rsDetail = salaryStandardDetailDao.searchByPositionid(con, salaryStandardDetail);
		while(rsItem.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rsItem.getInt("id"));
			jsonObject.put("itemName", rsItem.getString("itemName"));
			if(rsItem.getInt("isBasic")==1){
				jsonObject.put("isBasic", "是");
			}else {
				jsonObject.put("isBasic", "否");
			}
			
			while(rsDetail.next()){
				if(rsDetail.getInt("itemId")==rsItem.getInt("id")){
					jsonObject.put("isChose", "选中");
				}
			}
			rsDetail.beforeFirst();
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	//职位选中的薪酬项目名称
	public JSONArray ItemList(Connection con,SalaryStandardDetail salaryStandardDetail) throws Exception {
		JSONArray jsonArray = new JSONArray();
		ResultSet rsdetail = salaryStandardDetailDao.searchByPositionid(con, salaryStandardDetail);
		while(rsdetail.next()){
			SalaryItem salaryItem = new SalaryItem();
			salaryItem.setId(rsdetail.getInt("itemid"));
			ResultSet rsitem = salaryItemDao.itemById(con, salaryItem);
			if(rsitem.next()){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("itemid", rsitem.getInt("id"));
				jsonObject.put("itemName", rsitem.getString("itemName"));
				jsonObject.put("salary", rsdetail.getString("salary"));				
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}
}
