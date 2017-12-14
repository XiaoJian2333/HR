package com.xiaojian.model;

public class PositionName {
	private int id;
	private String name;
	private int parentId;
	
	public PositionName() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PositionName(String name, int parentId) {
		super();
		this.name = name;
		this.parentId = parentId;
	}
	
	public PositionName(int parentId) {
		super();
		this.parentId = parentId;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
