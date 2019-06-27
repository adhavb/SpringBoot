package com.bhuvan.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

@Entity
public class Customers extends ResourceSupport{
  @Id
  @GeneratedValue
  @Column(name="ID")
  private int customerId;
  
  @NotNull(message = "Name cannot be null")
  @NotBlank(message = "Name cannot be blank")
  @Size(min=2, message="Name should have atleast 2 characters")
  private String name;
  
  private String passportNumber;
  
  public Customers() {
    super();
  }

  public Customers(int customerId, String name, String passportNumber) {
    super();
    this.customerId = customerId;
    this.name = name;
    this.passportNumber = passportNumber;
  }
 
  public int getCustomerId() {
    return customerId;
  }
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassportNumber() {
    return passportNumber;
  }
  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }
    
}
