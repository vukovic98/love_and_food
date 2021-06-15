package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.model.Alarm;
import com.ftn.uns.ac.rs.love_and_food.service.AlarmService;
import com.ftn.uns.ac.rs.love_and_food.util.PageImplMapper;
import com.ftn.uns.ac.rs.love_and_food.util.PageImplementation;

@RestController
@RequestMapping(path = "/alarm")
public class AlarmController {

	@Autowired
	private AlarmService alarmService;
	
	@GetMapping(path = "/by-page/{pageNum}")
	public ResponseEntity<Page<Alarm>> findAllByPage(@PathVariable("pageNum") int pageNum) {
		Page<Alarm> alarmPage = this.alarmService.findAllByPage(pageNum);
		
		return new ResponseEntity<>(alarmPage, HttpStatus.OK);
	}
	
}
