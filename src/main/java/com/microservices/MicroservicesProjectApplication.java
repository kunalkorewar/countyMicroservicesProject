package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com")
@EnableScheduling
public class MicroservicesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesProjectApplication.class, args);
		System.out.println("tomcat start..");
	}

}
