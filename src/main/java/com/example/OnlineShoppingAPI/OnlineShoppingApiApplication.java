package com.example.OnlineShoppingAPI;

import com.example.OnlineShoppingAPI.model.Role;
import com.example.OnlineShoppingAPI.model.User;
import com.example.OnlineShoppingAPI.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class OnlineShoppingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(1, "ROLE_USER"));
//			userService.saveRole(new Role(2, "ROLE_MANAGER"));
//			userService.saveRole(new Role(3, "ROLE_ADMIN"));
//
//			userService.saveUser(new User(1, "Henry Arrovin", "Henry", "1234", new ArrayList<>()));
//			userService.saveUser(new User(2, "Arrovin Henry", "Arrovin", "12345", new ArrayList<>()));
//
//			userService.addRoleToUser("Henry", "ROLE_ADMIN");
//			userService.addRoleToUser("Arrovin", "ROLE_USER");
//		};
//	}
}
