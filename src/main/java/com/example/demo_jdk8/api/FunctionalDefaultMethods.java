package com.example.demo_jdk8.api;

@FunctionalInterface
public interface FunctionalDefaultMethods {

	void method();
	
	default void defaultMethod() {}
	
}
