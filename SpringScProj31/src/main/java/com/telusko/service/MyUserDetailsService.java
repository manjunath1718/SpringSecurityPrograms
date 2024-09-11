package com.telusko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.dao.UserRepo;
import com.telusko.dao.Users;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = repo.findByName(username);
		
		if(user==null) {
			
			System.out.println("404 User Not Found");
			throw new UsernameNotFoundException("404 User Not Found");
		}
		return new UserPrincipal(user);
	}

}
