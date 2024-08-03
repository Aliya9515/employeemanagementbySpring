package com.jsp.empoye_management;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class EmpoyeManagementApplication {
	public static void main(String[] args)	{
		SpringApplication.run(EmpoyeManagementApplication.class, args);
	}
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
