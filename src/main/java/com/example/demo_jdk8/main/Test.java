package com.example.demo_jdk8.main;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Arrays.asList("a", "b", "c").forEach(e -> System.out.print(e));
		
		System.out.println("\n---------------------------");
		
		Arrays.asList("1", "2", "3").forEach((String e) -> System.out.print(e));
		
		System.out.println("\n---------------------------");
		
		Arrays.asList( "1", "2", "3" ).forEach( e -> {
		    System.out.print( Integer.parseInt(e) + 1 );
		} );
		
		System.out.println("\n---------------------------");
		
		String str = "abc-";
		Arrays.asList("a", "b", "c").forEach(e -> System.out.print(e + str));
		
		System.out.println("\n---------------------------");
		
		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
		
	}
}
