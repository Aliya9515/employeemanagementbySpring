package com.jsp.employe_management.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class EmployeeClone {
	 private int empId;
	 private String firstName;
	 private String lastName;
	 private int age;
	 private String gender;
	 private String phone;
	 private String email;
	 private String dob;
}
