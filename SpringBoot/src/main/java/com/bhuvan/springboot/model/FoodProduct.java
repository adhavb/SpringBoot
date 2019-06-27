package com.bhuvan.springboot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class FoodProduct {
	
	@Id
  @GeneratedValue
  @Column(name="ID")
  private int Id;
	
	
	private String title;
	
	private float price;
	
	private int category;
	
	@Column(name="URL")
	private String imageUrl;
	
	public FoodProduct(){}
	
	public FoodProduct(int id,String title, float price, int category, String imageUrl) {
		super();
		this.Id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imageUrl = imageUrl;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

}
