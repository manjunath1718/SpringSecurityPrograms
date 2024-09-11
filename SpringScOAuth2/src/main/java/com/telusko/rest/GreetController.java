package com.telusko.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

	@GetMapping("/greet")
	public String greetings(){
		
		return "Hello goodmorning everyone";
	}
	
	

}
