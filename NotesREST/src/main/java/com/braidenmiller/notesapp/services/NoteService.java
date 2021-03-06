package com.braidenmiller.notesapp.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.braidenmiller.notesapp.entities.Note;

public interface NoteService {

	public Set<Note> index(String username);
	public Note show(String username, int id);
	public Note create(String username, Note todo);
	public Note update(String username, int id, Note note);
	public boolean destroy(String username, int id);
	public Set<Note> search(String username, String search);
}
