package com.grapeup.quizservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient // it will register this service with eureka
@RestController
public class QuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}

	@Value("${some.value}")
	private String value;

	@Value("${some.other}")
	private String other;

	@GetMapping(path = "/quiz/demo")
	public String getProp() {
		return value + " " + other;
	}
}
