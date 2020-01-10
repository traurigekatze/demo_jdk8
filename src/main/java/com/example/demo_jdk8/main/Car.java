package com.example.demo_jdk8.main;

import java.util.List;
import java.util.function.Supplier;

//import org.assertj.core.util.Lists;

public class Car {
	
	public static Car create(final Supplier<Car> supplier) {
		return supplier.get();
	}
	
	public static void collide( final Car car ) {
        System.out.println( "Collided " + car.toString() );
    }
         
    public void follow( final Car another ) {
        System.out.println( "Following the " + another.toString() );
    }
	
	public void repair() {
		System.out.println( "Repaired " + this.toString() );
	}
	
	final Car police = Car.create( Car::new );
//	List<Car> cars = Lists.newArrayList();
//	cars.forEach( police::follow );
	
}
