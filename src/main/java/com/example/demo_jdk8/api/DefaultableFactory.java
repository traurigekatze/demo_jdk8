package com.example.demo_jdk8.api;

import java.util.function.Supplier;

public interface DefaultableFactory {

	static Defaultable create( Supplier< Defaultable > supplier ) {
        return supplier.get();
    }
	
}
