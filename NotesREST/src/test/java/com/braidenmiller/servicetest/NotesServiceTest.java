package com.braidenmiller.servicetest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.braidenmiller.notesapp.entities.Note;
import com.braidenmiller.notesapp.services.NoteService;
import com.braidenmiller.notesapp.services.NoteServiceMock;

class NotesServiceTest {
	
	private Note note;

//	@Autowired
	private NoteService noteService = new NoteServiceMock();

	@BeforeEach
	void setUp() throws Exception {
		note = new Note();
		note.setDetails("Test Dtails");
		note.setTitle("Test Title");
		note.setCompleted(false);
		
//		noteService.create("test", note);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
//		noteService.destroy("test", note.getId());
	}

	@Test
	@DisplayName( "Test Index")
	void test_index() {
		Set<Note> testNotes = noteService.index("tester");
		
		assertTrue(testNotes.size() == 1);
		
	}
//	@Test
//	@DisplayName( "Test Show")
//	void test_show() {
//		Note testNote = noteService.show("test", note.getId());
//		
//		assertTrue(testNote != null);
//		assertTrue("Test Title".equals(testNote.getTitle()));
//		assertTrue("Test Details".equals(testNote.getDetails()));
//		assertFalse(testNote.isCompleted());
//		
//	}
//	@Test
//	@DisplayName( "Test search")
//	void test_search() {
//		Set<Note> aSearch = noteService.search("test", "a");
//		Set<Note> bSearch = noteService.search("test", "b");
//		Set<Note> estSearch = noteService.search("test", "est");
//		Set<Note> badUserSearch = noteService.search("badTest", "test");
//		
//		
//		assertTrue(aSearch != null);
//		assertTrue(bSearch != null);
//		assertTrue(estSearch != null);
//		assertTrue(badUserSearch != null);
//		
//		assertTrue(aSearch.size() == 1);
//		assertTrue(bSearch.size() == 0);
//		assertFalse(estSearch.size() == 1);
//		assertFalse(badUserSearch.size() == 0);
//		
//	}

}
