package com.braidenmiller.notesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.braidenmiller.notesapp.entities.User;
import com.braidenmiller.notesapp.repositories.UserRepo;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepo repo;
	@Autowired
	PasswordEncoder encode;
	
	@Override
	public User register(User user) {
		user.setPassword(encode.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("standard");
		repo.saveAndFlush(user);
		return user;
	}

}
