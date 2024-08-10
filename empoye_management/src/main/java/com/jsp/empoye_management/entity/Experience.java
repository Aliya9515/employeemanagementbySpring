package com.jsp.empoye_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ExperinceId;
	private String companyName;
	private String yearOfExp;
	private String monthOfExp;
	private String designation;
}
