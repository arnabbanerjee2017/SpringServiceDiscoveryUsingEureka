# SpringServiceDiscoveryUsingEureka
---------------------------------------------------------------------------------------------------------
			NETFLIX EUREKA TUTORIAL
---------------------------------------------------------------------------------------------------------
Netflix Eureka is Load Balancing / Service Discovery tool.

It acts a client-side service discovery tool.

In Eureka, every app is registered as a Eureka-Client to a Eureka Server. In fact the Eureka Server also registers itself to itself as a Eureka-Client.

1. The First Step.
-> Create a Eureka Server application with the required dependencies. Shown below -
	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>Greenwich.SR2</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
-> Then add the below properties to the application.properties file.
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
	(*) The server port is 8761. This is the default Eureka Server port. If you don't specify Eureka will select this as default. But you might see some errors/warnings. To avoid this, it is a better practice to add the property manually.
-> Then add the @EnableEurekaServer annotation to the top on the main bootstrap class. Shown as below.
@SpringBootApplication
@EnableEurekaServer
public class SpringServiceDiscoveryServerApplication {
	// ....
}
-> Then start the application as normal Spring Boot application.
-> Then go to the browser and hit http://localhost:8761/ and you can see the Eureka server is running.

2. The Second Step.
-> Create a Eureka Client application with the required dependencies. Show as below.
	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>Greenwich.SR2</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
-> Set the application name and the server port in application.properties file. Shown as below.
spring.application.name=SpringServiceDiscoveryClient1
server.port=8081
-> Then add the @EnableEurekaClient annotation to the top of main bootstrap class. Shown as below.
@SpringBootApplication
@EnableEurekaClient
public class SpringServiceDiscoveryClient1Application {
	// ...
}
-> Then start the application as normal Spring Boot application.
-> Then go to the browser and hit http://localhost:8761/ and you can see this application is registered with the Eureka server.

3. The Third Step
-> Same as step two. Create as many applications as you need. Just follow the instruction in step 2. But don't forget to modify the server.port property in the application.properties file if you are running all the applications on a same system. 

4. The Fourth Step.
-> Now create a Client application which will invoke the REST APIs registered to Eureka. Remember, to discover service, this service also needs to be registered with Eureka Server as client. So below are the dependencies.
	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>Greenwich.SR2</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
-> Set the application name and server port in the application.properties file. Shown as below.
server.port=8082
spring.application.name=SpringServiceDiscoveryClientMain
-> Now create a config class show as below and create a RestTemplate bean as shown below. Just add the @LoadBalanced annotation above the bean as shown below. It will make the rest template call the service discovery and resolute the actual service URL using the registered app name. See below points.
@Configuration
public class MyConfig {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
-> Here in the Service class, you can call the REST APIs using the RestTemplate object created above. Just pass the application name registered with the Eureka Server, instead of the localhost:<port>. You can find the app name by going http://localhost:8761/.
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
-> You just hit this application's controller and you can see the response is coming from the registered applications.


Another thing - 
When you want to view the details of the service discovery, just create an instance of DiscoveryClient with @Autowired annotation. And check the methods inside that object to get the details about the service discovery, like the list of services, the order of services, the description, etc.

Also every Eureka Client applications send heart-beat to the Eureka Server after some time intervals. This indicate that the clients are still alive and if a client stops sending heart-beat, that means the client is dead and no longer available at that address or URL. Then the Eureka server de-register the service.