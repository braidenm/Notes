package com.braidenmiller.notesapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.braidenmiller.notesapp.entities.User;
import com.braidenmiller.notesapp.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	// R E P O S I T O R I E S
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	PasswordEncoder encode;
	
	// C R U D  O P E R A T I O N S
	
	@Override
	public List<User> index() {
		return uRepo.findAll();
	}

	@Override
	public User show(int id) {
		Optional<User> user = uRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}
	@Override
	public User getLoggedIn(String username) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			return user;
		}
		return null;
	}

	@Override
	public User create(User user) {
		if (user.getEmail() != null) {
			if(uniqueUsername(user)) {
				user = uRepo.saveAndFlush(user);
				return user;
			}
			
		}
		return null;
	}

	@Override
	public User update(int id, User user) {
		Optional<User> managedOp = uRepo.findById(id);
		if(managedOp.isPresent()) {
			User managed = managedOp.get();
			managed.setEmail(user.getEmail());
			managed.setRole(user.getRole());
			managed.setEnabled(user.isEnabled());
			managed.setFirstName(user.getFirstName());
			managed.setLastName(user.getLastName());
			
			if(!managed.getUsername().equals(user.getUsername()) && !uniqueUsername(user)) {
				try {
					throw new Exception("Invalid Username");
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			managed.setUsername(user.getUsername());
			
			return uRepo.saveAndFlush(managed);
		}
		
		
		return null;
	}

	@Override
	public boolean destroy(int id) {
		Optional<User> optional = uRepo.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			user.setNotes(null);
			try {
				uRepo.delete(user);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public User updatePassword(int id, String password) {
		Optional<User> managedOp = uRepo.findById(id);
		if(managedOp.isPresent()) {
			User managed = managedOp.get();
			managed.setPassword(encode.encode(password));

			return uRepo.saveAndFlush(managed);
		}
		
		return null;
	}
	
	// P R I V A T E  M E T H O D S
	
	private boolean uniqueUsername(User user) {
		for (User u : uRepo.findAll()) {
			if(user.getUsername().equals(u.getUsername())) {
				return false;
			}
		}
		return true;
	}

	

}
