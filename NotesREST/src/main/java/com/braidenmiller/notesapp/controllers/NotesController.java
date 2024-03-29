package com.braidenmiller.notesapp.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braidenmiller.notesapp.entities.Note;
import com.braidenmiller.notesapp.services.NoteService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:3000" })
public class NotesController {

	//  S E R V I C E S
	
	@Autowired
	private NoteService noteService;
	
	// M A P P I N G S
	
	@GetMapping("notes")
	public Set<Note> index( HttpServletResponse res, Principal principal) {
		try {
			res.setStatus(200);
			return noteService.index(principal.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@GetMapping("notes/{id}")
	public Note show(Principal principal, HttpServletResponse res, @PathVariable Integer id) {
		try {
			res.setStatus(200);
			Note note = noteService.show(principal.getName(), id);
			if(note == null) {
				return null;
			}
			return note;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@GetMapping("notes/search/{search}")
	public Set<Note> search( HttpServletResponse res, Principal principal, @PathVariable String search){
		try {
			res.setStatus(200);
			return noteService.search(principal.getName(), search);
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
			
		}
	}

	@PostMapping("notes")
	public Note create(HttpServletRequest req, HttpServletResponse res, Principal principal,  @RequestBody Note note) {
		try {
			res.setStatus(201);
			note = noteService.create(principal.getName(), note);
			StringBuffer resourceUrl = req.getRequestURL();
			resourceUrl.append("/");
			resourceUrl.append(note.getId());
			res.setHeader("Location", resourceUrl.toString());
			return note;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
		
	}

	@PutMapping("notes/{id}")
	public Note update(HttpServletRequest req, Principal principal, HttpServletResponse res, @PathVariable Integer id, 
						 @RequestBody Note note) {
		
		try {
			note = noteService.update(principal.getName(), id, note);
			
			StringBuffer resourceUrl = req.getRequestURL();
			resourceUrl.append("/");
			resourceUrl.append(note.getId());
			res.setHeader("Location", resourceUrl.toString());
			
			res.setStatus(202);
			return note;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	@DeleteMapping("notes/{id}")
	public void destroy(Principal principal, HttpServletResponse res, @PathVariable Integer id) {
		
		if(noteService.destroy(principal.getName(), id)) {
			res.setStatus(200);
		}
		else {
			res.setStatus(500);
		}
	}
	
}
