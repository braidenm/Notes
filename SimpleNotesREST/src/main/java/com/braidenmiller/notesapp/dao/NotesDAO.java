package com.braidenmiller.notesapp.dao;

import java.util.List;

import com.braidenmiller.notesapp.entities.Note;

public interface NotesDAO {
	
	public List<Note> index();
	public Note show(int id);
	public Note create(Note todo);
	public Note update(int id, Note note);
	public boolean destroy(int id);
	public List<Note> search(String search);

}
