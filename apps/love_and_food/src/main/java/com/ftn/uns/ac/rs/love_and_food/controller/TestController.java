package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.facts.Item;
import com.ftn.uns.ac.rs.love_and_food.service.TestService;

@RestController
@RequestMapping(path = "/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping
	public ResponseEntity<Item> test(@RequestBody Item item) {
		
		Item freshItem = new Item(item.getId(), item.getName(), item.getCost(), item.getSalePrice());
		
		Item newItem = this.testService.getClassifiedItem(freshItem);
		
		return new ResponseEntity<>(newItem, HttpStatus.OK);
	}
	
}
