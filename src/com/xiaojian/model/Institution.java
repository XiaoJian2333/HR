package com.xiaojian.model;

import com.sun.xml.internal.bind.v2.model.core.ID;

public class Institution {

	private int id;
	private String insName;
	private int pid;
	
	
	
	public Institution() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Institution(String insName) {
		super();
		this.insName = insName;
	}

	public Institution(String insName, int pid) {
		super();
		this.insName = insName;
		this.pid = pid;
	}
	

	public Institution(int pid) {
		super();
		this.pid = pid;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
}
