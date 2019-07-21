package com.arnab.spring.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

	@Autowired
	private RestTemplate restTemplate;
	
	public String getHelloService1() throws RestClientException, URISyntaxException {
		return restTemplate.getForObject(new URI("http://SPRINGSERVICEDISCOVERYCLIENT1/eureka/hello"), String.class);
	}
	
	public String getHelloService2() throws RestClientException, URISyntaxException {
		return restTemplate.getForObject(new URI("http://SPRINGSERVICEDISCOVERYCLIENT2/eureka/hello"), String.class);
	}
	
}
