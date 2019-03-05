package com.braidenmiller.notesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braidenmiller.notesapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByUsername(String Username);

}
