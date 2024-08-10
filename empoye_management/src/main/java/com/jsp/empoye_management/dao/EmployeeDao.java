package com.jsp.empoye_management.dao;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.empoye_management.entity.Employee;
import com.jsp.empoye_management.repo.EmployeeRepo;
@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepo repo;

	public Employee saveEmployee(Employee u) {
		// TODO Auto-generated method stub
		return repo.save(u);
	}

	public Employee findById(int id) {
		Optional<Employee> r = repo.findById(id);
		if (r.isPresent()) {
			return r.get();
		} else {
			return null;
		}

	}

	public Employee deleteById(int id) {
		Optional<Employee> u = repo.findById(id);
		repo.delete(u.get());
		return u.get();
	}
	
	public Employee empFetchByPwd(String pwd) {
		return repo.fetchByPwd(pwd);
		
	}


	public Employee updateEmployee(Employee emp) {
		Optional<Employee> db = repo.findById(emp.getEmpId());
		if (db.isPresent()) {
			Employee empy = db.get();
			repo.save(emp);
			if (emp.getFirstName() == null) {
				emp.setFirstName(empy.getFirstName());
			} else if (emp.getLastName() == null) {
				emp.setLastName(empy.getLastName());
			} else if (emp.getAge() == 0) {
				emp.setAge(0);
			} else if (emp.getGender() == null) {
				emp.setGender(empy.getGender());
			} else if (emp.getPhone() == 0) {
				emp.setPhone(0);
			} else if (emp.getEmail() == null) {
				emp.setEmail(empy.getEmail());
			} else if (emp.getPwd() == null) {
				emp.setPwd(empy.getPwd());
			} else if (emp.getDob() == null) {
				emp.setDob(empy.getDob());
			}
			return repo.save(emp);
		} else {
			return null;

		}

	}

	
	
	

	public Employee findByEmail(String email) {
		 Optional<Employee> r = repo.findByEmail(email);
	        if (r.isPresent()) {
	            return r.get();
	        } else {
	            return null;
	        }
	}

//	public EmployeeDetails findByEmail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	

	
}
