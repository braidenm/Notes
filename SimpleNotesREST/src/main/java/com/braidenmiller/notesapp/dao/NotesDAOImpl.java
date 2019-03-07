package com.braidenmiller.notesapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.braidenmiller.notesapp.entities.Note;

@Service
public class NotesDAOImpl implements NotesDAO {
	
	// F I E L D S
	
	private List<Note> notes = new ArrayList<>();
	
	{
		notes.add(new Note(1, "Get Milk"));
		notes.add(new Note(2, "Get a Job"));
		notes.add(new Note(3, "Remember to take out the trash"));
		notes.add(new Note(4, "Remember something"));
		notes.add(new Note(5, "Look Im in a List"));
	}

	// M E T H O D S
	
	@Override
	public List<Note> index() {
		
		return notes;
	}

	@Override
	public Note show(int id) {
		for(Note note: notes) {
			if(note.getId() == id) {
				return note;
			}
		}
		return null;
	}

	@Override
	public Note create(Note note) {
		int largest = 0;
		for(Note n: notes) {
			if(n.getId() > largest) {
				largest = n.getId();
			}
		}
		note.setId(largest + 1);
		notes.add(note);
		return note;
	}

	@Override
	public Note update(int id, Note note) {
		for(Note n: notes) {
			if(n.getId() == id) {
				n = note;
				return n;
			}
		}
		return null;
	}

	@Override
	public boolean destroy(int id) {
		for(Note n: notes) {
			if(n.getId() == id) {
				notes.remove(n);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Note> search(String search) {
		List<Note> result = new ArrayList<>();
		for(Note n: notes) {
			if(n.getBody().toLowerCase().contains(search.toLowerCase())) {
				result.add(n);
			}
		}
		return result;
	}

}
