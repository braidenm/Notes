package com.braidenmiller.notesapp.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braidenmiller.notesapp.entities.Note;

public interface NotesRepo extends JpaRepository<Note, Integer> {
	Set<Note> findByDetailsLikeOrTitleLike(String details, String title);

}
