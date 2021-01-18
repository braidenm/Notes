package com.braidenmiller.notesapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.braidenmiller.notesapp.controllers.NotesController;

//@SpringBootTest
public class SmokeTest {

	@Autowired
	private NotesController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertTrue(true);
	}
}
