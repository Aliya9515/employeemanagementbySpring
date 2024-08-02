package com.jsp.empoye_management.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.empoye_management.entity.Employee;
public interface EmployeeRepo extends JpaRepository <Employee,Integer> {
}