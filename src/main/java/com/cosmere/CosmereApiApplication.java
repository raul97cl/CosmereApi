package com.cosmere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CosmereApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmereApiApplication.class, args);
	}

}
