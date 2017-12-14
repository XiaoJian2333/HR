package com.xiaojian.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaojian.util.JsonUtil;
import com.xiaojian.dao.SalaryItemDao;
import com.xiaojian.model.SalaryItem;
import com.xiaojian.model.SalaryStandardDetail;
import com.xiaojian.service.PosSalaryItemService;
import com.xiaojian.util.DbUtil;
import com.xiaojian.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SalaryItemServlet
 */
public class SalaryItemServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	SalaryItemDao salaryItemDao = new SalaryItemDao();
	PosSalaryItemService posSalaryItemService = new PosSalaryItemService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryItemServlet() {
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
			String itemName = request.getParameter("itemName");
			int isBasic= Integer.parseInt(request.getParameter("basic"));
			SalaryItem salaryItem = new SalaryItem(itemName, isBasic);
			Connection con = null;
			try{
				con = dbUtil.getCon();
				JSONObject result = new JSONObject();
				int addNums = salaryItemDao.addSalaryItem(con, salaryItem);
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
			String posName = request.getParameter("posName");
			Connection con=null;
			if(posName==null ||posName.equals("")){
				
				try {
					con=dbUtil.getCon();
					JSONObject result=new JSONObject();
					JSONArray jsonArray=salaryItemDao.itemList(con);
					result.put("rows", jsonArray);
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
			}else{
				SalaryStandardDetail salaryStandardDetail = new SalaryStandardDetail(Integer.parseInt(posName));
				try {
					con=dbUtil.getCon();
					JSONObject result=new JSONObject();
					JSONArray jsonArray=posSalaryItemService.currentItemList(con, salaryStandardDetail);
					result.put("rows", jsonArray);
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
