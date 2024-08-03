package com.jsp.empoye_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Experience {
	@Id
	private int ExperinceId;
	private String companyName;
	private String yearOfExp;
	private String monthOfExp;
	private String designation;
}
