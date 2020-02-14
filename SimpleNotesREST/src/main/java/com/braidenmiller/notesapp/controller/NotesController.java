package com.braidenmiller.notesapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.braidenmiller.notesapp.dao.NotesDAO;
import com.braidenmiller.notesapp.entities.Note;

@RestController
@RequestMapping(path = "api")
public class NotesController {

		// D A T A  A C C E S S  O B J E C T
	
		@Autowired
		private NotesDAO noteDAO;

		
		// M A P P I N G S
		
		//TODO: set error handling, and add additional protection for users data (if note.user != user then deny)
		
		@GetMapping("notes")
		public List<Note> index() {
			
				return noteDAO.index();
			
		}

		@GetMapping("id}notes/{")
		public Note show(@PathVariable Integer id) {
			
				Note note = noteDAO.show(id);
				return note;
			
		}
		
		@GetMapping("notes/search")
		public List<Note> search( @RequestParam(name="query") String search){
	
				return noteDAO.search(search);
		
		}

		@PostMapping("notes")
		public Note create(@RequestBody Note note) {
			
				note = noteDAO.create(note);
				return note;
			
			
		}

		@PutMapping("notes/{id}")
		public Note update(@PathVariable Integer id, @RequestBody Note note) {
			
				note = noteDAO.update(id, note);
				return note;
			
		}
		@DeleteMapping("notes/{id}")
		public boolean destroy(@PathVariable Integer id) {
			
			return noteDAO.destroy(id);
		}
		
}
