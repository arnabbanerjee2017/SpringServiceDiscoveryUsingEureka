package com.arnab.spring.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.arnab.spring.service.MyService;

@RestController
@RequestMapping(value = "/eureka")
public class MyController {

	@Autowired
	private MyService service;
	
	@GetMapping(value = "/hello1")
	public String getHelloService1() throws RestClientException, URISyntaxException {
		return service.getHelloService1();
	}
	
	@GetMapping(value = "/hello2")
	public String getHelloService2() throws RestClientException, URISyntaxException {
		return service.getHelloService2();
	}
	
}
