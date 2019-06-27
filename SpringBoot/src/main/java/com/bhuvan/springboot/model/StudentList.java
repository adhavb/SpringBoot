package com.bhuvan.springboot.model;

import java.util.List;

public class StudentList {
	List<Student> studentList;
	
	public StudentList(List<Student> studentList){
		this.studentList = studentList;
		
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
}
