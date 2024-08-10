package com.jsp.empoye_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class EducationalDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int educatinalId;
	private String degree;
	private String stream;
	private String yop;
	private String percentage;
	private String univercityName;
	}
