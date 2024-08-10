package com.jsp.empoye_management.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.empoye_management.entity.Employee;
public interface EmployeeRepo extends JpaRepository <Employee,Integer> {
	@Query("delete from Employee where email=?1")
	Employee deleteByEmail(String email);
	Optional<Employee> findByEmail(String email);
	@Query("select a from Employee a where pwd=?1")
	Employee fetchByPwd(String pwd);
}