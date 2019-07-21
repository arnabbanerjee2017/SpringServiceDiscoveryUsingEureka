package com.arnab.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringServiceDiscoveryClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringServiceDiscoveryClient2Application.class, args);
	}

}
