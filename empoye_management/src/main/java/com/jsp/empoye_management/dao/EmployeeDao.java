package com.jsp.empoye_management.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.empoye_management.entity.Employee;
import com.jsp.empoye_management.repo.EmployeeRepo;
@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepo repo;
	public Employee saveEmployee(Employee emp) {
		return repo.save(emp);
	}
}
