package com.jsp.empoye_management.entity;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data
public class Employee {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
int empId;
String fName;
String lName;
int age;
String gender;
String phone;
@Column(unique=true)
String email;
String pwd;
String dob;
@Lob
@Column(columnDefinition = "LONGBLOB",length= Integer.MAX_VALUE)
byte[]image;
@OneToMany(cascade=CascadeType.ALL)
List<Experience> experience;
@OneToMany(cascade=CascadeType.ALL)
List<EducationalDetails> eduDetails;
}
