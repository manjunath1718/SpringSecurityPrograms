package com.telusko.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class InfoController {

	@GetMapping("info")
	public String getInfo(HttpServletRequest request) {
		
		return "Hi folks, I'm Java Dev from in India "+request.getSession().getId();
	}
	
	@GetMapping("skill")
	public String getSkillInfo(HttpServletRequest request) {
		
		return "Java and SpringBoot "+request.getSession().getId();
	}
}
