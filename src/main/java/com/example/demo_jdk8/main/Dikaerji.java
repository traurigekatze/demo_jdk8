package com.example.demo_jdk8.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Dikaerji {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);

		List<String> list2 = new ArrayList<>();
		list2.add("+");
		list2.add("-");
		
		List<String> list3 = new ArrayList<>();
		list3.add("a");
		list3.add("b");
		
		List<List> list = new ArrayList<>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		List result = dikaerji(list);
		System.out.println(result.size());  
        for (Object object : result) {  
            System.out.println(object);  
        }
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static List dikaerji(List list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List l1 = (List) list.get(0);
		List result = new ArrayList<>();
		for (int i = 1; i < list.size(); i++) {
			List li = (List) list.get(i);
			List<Object> temp = new ArrayList<>();
			for (int j = 0; j < l1.size(); j++) {
				for (int j2 = 0; j2 < li.size(); j2++) {
					List<Object> cut = new ArrayList<>();
					if (l1.get(j) instanceof List) {
						cut.addAll((Collection<? extends Object>) l1.get(j));
					} else {
						cut.add(l1.get(j));	
					}
					if (li.get(j2) instanceof List) {
						cut.addAll((Collection<? extends Object>) li.get(j2));
					} else {
						cut.add(li.get(j2));
					}
					temp.add(cut);
				}
			}
			l1 = temp;
			if (i == list.size() - 1) {
				result = temp;
			}
		}
		return result;
	}
		
}
