package com.xiaojian.model;

import java.util.Date;


public class SalaryStandard {

	private int id;
	private String name;
	private String maker;
	private String registrant;
	private Date registrationTime;
	private double totalSalary;
	private String state;
	private int positionid;
	private String divice;
	
	public SalaryStandard(String name, String maker, String registrant, Date registrationTime, double totalSalary,
			String state, int positionid, String divice) {
		super();
		this.name = name;
		this.maker = maker;
		this.registrant = registrant;
		this.registrationTime = registrationTime;
		this.totalSalary = totalSalary;
		this.state = state;
		this.positionid = positionid;
		this.divice = divice;
	}
	
	public SalaryStandard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getRegistrant() {
		return registrant;
	}
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public double getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPositionid() {
		return positionid;
	}
	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}
	public String getDivice() {
		return divice;
	}
	public void setDivice(String divice) {
		this.divice = divice;
	}
	
}
