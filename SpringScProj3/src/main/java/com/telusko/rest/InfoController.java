package com.telusko.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class InfoController {

	List<Student> students=new ArrayList<>(List.of(new Student(1,"raj","Java")
			, new Student(2,"ram","Spring")
			,new Student(3,"mohan","DevOps")));
	
	@GetMapping("/allStudents")
	public List<Student> getAllStudents(){
		
		return students;		
	}
	
	@GetMapping("/info")
	public String getInfo(){
		
		return "This App is All About Students";		
	}
	
	@PostMapping("/add-Student")
	public String registerStudent(@RequestBody Student student){
		
		students.add(student);
		return "Student is added";		
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request){
		
		return (CsrfToken) request.getAttribute("_csrf");		
	}
	
	
}
