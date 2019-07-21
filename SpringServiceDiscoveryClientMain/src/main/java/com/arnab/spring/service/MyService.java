package com.arnab.spring.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public String getHelloService1() throws RestClientException, URISyntaxException {
		return restTemplate.getForObject(new URI("http://SPRINGSERVICEDISCOVERYCLIENT1/eureka/hello"), String.class);
	}
	
	public String getHelloService2() throws RestClientException, URISyntaxException {
		return restTemplate.getForObject(new URI("http://SPRINGSERVICEDISCOVERYCLIENT2/eureka/hello"), String.class);
	}
	
	public String getServices() {
		return "<h4>"
				+ "Description: " + discoveryClient.description()
				+ "<br />Order: " + discoveryClient.getOrder()
				+ "<br />Services: " + discoveryClient.getServices()
				+ "</h4>";
		
	}
	
}
