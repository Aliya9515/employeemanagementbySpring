package com.jsp.empoye_management.entity;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
public class Employee {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
String fName;
String lName;
int age;
String gender;
String phone;
String email;
String pwd;
String dob;
@Lob
@Column(columnDefinition = "LONGBLOB",length= Integer.MAX_VALUE)
byte[]image;
@OneToMany
List<Experience> experience;
@OneToMany
List<EducationalDetails> eduDetails;
}
