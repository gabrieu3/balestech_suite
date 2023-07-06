package com.balestech.b3web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.balestech")
public class B3WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(B3WebApplication.class, args);
	}

}
