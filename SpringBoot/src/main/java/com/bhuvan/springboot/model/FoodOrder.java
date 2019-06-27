package com.bhuvan.springboot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class FoodOrder {
	

	@Id
	@GeneratedValue
  	@Column(name="ID")
	private int Id;	
	
	private String userid;
	
	private int quantity;
	
	private int productid;
	
	private double price;
	
	public FoodOrder(){
		
	}
	
	public FoodOrder(String userId, int quantity, int productId, double price){
		this.userid = userId;
		this.quantity = quantity;
		this.productid = productId;
		this.price = price;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
