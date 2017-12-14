package com.xiaojian.model;

public class PositionType {
	private int id;
	private String name;
	
	public PositionType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PositionType(String name) {
		super();
		this.name = name;
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
}
