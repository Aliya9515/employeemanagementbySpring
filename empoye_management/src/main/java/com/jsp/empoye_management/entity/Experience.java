package com.jsp.empoye_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Experience {
	@Id
	int ExperinceId;
	String companyName;
	String yearOfExp;
	String monthOfExp;
	String designation;
}
