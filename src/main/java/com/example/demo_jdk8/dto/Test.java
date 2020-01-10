package com.example.demo_jdk8.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

//import org.assertj.core.util.Lists;

public class Test {
	public static void main(String[] args) {
		String msg = "输入的卡号无效，请确认后输入[1020014]";
		if (msg.contains("[") && msg.contains("]")) {
			String code = msg.substring(msg.indexOf("["), msg.indexOf("]")+1);
			System.out.println(code);
			msg = msg.replace(code, "");
			System.out.println(msg);
		}
		
		new A();
		new B("str");
		
		int result = 0;
		switch (2) {
		case 2:
			result = result + 2*1;
		case 3:
			result = result + 2*2;
		case 4:
			result = result + 2*3;
		default:
			break;
		}
		System.out.println(result);
//		ArrayList<E>
//		LinkedList<E>
//		Vector<E>
//		HashMap<K, V>
//		Hashtable<K, V>
//		HashSet<E>
//		ConcurrentHashMap<K, V>
//		List<String> lists = Lists.newArrayList();
//		lists.forEach(str -> {
//			System.out.println(str);
//		});
	}
}
