package com.xiaojian.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaojian.util.ResponseUtil;
import com.xiaojian.dao.SalaryStandardDetailDao;
import com.xiaojian.model.SalaryStandardDetail;
import com.xiaojian.util.DbUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SalaryStandardDetailServlet
 */
public class SalaryStandardDetailServlet extends HttpServlet {
	DbUtil dbUtil=new DbUtil();
	SalaryStandardDetailDao salaryStandardDetailDao = new SalaryStandardDetailDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryStandardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String updateIds = request.getParameter("updateIds");
		int positionid = Integer.parseInt(request.getParameter("posName"));
		SalaryStandardDetail salaryStandardDetail = new SalaryStandardDetail(positionid);
		
		String str[]=updateIds.split(",");
		
		Connection con=null;
		try {
			con = dbUtil.getCon();
			JSONObject result=new JSONObject();
			salaryStandardDetailDao.deleteByPositionId(con, salaryStandardDetail);
			int rsNums = salaryStandardDetailDao.addByPosidAndItemId(con, salaryStandardDetail,str);
			if(rsNums>0){
				result.put("success", "true");
				result.put("rsNums", rsNums);
			}else{
				result.put("errorMsg", "设置失败");
			}
			ResponseUtil.write(response, result);
		} catch(Exception e){
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
