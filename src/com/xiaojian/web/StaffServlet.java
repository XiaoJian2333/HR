package com.xiaojian.web;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaojian.util.ResponseUtil;
import com.xiaojian.model.Institution;
import com.xiaojian.model.PageBean;
import com.xiaojian.model.PositionName;
import com.xiaojian.util.JsonUtil;
import com.xiaojian.dao.InstitutionDao;
import com.xiaojian.dao.PositionNameDao;
import com.xiaojian.dao.StaffDao;
import com.xiaojian.model.Staff;
import com.xiaojian.service.StaffService;
import com.xiaojian.util.DateUtil;
import com.xiaojian.util.DbUtil;
import com.xiaojian.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class StaffSaveServlet
 */
public class StaffServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	DateUtil dateUtil = new DateUtil();
	StaffDao staffDao = new StaffDao();
	InstitutionDao institutionDao = new InstitutionDao();
	PositionNameDao positionNameDao = new PositionNameDao();
	StaffService staffService = new StaffService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
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
			String Ins1Id=request.getParameter("Ins1Id");
			String Ins2Id=request.getParameter("Ins2Id");
			String Ins3Id=request.getParameter("Ins3Id");
			int Pos1Id=Integer.parseInt(request.getParameter("Pos1Id"));
			int Pos2Id=Integer.parseInt(request.getParameter("Pos2Id"));
			int TitleId;
			if(StringUtil.isNotEmpty(request.getParameter("TitleId"))){
				TitleId=Integer.parseInt(request.getParameter("TitleId"));
			}else{
				TitleId=0;
			}
			String staffName=request.getParameter("staffName");
			String sex=request.getParameter("sex");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String qq=request.getParameter("qq");
			String mobile=request.getParameter("mobile");
			String address=request.getParameter("address");
			String zipCode=request.getParameter("zipCode");
			String nationality=request.getParameter("nationality");
			String birthPlace=request.getParameter("birthPlace");
			Date birthDate=null;
			if(StringUtil.isNotEmpty(request.getParameter("birthDate"))){
				try {
					birthDate=dateUtil.formatString(request.getParameter("birthDate"), "yyyy-MM-dd");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//DateUtil.formatString(birthday, "yyyy-MM-dd")
			}
			String ethnicity=request.getParameter("ethnicity");
			String religiousBelief=request.getParameter("religiousBelief");
			String politicalAffiliation=request.getParameter("politicalAffiliation");
			String idCard=request.getParameter("idCard");
			String socialSecurityNumber=request.getParameter("socialSecurityNumber");
			int age = 0;
			if(StringUtil.isNotEmpty(request.getParameter("age"))){
				age=Integer.parseInt(request.getParameter("age"));
			}
			String education=request.getParameter("education");
			int educationYear;
			if(StringUtil.isNotEmpty(request.getParameter("educationYear"))){
				educationYear=Integer.parseInt(request.getParameter("educationYear"));
			}else{
				educationYear=0;
			}
			String educationMajor=request.getParameter("educationMajor");
			String standardId=request.getParameter("standardId");
			String bank=request.getParameter("bank");
			String bankNum=request.getParameter("bankNum");
			String registrant=request.getParameter("registrant");
			String registrationTime=request.getParameter("registrationTime");
			String specialty=request.getParameter("specialty");
			String hobby=request.getParameter("hobby");
			String personalResume=request.getParameter("personalResume");
			String familyRelationships=request.getParameter("familyRelationships");
			String remark=request.getParameter("remark");
			String state="待复核";
			String imgUrl="";
			
			int InsId=0;//输入staff表的机构ID
			if(StringUtil.isEmpty(Ins3Id)){       //判断输入的机构号
				if(StringUtil.isEmpty(Ins2Id)){
					InsId = Integer.parseInt(Ins1Id);
				}else{
					InsId = Integer.parseInt(Ins2Id);
				}
			}else{
				InsId = Integer.parseInt(Ins3Id);
			}
			String registrationYear = registrationTime.substring(0, 4);//登记年份
			String Ins11Id;
			String Ins22Id;
			String Ins33Id;
			if(Ins1Id.length()>1){
				Ins11Id=Ins1Id;
			}else{
				Ins11Id="0"+Ins1Id;
			}
			if(Ins2Id.length()==0){
				Ins22Id="00";
			}else if(Ins2Id.length()>1){
				Ins22Id=Ins2Id;
			}else{
				Ins22Id="0"+Ins2Id;
			}
			if(Ins3Id.length()==0){
				Ins33Id="00";
			}else if(Ins3Id.length()>1){
				Ins33Id=Ins3Id;
			}else{
				Ins33Id="0"+Ins3Id;
			}
			String staffNum=registrationYear+Ins11Id+Ins22Id+Ins33Id;
			
			
			Connection con=null;
			try{
				con=dbUtil.getCon();
				
				Staff staffonlyIns = new Staff(InsId);
				int totalStaffInIns=0;
				ResultSet resultSet = staffDao.staffCountByInsId(con, staffonlyIns);
				if(!resultSet.next()){
					staffNum+="01";
				}else{
					resultSet.beforeFirst();
					List<Integer> list = new ArrayList<Integer>();
					while(resultSet.next()){
						String tmp = resultSet.getString("staffNum").substring(10, 12);
						int itmp = Integer.parseInt(tmp);
						list.add(itmp);
					}
					int a[]=new int[list.size()];
					for (int i = 0; i <list.size(); i++) {
						a[i]=list.get(i);
					}
					Arrays.sort(a);
					for (int i = 0; i <list.size()-1; i++) {
						
					//	System.out.println(a[i]);
						if ((a[i]+1)==a[i+1]) {
							continue;
						}else {
							totalStaffInIns=a[i]+1;
							break;
						}
					}
					if(totalStaffInIns==0){
						totalStaffInIns=a[a.length-1]+1;
					}
					if(totalStaffInIns<10){
						staffNum+="0"+totalStaffInIns;
					}else{
						staffNum+=totalStaffInIns;
					}
				}
				
				
				Staff staff = new Staff(staffNum, Pos2Id, TitleId, staffName, sex, 
						email, phone, qq, mobile, address, zipCode, nationality, 
						birthPlace, birthDate, ethnicity, religiousBelief, 
						politicalAffiliation, idCard, socialSecurityNumber,
						age, education, educationYear, educationMajor, InsId,
						bank, bankNum, registrant, 
						dateUtil.formatString(registrationTime, "yyyy-MM-dd HH:mm:ss"),
						specialty, hobby, personalResume, familyRelationships, remark, 
						state, imgUrl);
				con=dbUtil.getCon();
				int saveNums = 0;
				JSONObject result = new JSONObject();
				saveNums = staffDao.staffAdd(con, staff);
				
				if(saveNums>0){
					result.put("success", "true");
				}else{
					result.put("success", "true");
					result.put("errorMsg", "保存失败");
				}
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
		}else if(add==2){
			String s_Ins1Id = request.getParameter("s_Ins1Id");
			String s_Ins2Id = request.getParameter("s_Ins2Id");
			String s_Ins3Id = request.getParameter("s_Ins3Id");
			String s_btime = request.getParameter("s_btime");
			String s_etime = request.getParameter("s_etime");
			String s_state = request.getParameter("s_state");
			String s_Pos1Id = request.getParameter("s_Pos1Id");
			String s_Pos2Id = request.getParameter("s_Pos2Id");
			Connection con1 = null;
			
			String InsIds="";
			String PosIds="";
			try{
				con1=dbUtil.getCon();
				if(StringUtil.isNotEmpty(s_Ins1Id)){
					List<Integer> list = new ArrayList<Integer>();
					if(StringUtil.isEmpty(s_Ins2Id)){
						//1
						int ins1=Integer.parseInt(s_Ins1Id);
						ResultSet rs = institutionDao.listByIns1(con1, ins1);
						InsIds+=ins1;
						while(rs.next()){
							list.add(rs.getInt("id"));
							list.add(rs.getInt("pid"));
						}
						int a[]=new int[list.size()];
						for (int i = 0; i <list.size(); i++) {
							a[i]=list.get(i);
						}
						Arrays.sort(a);
						for(int i = 0;i<a.length;i++){
							if(i==0){
								InsIds+=","+a[i];
							}else{
								if(a[i]!=a[i-1]){
									InsIds+=","+a[i];
								}
							}
						}
					}else{
						if(StringUtil.isEmpty(s_Ins3Id)){
							//2
							int ins2=Integer.parseInt(s_Ins2Id);
							InsIds+=ins2;
							Institution institution = new Institution(ins2);
							ResultSet rs = institutionDao.secondInsList(con1, institution);
							while(rs.next()){
								list.add(rs.getInt("id"));
							}
							int a[]=new int[list.size()];
							for (int i = 0; i <list.size(); i++) {
								a[i]=list.get(i);
							}
							Arrays.sort(a);
							for(int i = 0;i<a.length;i++){
								if(i==0){
									InsIds+=","+a[i];
								}else{
									if(a[i]!=a[i-1]){
										InsIds+=","+a[i];
									}
								}
							}
						}else{
							int ins3=Integer.parseInt(s_Ins3Id);
							InsIds+=ins3;
						}
					}
				}
				if(StringUtil.isNotEmpty(s_Pos1Id)){
					List<Integer> list = new ArrayList<Integer>();
					if(StringUtil.isEmpty(s_Pos2Id)){
						int pos1 = Integer.parseInt(s_Pos1Id);
						PositionName positionName = new PositionName(pos1);
						ResultSet rs = positionNameDao.searchByPid(con1, positionName);
						while(rs.next()){
							list.add(rs.getInt("id"));
						}
						int a[]=new int[list.size()];
						for (int i = 0; i <list.size(); i++) {
							a[i]=list.get(i);
						}
						Arrays.sort(a);
						for(int i = 0;i<a.length;i++){
							if(i==0){
								PosIds+=a[i];
							}else{
								if(a[i]!=a[i-1]){
									PosIds+=","+a[i];
								}
							}
						}
					}else{
						int pos2 = Integer.parseInt(s_Pos2Id);
						PosIds+=pos2;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			Staff staff = new Staff();
			staff.setState(s_state);
			String page=request.getParameter("page");
			String rows=request.getParameter("rows");
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			Connection con=null;
			try{
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				System.out.println(PosIds);
				JSONArray jsonArray=staffService.currentItemList(con, pageBean,staff,InsIds,PosIds,s_btime,s_etime);
				int total=staffDao.staffCount(con,staff,InsIds,PosIds,s_btime,s_etime);
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
		}else if(add==3){
			int id = Integer.parseInt(request.getParameter("id"));
			String staffName=request.getParameter("staffName");
			String sex=request.getParameter("sex");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String qq=request.getParameter("qq");
			String mobile=request.getParameter("mobile");
			String address=request.getParameter("address");
			String zipCode=request.getParameter("zipCode");
			String nationality=request.getParameter("nationality");
			String birthPlace=request.getParameter("birthPlace");
			Date birthDate=null;
			if(StringUtil.isNotEmpty(request.getParameter("birthDate"))){
				try {
					birthDate=dateUtil.formatString(request.getParameter("birthDate"), "yyyy-MM-dd");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//DateUtil.formatString(birthday, "yyyy-MM-dd")
			}
			String ethnicity=request.getParameter("ethnicity");
			String religiousBelief=request.getParameter("religiousBelief");
			String politicalAffiliation=request.getParameter("politicalAffiliation");
			String idCard=request.getParameter("idCard");
			String socialSecurityNumber=request.getParameter("socialSecurityNumber");
			int age = 0;
			if(StringUtil.isNotEmpty(request.getParameter("age"))){
				age=Integer.parseInt(request.getParameter("age"));
			}
			String education=request.getParameter("education");
			int educationYear;
			if(StringUtil.isNotEmpty(request.getParameter("educationYear"))){
				educationYear=Integer.parseInt(request.getParameter("educationYear"));
			}else{
				educationYear=0;
			}
			String educationMajor=request.getParameter("educationMajor");
			String standardId=request.getParameter("standardId");
			String bank=request.getParameter("bank");
			String bankNum=request.getParameter("bankNum");
			String registrant=request.getParameter("registrant");
			String registrationTime=request.getParameter("registrationTime");
			String specialty=request.getParameter("specialty");
			String hobby=request.getParameter("hobby");
			String personalResume=request.getParameter("personalResume");
			String familyRelationships=request.getParameter("familyRelationships");
			String remark=request.getParameter("remark");
			int istate = Integer.parseInt(request.getParameter("state"));
			String state="";
			if(istate==1){
				state="待复核";
			}if(istate==2){
				state="正常";
			}
			String imgUrl="";
			
			Connection con=null;
			try{
				con=dbUtil.getCon();
				Staff staff = new Staff( id,0, staffName, sex, 
						email, phone, qq, mobile, address, zipCode, nationality, 
						birthPlace, birthDate, ethnicity, religiousBelief, 
						politicalAffiliation, idCard, socialSecurityNumber,
						age, education, educationYear, educationMajor,
						bank, bankNum, registrant, 
						dateUtil.formatString(registrationTime, "yyyy-MM-dd HH:mm:ss"),
						specialty, hobby, personalResume, familyRelationships, remark, 
						state, imgUrl);
				con=dbUtil.getCon();
				int saveNums = 0;
				JSONObject result = new JSONObject();
				saveNums = staffDao.staffUpdate(con, staff);
				
				if(saveNums>0){
					result.put("success", "true");
				}else{
					result.put("success", "true");
					result.put("errorMsg", "保存失败");
				}
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
			String s_btime ="";
			String s_etime ="";
			String s_state ="待复核";
			String InsIds="";
			String PosIds="";
			
			Staff staff = new Staff();
			staff.setState(s_state);
			String page=request.getParameter("page");
			String rows=request.getParameter("rows");
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			Connection con=null;
			try{
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				System.out.println(PosIds);
				JSONArray jsonArray=staffService.currentItemList(con, pageBean,staff,InsIds,PosIds,s_btime,s_etime);
				int total=staffDao.staffCount(con,staff,InsIds,PosIds,s_btime,s_etime);
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
		}else if(add==5||add==6){
			String delIds = request.getParameter("delIds");
			String state="";
			if(add==5){
				state="已删除";
			}else{
				state="待复核";
			}
			Staff staff = new Staff();
			staff.setState(state);
			Connection con=null;
			try{
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				int delNums=staffDao.staffDelete(con, delIds,staff);
				if(delNums>0){
					result.put("success", "true");
					result.put("delNums", delNums);
				}else{
					result.put("errorMsg", "删除失败");
				}
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
