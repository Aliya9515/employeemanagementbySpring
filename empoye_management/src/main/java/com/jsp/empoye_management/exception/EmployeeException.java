package com.jsp.empoye_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.empoye_management.util.ResponseStructure;

@RestControllerAdvice
public class EmployeeException {

	public ResponseEntity<ResponseStructure<String>> userNotFound(
			IdNotFound e) {

		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStateCode(HttpStatus.NOT_FOUND.value());
		rs.setData(e.getMessage());
		rs.setMessage("id not found .pls try again with new data");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}
	public ResponseEntity<ResponseStructure<String>> userNotFound2(
		EmailNotFound e) {

		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStateCode(HttpStatus.NOT_FOUND.value());
		rs.setData(e.getMsg());
		rs.setMessage("email not found .pls try again with new data");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}
	public ResponseEntity<ResponseStructure<String>> userNotFound3(
			PwdNotFound e) {

		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStateCode(HttpStatus.NOT_FOUND.value());
		rs.setData(e.getMessage());
		rs.setMessage("pwd not found .pls try again with new data");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}
	
	

}