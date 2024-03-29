package com.braidenmiller.notesapp.services;

import java.util.List;

import com.braidenmiller.notesapp.entities.User;

public interface UserService {

	public List<User> index();
	public User show(int id);
	public User create(User user);
	public User update(int id, User user);
	public User updatePassword(int id, String password);
	public boolean destroy(int id);
	public User getLoggedIn(String username);
}
