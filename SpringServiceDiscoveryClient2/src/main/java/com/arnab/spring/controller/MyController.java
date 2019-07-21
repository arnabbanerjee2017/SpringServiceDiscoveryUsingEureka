package com.arnab.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnab.spring.service.MyService;

@RestController
@RequestMapping(value = "/eureka")
public class MyController {

	@Autowired
	private MyService service;
	
	@GetMapping(value = "/hello")
	public String getHello() {
		return service.getHello();
	}
	
}
