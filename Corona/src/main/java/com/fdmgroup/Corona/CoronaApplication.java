package com.fdmgroup.Corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CoronaApplication {

	
	
	// dummy change 
	// more dummy changes
	// yet another dummy change


	
	//Mark was here
	// Hello everybody!! How are you doing today?
	// Timi is also here..
	
	
	

	//paul edited


	//Wendy was here
	
	// Kevin is here
	

	

@EnableJpaRepositories(basePackages = "your.repositories.package")
@EntityScan(basePackages = "your.entities.package")

public class CoronaApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CoronaApplication.class, args);
	}

}
