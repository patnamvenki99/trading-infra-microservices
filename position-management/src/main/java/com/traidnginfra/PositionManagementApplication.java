package com.traidnginfra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PositionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionManagementApplication.class, args);
	}

}
