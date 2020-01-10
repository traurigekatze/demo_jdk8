package com.example.demo_jdk8.dto;

public class A {

	public A() {
		System.out.println("A");
	}
	
	public A(String str) {
		System.out.println("A " + str);
	}
	
	static {
		System.out.println("A static");
	}
	
}
