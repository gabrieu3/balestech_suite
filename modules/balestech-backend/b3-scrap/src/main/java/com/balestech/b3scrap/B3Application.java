package com.balestech.b3scrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.balestech")
public class B3Application {

	public static void main(String[] args) {
		SpringApplication.run(B3Application.class, args);
	}

}
