package com.bhuvan.springboot.model;

public class Student {
	private String id;
	private String name;
	private String address;	
	private String dob;

	public Student(String id, String name, String address,
			String dob) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dob = dob;
		
		
	}
	public Student() {
		
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

}
