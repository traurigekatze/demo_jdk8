package com.example.demo_jdk8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DemoJdk8Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoJdk8Application.class, args);
	}
}
