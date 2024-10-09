package com.example.RabbitMq_Task_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitMqTaskProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqTaskProjectApplication.class, args);
	}

}
