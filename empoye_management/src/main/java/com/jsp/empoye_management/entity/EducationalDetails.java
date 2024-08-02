package com.jsp.empoye_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class EducationalDetails {
	@Id
	int educatinalId;
	String degree;
	String stream;
	String yop;
	String percentage;
	String univercityName;
	}
