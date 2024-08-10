package com.jsp.empoye_management.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jsp.employe_management.dto.EmployeeClone;
import com.jsp.empoye_management.dao.EmployeeDao;
import com.jsp.empoye_management.entity.EducationalDetails;
import com.jsp.empoye_management.entity.Employee;
import com.jsp.empoye_management.entity.Experience;
import com.jsp.empoye_management.entity.LoginEmployee;
import com.jsp.empoye_management.exception.IdNotFound;
import com.jsp.empoye_management.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao dao;
	
	@Autowired
	JavaMailSender mailsender;
	
	@Autowired
	ModelMapper mapper;
	
	//saving operation
		public Employee SaveEmployee(Employee e) {
			Employee ea = dao.saveEmployee(e);
			try {
				sendHtmlEmail(e);
			} catch (MessagingException ex) {
				ex.printStackTrace();
			}
			return ea;
		}
	//sending email through spring boot
		public void sendHtmlEmail(Employee emp) throws MessagingException {
			MimeMessage message = mailsender.createMimeMessage();

			message.setFrom(new InternetAddress("shaikaliya996@gmail.com"));
			message.setRecipients(MimeMessage.RecipientType.TO, emp.getEmail());
			message.setSubject("Test email from Spring");

			String htmlContent = "<h1>Hi " + emp.getLastName() + ",</h1>" +

					"We have received your application and appreciate the time you took to apply. "
					+ "Our team will review your qualifications and get back to you if your profile matches our needs. "
					+ "Thank you once again for considering a career with us. We encourage you to visit our <a href=\"[Careers Page URL]\">careers page</a> for future opportunities.</p>";

			message.setContent(htmlContent, "text/html; charset=utf-8");

			mailsender.send(message);
		}
	//fetching details through find by id
		public ResponseEntity<ResponseStructure<Employee>> fetchEmployeeDetails(int id) {

			Employee db = dao.findById(id);
			if (db != null) {
				ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
				rs.setStateCode(HttpStatus.CREATED.value());
				System.out.println(dao.findById(id));
				rs.setData(dao.findById(id));
				rs.setMessage("id  found  Sucessfully..!");
				return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.CREATED);

			} else {
				throw new IdNotFound();
			}
		}
	// deleting by id
		public ResponseEntity<ResponseStructure<Employee>> delete(int id) {
			ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
			rs.setStateCode(HttpStatus.CREATED.value());
			rs.setData(dao.deleteById(id));
			rs.setMessage("employee deleted  Sucessfully..!");
			return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.CREATED);
		}
	// updating Employee details
		public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employeeDetails) {

			Employee db = dao.updateEmployee(employeeDetails);
			if (db != null) {
				ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
				rs.setStateCode(HttpStatus.CREATED.value());
				System.out.println(dao.updateEmployee(employeeDetails));
				rs.setData(dao.updateEmployee(employeeDetails));
				rs.setMessage("id  found  Sucessfully..!");
				return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.CREATED);

			} else {
				throw new IdNotFound();
			}
		}
	//login operation

		

//		 public ResponseEntity<ResponseStructure<EmployeeDetails>> loginEmployee(String email, String password) {
//		        EmployeeDetails emp = dao.findByEmail(email);
//		        ResponseStructure<EmployeeDetails> responseStructure = new ResponseStructure<>();
	//
//		        if (emp != null && emp.getPwd().equals(password)) {
//		            responseStructure.setStateCode(HttpStatus.OK.value());
//		            responseStructure.setMessage("Login Successful");
//		            responseStructure.setData(emp);
//		            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
//		        } else {
//		            responseStructure.setStateCode(HttpStatus.UNAUTHORIZED.value());
//		            responseStructure.setMessage("Invalid Credentials");
//		            responseStructure.setData(null);
//		            return new ResponseEntity<>(responseStructure, HttpStatus.UNAUTHORIZED);
//		        }
//		    }
	//	
		public ResponseEntity<ResponseStructure<Employee>> loginEmployee(LoginEmployee login) {
			Employee e = dao.findByEmail(login.getEmail());
			if(e!=null) {
				Employee empp=dao.empFetchByPwd(login.getPassword());
				if(login.getPassword().equals(e.getPwd())){
					ResponseStructure<Employee> rs=new ResponseStructure<Employee>();
					rs.setStateCode(HttpStatus.CONTINUE.value());
					rs.setMessage("Login successfully.................!");
					rs.setData(mapper.map(e, Employee.class));
					rs.setStateCode(HttpStatus.FOUND.value());
					return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.FOUND);
				}else {
					throw new IdNotFound("password is not found exception");
				}
			}
			else {
				throw new IdNotFound("email not found");
			}
			
		}

		
		
		//saving image operation

		public ResponseEntity<ResponseStructure<EmployeeClone>> saveImageById(int id, MultipartFile file)
				throws IOException {

			Employee db = dao.findById(id);
			if (db != null) {
				db.setImage(file.getBytes());
				ResponseStructure<EmployeeClone> emp = new ResponseStructure<EmployeeClone>();

				emp.setStateCode(HttpStatus.ACCEPTED.value());
				emp.setMessage("image save sucessfully...!");
				emp.setData(mapper.map(dao.updateEmployee(db), EmployeeClone.class));
				return new ResponseEntity<ResponseStructure<EmployeeClone>>(HttpStatus.CREATED);
			} else {
				throw new IdNotFound("id is not found");
			}

		}
		
		//fetching image operation
		public ResponseEntity<byte[]>fetchImage(int id){
			  Employee emp = dao.findById(id);
			       if(emp!=null) {
			        byte[]data=dao.findById(id).getImage();
			     HttpHeaders headers=new HttpHeaders();
			     headers.setContentType(MediaType.IMAGE_JPEG);
			     return new ResponseEntity<byte[]>(data,headers,HttpStatus.OK);
			    
			       }else
			        throw new IdNotFound("id is not found exception");
			 }
	
		
		public ResponseEntity<ResponseStructure<Employee>> addEductionDetails(int id, EducationalDetails ed) throws MessagingException {
			Employee emp = dao.findById(id);
			if(emp.getEduDetails()==null) {
				List<EducationalDetails> l = new ArrayList<EducationalDetails>();
				l.add(ed);
				emp.setEduDetails(l);
			}else {
				List<EducationalDetails> l = emp.getEduDetails();
				l.add(ed);
				emp.setEduDetails(l);
			}
			ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
			rs.setStateCode(HttpStatus.CREATED.value());
			rs.setMessage("Education details saved Sucessfully..!");
			rs.setData(mapper.map(dao.saveEmployee(emp), Employee.class));
			return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.CREATED);
		}

		public ResponseEntity<ResponseStructure<Employee>> saveExperienceDetails(int id, Experience ex) {
			Employee emp = dao.findById(id);
			if(emp.getEduDetails()==null) {
				List<Experience> l = new ArrayList<Experience>();
				l.add(ex);
				emp.setExperience(l);
			}else {
				List<Experience> l = emp.getExperience();
				l.add(ex);
				emp.setExperience(l);
			}
			ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
			rs.setStateCode(HttpStatus.CREATED.value());
			rs.setMessage("Experience details saved Sucessfully..!");
			rs.setData(mapper.map(dao.saveEmployee(emp), Employee.class));
			return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.CREATED);
		}

}