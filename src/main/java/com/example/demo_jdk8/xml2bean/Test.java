package com.example.demo_jdk8.xml2bean;

import javax.xml.bind.JAXBException;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
	public static void main(String[] args) {
		StudentList list = new StudentList();
		Student student1 = new Student("张强", "男", 18, 18);
		Student student2 = new Student("光强", "男", 19, 19);
		list.getLists().add(student1);
		list.getLists().add(student2);
		
		String xmlStr = "";
		
		try {
			xmlStr = XMLUtils.beanToXml(list, StudentList.class);
			log.info("beanToXml={}", xmlStr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		try {
			StudentList object = XMLUtils.xmlToBean(xmlStr, StudentList.class);
			log.info("xmlToBean={}", JSON.toJSONString(object));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
