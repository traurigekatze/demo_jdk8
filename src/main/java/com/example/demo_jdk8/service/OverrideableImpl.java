package com.example.demo_jdk8.service;

import com.example.demo_jdk8.api.Defaultable;

public class OverrideableImpl implements Defaultable {

	@Override
	public String notRequired() {
		return "Overridden implementation";
	}
	
}
