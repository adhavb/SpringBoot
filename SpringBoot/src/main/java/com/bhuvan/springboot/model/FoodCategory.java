package com.bhuvan.springboot.model;

public class FoodCategory {
	
	private int ctgryId;
	
	private String ctgryName;
	
	public FoodCategory(){
		
	}
	
	public FoodCategory(int ctgryId, String ctgryName){
		this.ctgryId = ctgryId;
		this.ctgryName = ctgryName;
	}

	public int getCtgryId() {
		return ctgryId;
	}

	public void setCtgryId(int ctgryId) {
		this.ctgryId = ctgryId;
	}

	public String getCtgryName() {
		return ctgryName;
	}

	public void setCtgryName(String ctgryName) {
		this.ctgryName = ctgryName;
	}

}
