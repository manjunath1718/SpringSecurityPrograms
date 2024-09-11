package com.telusko.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.dao.Users;
import com.telusko.service.JwtService;
import com.telusko.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user){
		
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		return service.saveTheUser(user);
				
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {

//		System.out.println("Its Login not registration");
		 Authentication authentication = authenticationManager
				 .authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		
		 if(authentication.isAuthenticated()) {
			 
			 return jwtService.generateToken(user.getName());
		 }else {
			 return "Login Failed";
		 }
		 
	}
	
	@PostMapping("/userinfo")
	public String info() {

		return "user having id, name, password";

	}
	
	
	
	
	
	
}
