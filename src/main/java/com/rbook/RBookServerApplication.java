package com.rbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({ "com.rbook.*" })
public class RBookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RBookServerApplication.class, args);
	}

}
