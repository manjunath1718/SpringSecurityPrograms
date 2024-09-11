package com.telusko.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.dao.Users;
import com.telusko.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user){
		
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		return service.saveTheUser(user);
				
	}
	
	
}
