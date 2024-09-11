package com.telusko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.dao.UserRepo;
import com.telusko.dao.Users;

@Service
public class UserService {

	@Autowired
	UserRepo repo;
	
	public Users saveTheUser(Users user) {
		
		return repo.save(user);
	}
	
	
}
