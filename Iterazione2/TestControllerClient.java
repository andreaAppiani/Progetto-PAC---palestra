package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestClient {

	@Test
	void testParsing() {
		String s = "[{\"tipo\":\"Gambe\",\"nome\":\"Corsa\"},{\"tipo\":\"Pettorali\",\"nome\":\"Panca Piana\"}]";
	
		AppView view = new AppView();
		AppController c = AppController.getController(view);
		List<Esercizio> l = c.parsingEsercizi(s);
		
		assertTrue(l.get(0).getNome().equals("Corsa") && l.get(0).getTipologia().equals("Gambe") && 
				l.get(1).getNome().equals("Panca Piana") && l.get(1).getTipologia().equals("Pettorali"));		
	}
}
