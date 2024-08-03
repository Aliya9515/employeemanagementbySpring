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
 private int empId;
 private String firstName;
 private String lastName;
 private int age;
 private String gender;
 private String phone;
//@Column(unique=true)
 private String email;
 private String pwd;
 private String dob;
@Lob
@Column(columnDefinition = "LONGBLOB",length= Integer.MAX_VALUE)
 private byte[]image;
@OneToMany(cascade=CascadeType.ALL)
 private List<Experience> experience;
@OneToMany(cascade=CascadeType.ALL)
 private List<EducationalDetails> eduDetails;
}
