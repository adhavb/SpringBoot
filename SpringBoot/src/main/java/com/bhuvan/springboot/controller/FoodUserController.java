package com.bhuvan.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhuvan.springboot.model.FoodCategory;
import com.bhuvan.springboot.model.FoodOrder;
import com.bhuvan.springboot.model.FoodProduct;
import com.bhuvan.springboot.model.FoodUser;
import com.bhuvan.springboot.service.FoodService;

@Controller
@RequestMapping("/user")
public class FoodUserController {
	
	@Autowired
	FoodService foodService;
	
	@GetMapping("/getall")
	  public ResponseEntity<FoodProduct> getOrders() {
		return new ResponseEntity(foodService.getProducts(),HttpStatus.OK);
	  }
	
	@GetMapping("/get/{id}")
	  public ResponseEntity<FoodProduct> getOrder(@PathVariable int id) {
		return new ResponseEntity(foodService.getProduct(id),HttpStatus.OK);
	  }
	
	/*@PutMapping("/update/{id}")
	  public ResponseEntity<FoodProduct> updateOrder(@PathVariable int id, @RequestBody FoodOrder foodOrder) {
		return new ResponseEntity(foodService.updateProduct(foodOrder, id),HttpStatus.OK);
	  }*/
	
	@DeleteMapping("/delete/{id}")
	  public ResponseEntity<FoodProduct> deleteUser(@PathVariable int id) {
		System.out.println("going to delete "+id);
		return new ResponseEntity(foodService.deleteProduct(id),HttpStatus.OK);
	  }
	
	@PostMapping("/save")
	  public ResponseEntity<FoodUser> saveUser(@RequestBody FoodUser user) {
		return new ResponseEntity(foodService.saveUser(user),HttpStatus.OK);

	  }


}
