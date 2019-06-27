package com.bhuvan.springboot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class FoodUser {
	
	@Id
  @GeneratedValue
  @Column(name="ID")
  private int Id;		
	
  private String email;
  
  public FoodUser(){
	  
  }
  
  public FoodUser(int id, String email){
	  this.Id = id;
	  this.email = email;
  }


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
		

}
