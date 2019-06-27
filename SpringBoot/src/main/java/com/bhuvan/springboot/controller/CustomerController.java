package com.bhuvan.springboot.controller;



import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bhuvan.springboot.exception.RecordNotFoundException;
import com.bhuvan.springboot.model.Customers;
import com.bhuvan.springboot.service.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;
  
  @GetMapping
  public ResponseEntity<List<Customers>> retrieveAllCustomers() {
    return new ResponseEntity(customerService.findAll(),HttpStatus.OK);
  }
  
  @GetMapping("CustomerByName")
  public ResponseEntity<List<Customers>> retrieveCustomerByName(@RequestParam(value = "name") String name) {
    return new ResponseEntity(customerService.findByName(name),HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Customers> retrieveCustomerById(@PathVariable int id) {
    Customers customers = customerService.findById(id);
   
    if (customers == null)
      throw new RecordNotFoundException("id-" + id);

   
    return new ResponseEntity<Customers>(customers, HttpStatus.OK);
  }


  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable int id) {
	  customerService.deleteById(id);
  }
  
  @PostMapping
  public Customers createCustomer(@Valid @RequestBody Customers customers) {
	  Customers savedCustomer = customerService.save(customers);   
    return savedCustomer;

  }

  @PutMapping("/{id}")
  public Customers updateCustomer(@Valid @RequestBody Customers customers, @PathVariable int id) {

     return customerService.update(customers,id);
  }
}
