package com.example.demo_jdk8.dto;

public class B extends A {

	static {
		System.out.println("B static");
	}
	
	public B() {
		System.out.println("B");
	}
	
	public B(String str) {
		super(str);
		System.out.println("B " + str);
	}
	
}
