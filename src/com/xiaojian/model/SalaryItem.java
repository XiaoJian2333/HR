package com.xiaojian.model;

public class SalaryItem {

	private int id;
	private String itemName;
	private int isBasic;
	
	public SalaryItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SalaryItem(String itemName, int isBasic) {
		super();
		this.itemName = itemName;
		this.isBasic = isBasic;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getisBasic() {
		return isBasic;
	}
	public void setisBasic(int isBasic) {
		this.isBasic = isBasic;
	}
	
}
