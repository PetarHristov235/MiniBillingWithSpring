package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;


@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		ConfigurableApplicationContext check= SpringApplication.run(DemoApplication.class,args);
		UserRepository da=check.getBean(UserRepository.class);
		List<User> userList=da.findAll();
		System.out.println(userList);
	}

}
