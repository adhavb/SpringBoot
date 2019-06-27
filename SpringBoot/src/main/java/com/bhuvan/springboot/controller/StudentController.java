package com.bhuvan.springboot.controller;

import java.net.URI;
import java.util.List;

import com.bhuvan.springboot.model.Course;
import com.bhuvan.springboot.model.Course_old;
import com.bhuvan.springboot.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bhuvan.springboot.service.StudentService;


@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

//	@GetMapping("/students/{studentId}/courses")
//	public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
//		return studentService.retrieveCourses(studentId);
//	}
//	
//	@GetMapping("/students/{studentId}/courses/{courseId}")
//	public Course retrieveDetailsForCourse(@PathVariable String studentId,
//			@PathVariable String courseId) {
//		return studentService.retrieveCourse(studentId, courseId);
//	}
	
	@GetMapping("/students/courses")
	public List<Course_old> retrieveCourses() {
		return studentService.retrieveAllCourses();
	}
	
	@GetMapping("/students")
	public List<Student> retrieveStudents() {
		return studentService.retrieveAllStudents();
	}
	
	@GetMapping("/students/{studentId}")
	public Student retrieveStudent(@PathVariable String studentId) {
		return studentService.retrieveStudent(studentId);
	}
	
	@PostMapping("/students/registerstudent")
	public void registerStudent(@RequestBody Student student) {
		 studentService.registerStudent(student);
		
	}
	
//	@PostMapping("/students/{studentId}/courses")
//	public ResponseEntity<Void> registerStudentForCourse(
//			@PathVariable String studentId, @RequestBody Course newCourse) {
//
//		Course course = studentService.addCourse(studentId, newCourse);
//
//		if (course == null)
//			return ResponseEntity.noContent().build();
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
//				"/{id}").buildAndExpand(course.getId()).toUri();
//
//		return ResponseEntity.created(location).build();
//	}

}