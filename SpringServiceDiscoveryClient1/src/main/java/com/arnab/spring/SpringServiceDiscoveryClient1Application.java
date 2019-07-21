package com.arnab.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringServiceDiscoveryClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringServiceDiscoveryClient1Application.class, args);
	}

}
