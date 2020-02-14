package com.braidenmiller.notesapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braidenmiller.notesapp.entities.User;
import com.braidenmiller.notesapp.services.UserService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class UserController {

	//  S E R V I C E S
	
	@Autowired
	private UserService uService;
	
	// M A P P I N G S
	
	@GetMapping("users")
	public List<User> index( HttpServletResponse res) {
		try {
			res.setStatus(200);
			return uService.index();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@GetMapping("users/{id}")
	public User show( HttpServletResponse res, @PathVariable Integer id) {
		try {
			res.setStatus(200);
			User user = uService.show(id);
			if(user == null) {
				return null;
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@GetMapping("users/loggedIn")
	public User getLog( HttpServletResponse res, Principal principal) {
		try {
			res.setStatus(200);
			User user = uService.getLoggedIn(principal.getName());
			if(user == null) {
				return null;
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
//	@GetMapping("users/search/{search}")
//	public Set<User> search( HttpServletResponse res, Principal principal, @PathVariable String search){
//		try {
//			res.setStatus(200);
//			return uService.search( search);
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			return null;
//			
//		}
//	}
//
//	@PostMapping("users")
//	public User create(HttpServletRequest req, HttpServletResponse res, Principal principal,  @RequestBody User note) {
//		try {
//			res.setStatus(201);
//			note = uService.create(principal.getName(), note);
//			StringBuffer resourceUrl = req.getRequestURL();
//			resourceUrl.append("/");
//			resourceUrl.append(note.getId());
//			res.setHeader("Location", resourceUrl.toString());
//			return note;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//			
//		}
//		
//		
//	}

	@PutMapping("users/{id}")
	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer id, 
						 @RequestBody User user) {
		
		try {
			user = uService.update(id, user);
			
			StringBuffer resourceUrl = req.getRequestURL();
			resourceUrl.append("/");
			resourceUrl.append(user.getId());
			res.setHeader("Location", resourceUrl.toString());
			
			res.setStatus(202);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	@PutMapping("users/password/{id}")
	public User updatePassword(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer id, 
			@RequestBody String password) {
		
		try {
			User user = null;
			user = uService.updatePassword(id, password);
			
			StringBuffer resourceUrl = req.getRequestURL();
			resourceUrl.append("/");
			resourceUrl.append(user.getId());
			res.setHeader("Location", resourceUrl.toString());
			
			res.setStatus(202);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	@DeleteMapping("users/{id}")
	public void destroy(HttpServletResponse res, @PathVariable Integer id) {
		
		if(uService.destroy(id)) {
			res.setStatus(200);
		}
		else {
			res.setStatus(500);
		}
	}
	
}
