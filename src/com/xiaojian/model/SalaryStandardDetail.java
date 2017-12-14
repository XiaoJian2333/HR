package com.xiaojian.model;

public class SalaryStandardDetail {

	private int positionId;
	private int itemId;
	private double salary;
	public SalaryStandardDetail(int positionId) {
		super();
		this.positionId = positionId;
	}
	
	public SalaryStandardDetail(int positionId, int itemId, double salary) {
		super();
		this.positionId = positionId;
		this.itemId = itemId;
		this.salary = salary;
	}

	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
