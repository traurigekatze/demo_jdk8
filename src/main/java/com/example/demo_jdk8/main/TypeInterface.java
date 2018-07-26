package com.example.demo_jdk8.main;

import com.example.demo_jdk8.api.Value;

public class TypeInterface {
	
	public static void main(String[] args) {
		final Value<String> valueStr = new Value<>();
		System.out.println(valueStr.getOrDefault("22", Value.defaultValue()));
		
		final Value<Integer> valueInt = new Value<>();
		System.out.println(valueInt.getOrDefault(111, Value.defaultValue()));
	}

}
