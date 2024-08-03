package com.jsp.empoye_management.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.empoye_management.clone.EmployeeClone;
import com.jsp.empoye_management.entity.Employee;
import com.jsp.empoye_management.service.EmployeeService;
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@Autowired
    ModelMapper mapper;
	
	@PostMapping("/register")
	public EmployeeClone registerEmployee(@RequestBody Employee u) {
		 Employee register = service.SaveEmployee(u);
		 try {
			 EmployeeClone ec=m1(register);
			 return ec;
	}catch(Exception ex) {
		ex.printStackTrace();
	}
		 return null;
}
	@PostMapping("/emp")
	public EmployeeClone m1(@RequestBody Employee emp) {
		EmployeeClone c = mapper.map(emp,EmployeeClone.class);
		return c;
	}
	@PostMapping("/sendHtml")
	
	public String sendHtml(@RequestBody Employee emp) {
		try {
		service.sendHtmlEmail(emp);
			return"message send successfully";
		}catch(Exception e) {
			return "internal error";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

	}
