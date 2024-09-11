package com.telusko.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {

	public Users findByName(String name);
}
