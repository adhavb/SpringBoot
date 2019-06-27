package com.bhuvan.springboot.client;


import org.springframework.web.client.RestTemplate;

import com.bhuvan.springboot.model.Student;
import com.bhuvan.springboot.model.StudentList;

public class StudentClient {
	
	private static void main(String a[])
	{
	    final String uri = "http://localhost:8080/students";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    StudentList result = restTemplate.getForObject(uri, StudentList.class);      
	     
	    for(Student values: result.getStudentList())
	    {
	    	System.out.println(values.getName());	    
	     
	    }
	    
//	    String payload = "data={" +
//                "\"username\": \"admin\", " +
//                "\"first_name\": \"System\", " +
//                "\"last_name\": \"Administrator\"" +
//                "}";
//        StringEntity entity = new StringEntity(payload,
//                ContentType.APPLICATION_FORM_URLENCODED);
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost request = new HttpPost("http://localhost:8080/register");
//        request.setEntity(entity);
//
//        HttpResponse response = httpClient.execute(request);
//        System.out.println(response.getStatusLine().getStatusCode());
	}

}


