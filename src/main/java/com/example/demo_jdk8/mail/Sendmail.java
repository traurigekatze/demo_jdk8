package com.example.demo_jdk8.mail;

import java.util.Properties;

import org.springframework.boot.web.servlet.server.Session;

public class Sendmail {
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("mail.host", "traurigekatze@163.com");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		
		//使用JavaMail发送邮件的5个步骤
		//1、创建session
//		Session
//		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
//		session.setDebug(true);
//		//2、通过session得到transport对象
//		Transport ts = session.getTransport();
//		//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
//		ts.connect("smtp.sohu.com", "gacl", "邮箱密码");
//		//4、创建邮件
//		Message message = createSimpleMail(session);
//		//5、发送邮件
//		ts.sendMessage(message, message.getAllRecipients());
//		ts.close();
		
	}
	
}
