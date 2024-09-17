package com.FoodAid.Group35;

import com.FoodAid.Group35.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class Group35Application {

	public static void main(String[] args) {
		SpringApplication.run(Group35Application.class, args);
	}

}
