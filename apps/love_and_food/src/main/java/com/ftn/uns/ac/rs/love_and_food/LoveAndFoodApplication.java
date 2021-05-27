package com.ftn.uns.ac.rs.love_and_food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LoveAndFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoveAndFoodApplication.class, args);
	}
}
