package com.bhuvan.springboot.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bhuvan.springboot.model.Customers;

public class CustomerClient {

	public static void main(String a[])
	{
	    final String uri = "http://localhost:8080/customers/1";
	    final String getallCustomers = "http://localhost:8080/customers/";
	    final String postCustomers = "http://localhost:8080/customers";
	    final String putCustomers = "http://localhost:8080/customers/{id}";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    Customers customer = restTemplate.getForObject(uri, Customers.class);      
	    System.out.println(customer.getCustomerId()+" "+customer.getName()+" "+customer.getPassportNumber());
	    
	    ResponseEntity<Customers[]> response = restTemplate.getForEntity(getallCustomers, Customers[].class);
		List<Customers> customerList = Arrays.asList(response.getBody()); 
		for(Customers customerObj:customerList){			
			System.out.println(customerObj.getCustomerId()+" "+customerObj.getName()+" "+customerObj.getPassportNumber());
	    }
		
		 	   
	    //POST Request
	    String payload = "{" +
                "\"name\": \"Abishan\", " +
                "\"passportNumber\": \"G1H2I3J4\"" +
                "}";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("name", "Abishan");
	    params.put("passportNumber", "G1H2I3J4");
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.set("Content-Type", "application/json");
	    HttpEntity <String> httpEntity = new HttpEntity <String> (payload, httpHeaders);
	    //ResponseEntity<Customers> strresponse = restTemplate.postForEntity(postCustomers, httpEntity, Customers.class);	     
	    ResponseEntity<Customers> strresponse = restTemplate.postForEntity(postCustomers, params, Customers.class);
	    customer = strresponse.getBody(); 
	    System.out.println(customer.getCustomerId()+" "+customer.getName()+" "+customer.getPassportNumber());
	    
	    //PUT Request
	    Map<String, String> putparams = new HashMap<String, String>();
	    putparams.put("id", "19");
	    Customers customers = new Customers(19,"Abhisan","G1H2I3J4");
	    restTemplate.put(putCustomers,customers,putparams);	    
	}

}
