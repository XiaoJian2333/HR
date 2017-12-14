package com.xiaojian.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaojian.dao.PositionNameDao;
import com.xiaojian.dao.PositionTypeDao;
import com.xiaojian.model.Institution;
import com.xiaojian.model.PositionName;
import com.xiaojian.model.PositionType;
import com.xiaojian.util.DbUtil;
import com.xiaojian.util.JsonUtil;
import com.xiaojian.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddPositionServlet
 */
public class AddPositionServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	PositionTypeDao positionTypeDao = new PositionTypeDao();
	PositionNameDao positionNameDao = new PositionNameDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPositionServlet() {
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
			String posType = request.getParameter("posType");
			PositionType positionType = new PositionType(posType);
			Connection con = null;
			try{
				con = dbUtil.getCon();
				JSONObject result = new JSONObject();
				int addNums = positionTypeDao.addPosType(con, positionType);
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
		}else if (add==2) {
			Connection con=null;
			try {
				con=dbUtil.getCon();
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", "");
				jsonObject.put("name", "请选择...");
				jsonArray.add(jsonObject);
				jsonArray.addAll(JsonUtil.formatRsToJsonArray(positionTypeDao.posTypeList(con)));
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
		}else if (add==3) {
			String  posName = request.getParameter("posName");
			if(!request.getParameter("posTypeId").equals("")){			
				int pid = Integer.parseInt(request.getParameter("posTypeId"));
				PositionName positionName = new PositionName(posName, pid);
				Connection con = null;
				try{
					con = dbUtil.getCon();
					JSONObject result = new JSONObject();
					int addNums = positionNameDao.addPosition(con, positionName);
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
				result.put("errorMsg", "请选择职位类型");
				try {
					ResponseUtil.write(response, result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(add==4){
			Connection con=null;
			if(request.getParameter("id")!=""){
				int pid = Integer.parseInt(request.getParameter("id"));
				PositionName positionName = new PositionName(pid);
				try {
					con=dbUtil.getCon();
					JSONArray jsonArray=new JSONArray();
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("id", "");
					jsonObject.put("name", "请选择...");
					jsonArray.add(jsonObject);
					jsonArray.addAll(JsonUtil.formatRsToJsonArray(positionNameDao.searchByPid(con, positionName)));
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
			}
		}else if(add==5){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", "");
				jsonObject.put("name", "请选择...");
				jsonArray.add(jsonObject);
				jsonArray.addAll(JsonUtil.formatRsToJsonArray(positionNameDao.positionNameList(con)));
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
