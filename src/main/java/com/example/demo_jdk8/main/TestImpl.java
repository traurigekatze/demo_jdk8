package com.example.demo_jdk8.main;

import com.example.demo_jdk8.api.DefaultableFactory;
import com.example.demo_jdk8.api.Defaultable;
import com.example.demo_jdk8.service.DefaultableImpl;
import com.example.demo_jdk8.service.OverrideableImpl;

public class TestImpl {
	public static void main(String[] args) {
		
		Defaultable defaulable = DefaultableFactory.create( DefaultableImpl::new );
	    System.out.println( defaulable.notRequired() );
	         
	    defaulable = DefaultableFactory.create( OverrideableImpl::new );
	    System.out.println( defaulable.notRequired() );
		
	}
}
