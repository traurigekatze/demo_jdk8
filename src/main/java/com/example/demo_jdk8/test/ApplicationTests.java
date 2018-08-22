package com.example.demo_jdk8.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo_jdk8.DemoJdk8Application;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoJdk8Application.class)
public class ApplicationTests {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Test
	public void sendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("hekai@jshuii.com");
		message.setTo("traurigekatze@163.com");
		message.setSubject("主题：测试邮件");
		message.setText("test mail send content");
		
		mailSender.send(message);
	}
	
	@Test
	public void sendAttachmentsMail() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("hekai@jshuii.com");
		helper.setTo("traurigekatze@163.com");
		helper.setSubject("主题：有附件的测试邮件");
		helper.setText("test mail send content have attachment");
		buildExcel(); // 写入excel
		FileSystemResource file = new FileSystemResource(new File("C:\\File_study\\aa\\test_file\\doc.xls"));
		helper.addAttachment("附件-doc.xls", file);

		mailSender.send(mimeMessage);
	} 
	
	@SuppressWarnings("resource")
	private void buildExcel() {
		HSSFWorkbook workbook = new HSSFWorkbook();
        //第二步，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("测试邮件附件");
        //第三步，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("用户名");
        cell = row.createCell(1);
        cell.setCellValue("密码");
        cell=row.createCell(2);
        cell.setCellValue("备注");
        //第五步，写入实体数据
        for (int i = 0; i < 5; i++) {
            HSSFRow row1 = sheet.createRow(i+1);
            //创建单元格设值
            row1.createCell(0).setCellValue("userName：" + i);
            row1.createCell(1).setCellValue("userPwd：" + i);
            row1.createCell(2).setCellValue("userRemark：" + i);
        }
        //将文件保存到指定的位置
        File file = new File("C:/File_study/aa/test_file");
        if (file.exists()) {
			log.info("文件夹存在");
		} else {
			log.info("文件夹不存在");
			file.mkdirs();
		}
        try {
            FileOutputStream fos = new FileOutputStream(file.getPath()+"/doc.xls");
            workbook.write(fos);
            System.out.println("恭喜您！写入成功！！！！！！");
            fos.close();
        } catch (IOException e) {
        	System.out.println("写入文件出错啦！");
            e.printStackTrace();
        }
	}

}
