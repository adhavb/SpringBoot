package com.bhuvan.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhuvan.springboot.model.FoodCategory;
import com.bhuvan.springboot.model.FoodProduct;
import com.bhuvan.springboot.service.FoodService;

@RestController
@RequestMapping("/product")
public class FoodProductController {
	
	@Autowired
	FoodService foodService;
	
	@GetMapping("/categories")
	  public ResponseEntity<FoodCategory> getCategories() {
		return new ResponseEntity(foodService.getCategories(),HttpStatus.OK);
	  }
	@GetMapping("/getall")
	  public ResponseEntity<FoodProduct> getProducts() {
		return new ResponseEntity(foodService.getProducts(),HttpStatus.OK);
	  }
	
	@GetMapping("/get/{id}")
	  public ResponseEntity<FoodProduct> getProduct(@PathVariable int id) {
		return new ResponseEntity(foodService.getProduct(id),HttpStatus.OK);
	  }
	
	@PutMapping("/update/{id}")
	  public ResponseEntity<FoodProduct> updateProduct(@PathVariable int id, @RequestBody FoodProduct foodProduct) {
		return new ResponseEntity(foodService.updateProduct(foodProduct, id),HttpStatus.OK);
	  }
	
	@DeleteMapping("/delete/{id}")
	  public ResponseEntity<FoodProduct> deleteProduct(@PathVariable int id) {
		System.out.println("going to delete "+id);
		return new ResponseEntity(foodService.deleteProduct(id),HttpStatus.OK);
	  }
	
	@PostMapping("/save")
	  public ResponseEntity<FoodProduct> createProduct(@RequestBody FoodProduct product) {
		return new ResponseEntity(foodService.saveProduct(product),HttpStatus.OK);

	  }

}
