package com.xiaojian.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaojian.dao.AuthDao;
import com.xiaojian.dao.RoleDao;
import com.xiaojian.model.User;
import com.xiaojian.util.DbUtil;
import com.xiaojian.util.ResponseUtil;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class AuthServlet
 */
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	AuthDao authDao=new AuthDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
		String parentId=request.getParameter("parentId");
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		RoleDao roleDao=new RoleDao();
		
		try {
			Connection con=dbUtil.getCon();
			String ids = roleDao.getAuthIdsById(con,currentUser.getroleId());
			JSONArray treeJsonArray = authDao.getAuthsByParentId(con, parentId, ids);
			ResponseUtil.write(response, treeJsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
