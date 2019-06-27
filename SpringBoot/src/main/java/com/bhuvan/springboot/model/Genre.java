package com.bhuvan.springboot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Genre {
	
	@Id
	  @GeneratedValue
	  @Column(name="ID")
	  private int Id;
		
	  private String name;

	  public Genre(){
		  
	  }
	  public Genre(int id, String name){
		  this.Id = id;
		  this.name = name;
	  }
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
