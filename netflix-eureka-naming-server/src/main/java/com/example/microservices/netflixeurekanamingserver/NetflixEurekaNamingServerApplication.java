package com.example.microservices.netflixeurekanamingserver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaNamingServerApplication {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(NetflixEurekaNamingServerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaNamingServerApplication.class, args);
		LOGGER.info("Eureka");
		
		
	}
	
	
	
}
