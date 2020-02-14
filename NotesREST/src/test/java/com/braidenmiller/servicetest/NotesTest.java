package com.braidenmiller.servicetest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.braidenmiller.notesapp.entities.Note;
import com.braidenmiller.notesapp.entities.User;
import com.braidenmiller.notesapp.services.NoteService;
import com.braidenmiller.notesapp.services.UserService;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotesTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotesTest {

	private Note note;

	@MockBean
	private NoteService noteService;
	
	@MockBean
	private UserService userService;
	

//	@BeforeEach
//	void setUp() throws Exception {
//		
//		
//		
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//		
//		noteService.destroy("test", note.getId());
//	}

	@Test
	void test_index() {
		
		User testUser = userService.show(4);
		note = new Note();
		note.setDetails("Test Dtails");
		note.setTitle("Test Title");
		note.setCompleted(false);
		note.setUser(testUser);
		
		note = noteService.create("test", note);
		
		Set<Note> testNotes = noteService.index("test");
		
		assertTrue(testNotes.size() == 1);
		
	}
//	@Test
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
