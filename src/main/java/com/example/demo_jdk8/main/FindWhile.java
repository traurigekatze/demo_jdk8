package com.example.demo_jdk8.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindWhile {

	public static void main(String[] args) {
		List<Integer> lists = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int num = (int) (1 + Math.random()*10);
			lists.add(num);
		}
		Collections.sort(lists);
		int target = 5;
		int index = getIndex(lists, target);
		System.out.println("target index = " + index);
	}
	
	private static int getIndex(List<Integer> lists, int target) {
		int base = 0;
		int top = lists.size() - 1;
		lists.set(3, 5);
		lists.forEach(a -> {System.out.println(a);});
		while (base <= top) {
			int mid = (top + base) / 2;
			if (lists.get(mid) == target) {
				return mid;
			} else if (lists.get(mid) > target) {
				top = mid - 1;
			} else if (lists.get(mid) < target) {
				base = mid + 1;
			}
		}
		return -1;
	}

}
