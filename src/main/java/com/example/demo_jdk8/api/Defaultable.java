package com.example.demo_jdk8.api;

public interface Defaultable {

	default String notRequired() {
		return "Default implementation";
	}
	
}
