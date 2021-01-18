package com.braidenmiller.servicetest;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.braidenmiller.notesapp.NotesRestApplication;
import com.braidenmiller.notesapp.controllers.NotesController;
import com.braidenmiller.notesapp.entities.Note;
import com.braidenmiller.notesapp.entities.User;
import com.braidenmiller.notesapp.services.NoteService;
import com.braidenmiller.notesapp.services.UserService;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@SpringBootTest(classes = NotesRestApplication.class)
//@RunWith(SpringRunner.class)
//@WebMvcTest(NotesController.class)
//@WebMvcTest
@SpringBootTest(classes = NotesTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@AutoConfigureMockMvc
@ContextConfiguration(classes = NotesRestApplication.class)
class NotesTest {

	private Note note;

	@MockBean
	private NoteService noteService;
	
	@MockBean
	private UserService userService;
	
	@Autowired
    private MockMvc mockMvc;
	
	private static final String RESTURL="/api/";

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
////		noteService.destroy("test", note.getId());
//	}
//
	@Test
//	@SessionAttribute()
	void test_index() {
		
		try {
			this.mockMvc.perform( MockMvcRequestBuilders
			           .get(RESTURL+"index"))
			               .andDo(print())
			               .andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}   
		
		assertTrue(true);
		
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
