package com.braidenmiller.notesapp;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.braidenmiller.notesapp.controllers.NotesController;

@RunWith(SpringRunner.class)
//@WebMvcTest(NotesController.class)
public class NotesRestApplicationTests {
	
	

	@Test
	public void contextLoads() {
		assertTrue(true);
	}

}
