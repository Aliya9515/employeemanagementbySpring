package com.jsp.empoye_management.service;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsp.empoye_management.dao.EmployeeDao;
import com.jsp.empoye_management.entity.Employee;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmployeeService {
	@Autowired
	EmployeeDao dao;
	@Autowired
	JavaMailSender mailSender;
	
	public Employee SaveEmployee( Employee u) {
		Employee rs = dao.saveEmployee(u);
		try {
	    sendHtmlEmail(u);
		}catch(MessagingException ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	public void sendHtmlEmail(Employee u) throws AddressException, MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		message.setFrom(new InternetAddress("shaikaliya996@gmail.com"));
	    message.setRecipients(MimeMessage.RecipientType.TO,u.getEmail());
	    message.setSubject("Test email from Spring");

	    String htmlContent = "<img src='cid:logoImage' alt='Company Logo' style='width:100px;height:100px;'><br>" +
	    		             "<h1>Hi "+u.getFirstName()+" "+u.getLastName()+",</h1>" +
	                         "we have recived you are application and appriciate the time you took to apply "+
	    		             "our team will review your qualification and get back to you if your profiles matches our reqirements"+
	                         "thank you once again for considering a carrer with us.";
	    message.setContent(htmlContent, "text/html; charset=utf-8");
	    //Add the image as an inline resource
	    FileSystemResource file = new FileSystemResource(new File("C:\\Users\\shaik_2tsb0bn\\Downloads\\lotus-8369252_640.webp"));
	    helper.addInline("logoImage", file);
	    mailSender.send(message);
	}
}
