package com.jsp.empoye_management.controller;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.employe_management.dto.EmployeeClone;
import com.jsp.empoye_management.entity.Employee;
import com.jsp.empoye_management.entity.LoginEmployee;
import com.jsp.empoye_management.service.EmployeeService;
import com.jsp.empoye_management.util.ResponseStructure;
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@Autowired
	ModelMapper mapper;

	@PostMapping("/save")

	public EmployeeClone register(@RequestBody Employee e) {
		Employee register = service.SaveEmployee(e);
		try {
			EmployeeClone ec = m1(register);
			return ec;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	@PostMapping("/emp")
	private EmployeeClone m1(@RequestBody Employee emp) {
		EmployeeClone c = mapper.map(emp, EmployeeClone.class);
		return c;
	}

	@PostMapping("/sendhtml")
	public String sendHtml(@RequestBody Employee emp) {
		try {
			service.sendHtmlEmail(emp);
			return "msg send successfully";
		} catch (Exception e) {
			return "internal error";
		}
	}

	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Employee>> findById(@RequestParam int id) {
		return service.fetchEmployeeDetails(id);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<Employee>> delete(@RequestParam int id) {
		return service.delete(id);
	}
	@PutMapping("/update")
    public ResponseEntity<ResponseStructure<Employee>> update(@RequestBody Employee employeeDetails) {
		return service.updateEmployee(employeeDetails);
        
    }
	@GetMapping("/login")
    public ResponseEntity<ResponseStructure<Employee>> login(@RequestBody LoginEmployee loginRequest) {
        return service.loginEmployee(loginRequest);
    }
	 @PostMapping("/image")
	 public ResponseEntity<ResponseStructure<EmployeeClone>> saveImage(@RequestParam int id, @RequestParam MultipartFile file)
	   throws IOException {
	  return service.saveImageById(id,file);
	 }
	 @GetMapping("/fetchImage")
	 public ResponseEntity<byte[]> findById1(@RequestParam int id) {
	  return service.fetchImage(id);
	 }
	}
