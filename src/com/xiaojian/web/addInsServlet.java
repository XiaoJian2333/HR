package com.xiaojian.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaojian.dao.InstitutionDao;
import com.xiaojian.model.Institution;
import com.xiaojian.util.DbUtil;
import com.xiaojian.util.JsonUtil;
import com.xiaojian.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class addInsServlet
 */
public class addInsServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	InstitutionDao institutionDao = new InstitutionDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addInsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int add=0;
		add = Integer.parseInt(request.getParameter("add"));
		if(add==1){
			
			String  insName1 = request.getParameter("insName1");
			Institution institution = new Institution(insName1);
			Connection con = null;
			try{
				con = dbUtil.getCon();
				JSONObject result = new JSONObject();
				int addNums = institutionDao.addIns(con, institution);
				if(addNums>0){
					result.put("success", "true");
				}else{
					result.put("success", "true");
					result.put("errorMsg", "新增失败");
				}
				ResponseUtil.write(response, result);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try{
					dbUtil.closeCon(con);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}else if(add==2){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", "");
				jsonObject.put("insName", "请选择...");
				jsonArray.add(jsonObject);
				jsonArray.addAll(JsonUtil.formatRsToJsonArray(institutionDao.firstInsList(con)));
				ResponseUtil.write(response, jsonArray);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(add==3){
			String  insName2 = request.getParameter("insName2");
			if(!request.getParameter("insId1").equals("")){			
				int pid = Integer.parseInt(request.getParameter("insId1"));
				Institution institution = new Institution(insName2,pid);
				Connection con = null;
				try{
					con = dbUtil.getCon();
					JSONObject result = new JSONObject();
					int addNums = institutionDao.addIns2(con, institution);
					if(addNums>0){
						result.put("success", "true");
					}else{
						result.put("success", "true");
						result.put("errorMsg", "新增失败");
					}
					ResponseUtil.write(response, result);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally {
					try{
						dbUtil.closeCon(con);
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}else{
				JSONObject result = new JSONObject();
				result.put("errorMsg", "请选择一级机构");
				try {
					ResponseUtil.write(response, result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (add==4) {
			Connection con=null;
			if(request.getParameter("id")!=""){
				int pid = Integer.parseInt(request.getParameter("id"));
				Institution institution = new Institution(pid);
				try {
					con=dbUtil.getCon();
					JSONArray jsonArray=new JSONArray();
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("id", "");
					jsonObject.put("insName", "请选择...");
					jsonArray.add(jsonObject);
					jsonArray.addAll(JsonUtil.formatRsToJsonArray(institutionDao.secondInsList(con, institution)));
					ResponseUtil.write(response, jsonArray);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						dbUtil.closeCon(con);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", "");
				jsonObject.put("insName", "请选择...");
				jsonArray.add(jsonObject);
				try {
					ResponseUtil.write(response, jsonArray);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(add==5){
			String  insName3 = request.getParameter("insName3");
			if(!request.getParameter("insId2").equals("")){
				
				int pid = Integer.parseInt(request.getParameter("insId2"));
				Institution institution = new Institution(insName3,pid);
				Connection con = null;
				try{
					con = dbUtil.getCon();
					JSONObject result = new JSONObject();
					int addNums = institutionDao.addIns2(con, institution);
					if(addNums>0){
						result.put("success", "true");
					}else{
						result.put("success", "true");
						result.put("errorMsg", "新增失败");
					}
					ResponseUtil.write(response, result);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally {
					try{
						dbUtil.closeCon(con);
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}else{
				JSONObject result = new JSONObject();
				result.put("errorMsg", "请选择机构");
				try {
					ResponseUtil.write(response, result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
