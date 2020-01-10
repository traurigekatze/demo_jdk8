package com.example.demo_jdk8.xml2bean;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XMLUtils {

	/**
	 * bean 2 xml
	 * @param object
	 * @param load
	 * @return
	 * @throws JAXBException
	 */
	public static String beanToXml(Object object, Class<?> load) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(load);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		StringWriter writer = new StringWriter();
		marshaller.marshal(object, writer);
		return writer.toString();
	}
	
	/**
	 * xml 2 bean
	 * @param xmlStr
	 * @param load
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <V> V xmlToBean(String xmlStr, Class<?> load) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(load);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object object = unmarshaller.unmarshal(new StringReader(xmlStr));
//		Object object = unmarshaller.unmarshal(new File(xmlPath));
		return (V) object;
	}
	
}
