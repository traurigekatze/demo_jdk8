package com.example.demo_jdk8.xml2bean;

import javax.xml.bind.annotation.XmlElement;

public class Student {
	
	private String name;
	
	private String sex;
	
	private int age;
	
	private int number;

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@XmlElement(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement(name = "number")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public Student() {
		
	}

	public Student(String name, String sex, int age, int number) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.number = number;
	}
	
}
