package com.arnab.spring.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

	public String getHello() {
		return "Hello, Eureka is awesome! From Client 2.";
	}
	
}
