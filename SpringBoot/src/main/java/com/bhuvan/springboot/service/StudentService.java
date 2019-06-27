package com.bhuvan.springboot.service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bhuvan.springboot.model.Course;
import com.bhuvan.springboot.model.Course_old;
import com.bhuvan.springboot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class StudentService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static List<Student> students = new ArrayList<>();
	private static List<Course_old> courses = new ArrayList<>();

	static {
		//Initialize Data
		Course_old course1 = new Course_old("Course1", "Spring", "10 Steps", Arrays
				.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course_old course2 = new Course_old("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course_old course3 = new Course_old("Course3", "Spring Boot", "6K Students",
				Arrays.asList("Learn Maven", "Learn Spring",
						"Learn Spring MVC", "First Example", "Second Example"));
		Course_old course4 = new Course_old("Course4", "Maven",
				"Most popular maven course on internet!", Arrays.asList(
						"Pom.xml", "Build Life Cycle", "Parent POM",
						"Importing into Eclipse"));

//		Student ranga = new Student("Student1", "Ranga Karanam",
//				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
//						.asList(course1, course2, course3, course4)));
//
//		Student satish = new Student("Student2", "Satish T",
//				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
//						.asList(course1, course2, course3, course4)));
		
		Student ranga = new Student("Student1", "Ranga Karanam",
				"India","Hiker, Programmer and Architect");

		Student satish = new Student("Student2", "Satish T",
				"India","Hiker, Programmer and Architect");

		students.add(ranga);
		students.add(satish);
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);
	}

	/*public List<Student> retrieveAllStudents() {
		return students;
	}*/
	public List<Student> retrieveAllStudents() {
		String sql = "SELECT id,name,address,DATE_FORMAT(dob, '%d/%m/%Y') dob FROM student";
		   RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
		   return this.jdbcTemplate.query(sql, rowMapper);
	}
	public List<Course_old> retrieveAllCourses() {
		
		String sql = "SELECT GROUP_CONCAT(course_id) FROM course";
		return courses;
	}

	public Student retrieveStudent(String studentId) {
		String sql ="SELECT id,name,address,DATE_FORMAT(dob, '%d/%m/%Y') dob FROM student where id = ?";
		Student student = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws
            SQLException {
              preparedStatement.setString(1, studentId);
          }
        }, new ResultSetExtractor<Student>() {
            public Student extractData(ResultSet resultSet) throws SQLException,
              DataAccessException {
                if (resultSet.next()) {
                    return new Student(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                }
                return new Student();
            }
          }
      );
		return student;
	}
	
	public void registerStudent(Student student){
		String sql = "INSERT INTO student (id, name, description) values (?, ?, ?)";
	    jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAddress());
	    
	}
//	public List<Course> retrieveCourses(String studentId) {
//		Student student = retrieveStudent(studentId);
//		if (student == null) {
//			return null;
//		}
//
//		return student.getCourses();
//	}

//	public Course retrieveCourse(String studentId, String courseId) {
//		Student student = retrieveStudent(studentId);
//
//		if (student == null) {
//			return null;
//		}
//
//		for (Course course : student.getCourses()) {
//			if (course.getId().equals(courseId)) {
//				return course;
//			}
//		}
//
//		return null;
//	}

//	private SecureRandom random = new SecureRandom();
//
//	public Course addCourse(String studentId, Course course) {
//		Student student = retrieveStudent(studentId);
//
//		if (student == null) {
//			return null;
//		}
//
//		String randomId = new BigInteger(130, random).toString(32);
//		course.setId(randomId);
//
//		student.getCourses().add(course);
//
//		return course;
//	}
}
