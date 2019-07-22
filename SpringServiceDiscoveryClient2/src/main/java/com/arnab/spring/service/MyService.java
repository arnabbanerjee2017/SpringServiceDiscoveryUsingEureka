/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

	public String getHello() {
		return "Hello, Eureka is awesome! From Client 2.";
	}
	
}
