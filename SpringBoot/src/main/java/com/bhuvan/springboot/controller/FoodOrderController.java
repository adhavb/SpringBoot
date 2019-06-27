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
import com.bhuvan.springboot.model.ResponseStatus;
import com.bhuvan.springboot.service.FoodService;

@Controller

public class FoodOrderController {
	
	@Autowired
	FoodService foodService;
	
	@GetMapping("/order/getall")
	  public ResponseEntity<FoodProduct> getOrders() {
		return new ResponseEntity(foodService.getProducts(),HttpStatus.OK);
	  }
	
	@GetMapping("/order/get/{id}")
	  public ResponseEntity<FoodOrder> getOrder(@PathVariable String id) {
		return new ResponseEntity(foodService.getOrder(id),HttpStatus.OK);
	  }
	
	/*@PutMapping("/update/{id}")
	  public ResponseEntity<FoodProduct> updateOrder(@PathVariable int id, @RequestBody FoodOrder foodOrder) {
		return new ResponseEntity(foodService.updateProduct(foodOrder, id),HttpStatus.OK);
	  }*/
	
	@DeleteMapping("/order/delete/{id}")
	  public ResponseEntity<FoodProduct> deleteOrder(@PathVariable int id) {
		System.out.println("going to delete "+id);
		return new ResponseEntity(foodService.deleteProduct(id),HttpStatus.OK);
	  }
	
	@PostMapping("/cart/save")
	  public ResponseEntity<ResponseStatus> createOrder(@RequestBody FoodOrder order) {
		ResponseStatus status = new ResponseStatus();
		status.setErrorCode(0);
		status.setErrorMessage("Success");
		if(foodService.saveCart(order))
			return new ResponseEntity(status,HttpStatus.OK);
		status.setErrorCode(1);
		status.setErrorMessage("Failed");
		return new ResponseEntity(status,HttpStatus.EXPECTATION_FAILED);

	  }
	@PutMapping("/cart/update/{id}/{quantity}")
	public ResponseEntity<ResponseStatus> updateOrder(@PathVariable String id, @PathVariable String quantity,@RequestBody FoodOrder order) {
		ResponseStatus status = new ResponseStatus();
		status.setErrorCode(0);
		status.setErrorMessage("Success");
		if(foodService.updateCart(quantity, id))
			return new ResponseEntity(status,HttpStatus.OK);
		status.setErrorCode(1);
		status.setErrorMessage("Failed");
		return new ResponseEntity(status,HttpStatus.EXPECTATION_FAILED);
	  }
	
	@DeleteMapping("/cart/delete/{id}")
	  public ResponseEntity<FoodProduct> deleteCart(@PathVariable int id) {
		
		return new ResponseEntity(foodService.deleteCart(id),HttpStatus.OK);
	  }
	
	@DeleteMapping("/cart/clear/{id}")
	  public ResponseEntity<FoodProduct> clearCart(@PathVariable("id") String userId) {
		
		return new ResponseEntity(foodService.clearCart(userId),HttpStatus.OK);
	  }
	
	@PostMapping("/cart/anonymous/save")
	  public void createAnonymousCart(@RequestBody FoodOrder order) {
		foodService.saveCart(order);

	  }
	
	


}
