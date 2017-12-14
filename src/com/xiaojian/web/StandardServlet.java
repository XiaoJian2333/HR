package com.xiaojian.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaojian.dao.SalaryStandardDao;
import com.xiaojian.dao.SalaryStandardDetailDao;
import com.xiaojian.model.PageBean;
import com.xiaojian.model.SalaryStandard;
import com.xiaojian.model.SalaryStandardDetail;
import com.xiaojian.service.PosSalaryItemService;
import com.xiaojian.util.DateUtil;
import com.xiaojian.util.DbUtil;
import com.xiaojian.util.JsonUtil;
import com.xiaojian.util.ResponseUtil;
import com.xiaojian.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class StandardServlet
 */
public class StandardServlet extends HttpServlet {
	
	DbUtil dbUtil = new DbUtil();
	PosSalaryItemService posSalaryItemService = new PosSalaryItemService();
	SalaryStandardDao salaryStandardDao = new SalaryStandardDao();
	SalaryStandardDetailDao salaryStandardDetailDao = new SalaryStandardDetailDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StandardServlet() {
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
			String positionId = request.getParameter("positionId");
			if(StringUtil.isNotEmpty(positionId)){
				int posId = Integer.parseInt(positionId);
				SalaryStandardDetail salaryStandardDetail = new SalaryStandardDetail(posId);
				Connection con=null;
				try{
					con = dbUtil.getCon();
					JSONArray jsonArray=posSalaryItemService.ItemList(con, salaryStandardDetail);
					ResponseUtil.write(response, jsonArray);
				}catch (Exception e) {
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
			
		}else if(add==2){
			String name = request.getParameter("name");
			int positionId =Integer.parseInt(request.getParameter("positionId")) ;
			double totalSalary = Double.parseDouble(request.getParameter("totalSalary"));
			String maker = request.getParameter("maker");
			String registrant = request.getParameter("registrant");
			String registrationTime = request.getParameter("registrationTime");
			String state = "待复核";
			String divice = "";			
			String[] itemid = request.getParameterValues("itemid");
			String[] itemName = request.getParameterValues("itemName");
			String[] salary = request.getParameterValues("salary");
			Connection con=null;
			SalaryStandard salaryStandardOnlyPosId = new SalaryStandard();
			salaryStandardOnlyPosId.setPositionid(positionId);
			try{
				con = dbUtil.getCon();
				ResultSet rSet = salaryStandardDao.searchByPositionId(con, salaryStandardOnlyPosId);
				if(!rSet.next()){
					SalaryStandard salaryStandard = new SalaryStandard(name, maker, registrant, DateUtil.formatString(registrationTime, "yyyy-MM-dd"), totalSalary, state, positionId, divice);
					int rsNum=salaryStandardDao.addStandard(con, salaryStandard);
					for(int i=0;i<itemid.length;i++){
						SalaryStandardDetail salaryStandardDetail = new SalaryStandardDetail(positionId, Integer.parseInt(itemid[i]), Double.parseDouble(salary[i]));
						salaryStandardDetailDao.addSalary(con, salaryStandardDetail);
					}
					JSONObject result = new JSONObject();		
					if(rsNum>0){
						result.put("success", "true");
					}else{
						result.put("success", "true");
						result.put("errorMsg", "保存失败");
					}
					ResponseUtil.write(response, result);
				}else{
					JSONObject result = new JSONObject();
					result.put("success", "true");
					result.put("errorMsg", "已存在该职位对应的薪酬标准！标准编号为："+rSet.getInt("id")+"，如需更改，请选择更改按钮！");
					ResponseUtil.write(response, result);
				}
			}catch (Exception e) {
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
			String page=request.getParameter("page");
			String rows=request.getParameter("rows");
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			Connection con=null;
			try{
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				JSONArray jsonArray=JsonUtil.formatRsToJsonArray(salaryStandardDao.salaryStandard(con, pageBean));
				int total=salaryStandardDao.salaryStandardCount(con);
				result.put("rows", jsonArray);
				result.put("total", total);
				ResponseUtil.write(response, result);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(add==4){
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int positionId =Integer.parseInt(request.getParameter("positionId")) ;
			double totalSalary = Double.parseDouble(request.getParameter("totalSalary"));
			String maker = request.getParameter("maker");
			String registrant = request.getParameter("registrant");
			String registrationTime = request.getParameter("registrationTime");
			String state = "";
			int istate = Integer.parseInt(request.getParameter("state"));
			if(istate==1){
				state="待复核";
			}if(istate==2){
				state="正常";
			}
			String divice = "";			
			String[] itemid = request.getParameterValues("itemid");
			String[] itemName = request.getParameterValues("itemName");
			String[] salary = request.getParameterValues("salary");
			Connection con=null;
			try{
				con = dbUtil.getCon();
					SalaryStandard salaryStandard = new SalaryStandard(name, maker, registrant, DateUtil.formatString(registrationTime, "yyyy-MM-dd"), totalSalary, state, positionId, divice);
					salaryStandard.setId(id);
					int rsNum=salaryStandardDao.updateStandard(con, salaryStandard);
					for(int i=0;i<itemid.length;i++){
						SalaryStandardDetail salaryStandardDetail = new SalaryStandardDetail(positionId, Integer.parseInt(itemid[i]), Double.parseDouble(salary[i]));
						salaryStandardDetailDao.addSalary(con, salaryStandardDetail);
					}
					JSONObject result = new JSONObject();		
					if(rsNum>0){
						result.put("success", "true");
					}else{
						result.put("success", "true");
						result.put("errorMsg", "保存失败");
					}
					ResponseUtil.write(response, result);
			}catch (Exception e) {
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
