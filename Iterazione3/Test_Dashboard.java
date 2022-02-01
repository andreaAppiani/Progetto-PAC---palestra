package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class Test_DashboardParsing {

	@Test
	void test() {
		String s = "[{'tipo':'Pettorali','tempo':'10:15:00','nome':'Panca piana'},{'tipo':'Braccia','tempo':'10:17:00','nome':'Colonna'}]";
	
		DashboardView view = DashboardView.getView();
		DashboardController controller = DashboardController.getController(view);
		
		List<Macchinario> l = controller.parsingMacchinari(s);
		
		System.out.println("lista:"+l.toString());
		
		assertTrue(l.get(0).getNome().equals("Panca piana") && l.get(1).getNome().equals("Colonna"));
	}

}
