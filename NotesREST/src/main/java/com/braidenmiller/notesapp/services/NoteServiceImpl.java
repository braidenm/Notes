package com.braidenmiller.notesapp.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braidenmiller.notesapp.entities.Note;
import com.braidenmiller.notesapp.entities.User;
import com.braidenmiller.notesapp.repositories.NotesRepo;
import com.braidenmiller.notesapp.repositories.UserRepo;

@Service
public class NoteServiceImpl implements NoteService {
	
	// R E P O S I T O R I E S
	
	@Autowired
	private NotesRepo notesRepo;
	@Autowired
	private UserRepo userRepo;

	// C R U D  O P E R A T I O N S
	
	@Override
	public Set<Note> index(String username) {
		Set<Note> notes = new HashSet<>();
		for (Note note : notesRepo.findAll()) {
			if(note.getUser().getUsername().equals(username)) {
				notes.add(note);
			}
		}
		return notes;

		
	}

	@Override
	public Note show(String username, int id) {

		Optional<Note> optional = notesRepo.findById(id);
		if(optional.isPresent()) {
			Note note = optional.get();
			if(note.getUser().getUsername().equals(username)) {
				return note;
			}
		}
		return null;
	
	}

	@Override
	public Note create(String username, Note note) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			note.setUser(user);
			note = notesRepo.saveAndFlush(note);
			return note;
		}
		
		return null;
	}

	@Override
	public Note update(String username, int id, Note note) {
		User user = userRepo.findByUsername(username);
		
		if(user != null) {
			Optional<Note> optional = notesRepo.findById(id);
			if(optional.isPresent()) {
				Note managed = optional.get();
				if(managed.getUser().getUsername().equals(username)) {
					managed.setCompleted(note.isCompleted());
					managed.setDetails(note.getDetails());
					managed.setTitle(note.getTitle());
					try {
						managed = notesRepo.saveAndFlush(managed);
						return managed;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		User user = userRepo.findByUsername(username);
		Optional<Note> optional = notesRepo.findById(id);
		if(user != null && optional.isPresent()) {
			Note note = optional.get();
			if(note.getUser().getUsername().equals(username)) {
				user.removeNote(note);
				note.setUser(null);
				try {
					notesRepo.delete(note);
					userRepo.saveAndFlush(user);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		
		return false;
	}

	@Override
	public Set<Note> search(String username, String search) {
		// uses the mySQL wild card to search for any number of characters 'like' 
		search = search.replaceAll(" ", "% %");
		search = "%" + search + "%";
		
		User user = userRepo.findByUsername(username);
		Set<Note> notes = notesRepo.findByDetailsLikeOrTitleLike(search, search);
		Set<Note> result = new HashSet<>();
		if(user != null) {
			for (Note note : notes) {
				if(note.getUser().getUsername().equals(username)) {
					result.add(note);
				}
			}
			if(search.equals("") || search == null) {
				result.addAll(notesRepo.findAll());
			}
		}
		
		
		
		return result;
	}

}
