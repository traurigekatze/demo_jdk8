package com.example.demo_jdk8.xml2bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "students")
public class StudentList {

	private static List<Student> lists;
	
	static {
		lists = new ArrayList<>();
	}

	@XmlElement(name = "student")
	public List<Student> getLists() {
		return lists;
	}

}
