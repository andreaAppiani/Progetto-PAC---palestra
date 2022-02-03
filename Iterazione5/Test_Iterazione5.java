package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class Test_Iterazione5 {

	MyController controller = MyController.getController();
	SchedaFitness scheda = new SchedaFitness(1);
	
	@Test
	void testAlgoritmoGreedy() {
		List<Macchinario> statoAttualeMac = new ArrayList<>();
		Macchinario m1 = new Macchinario("Panca Piana","Pettorali","10:20:00");
		Macchinario m2 = new Macchinario("Colonna","Braccia","10:17:00");
		Macchinario m3 = new Macchinario("Cavi","Braccia","10:10:00");
		Macchinario m4 = new Macchinario("Leg-press","Gambe","10:20:30");
		Macchinario m5 = new Macchinario("Leg-extension","Gambe","10:15:30");
		Macchinario m6 = new Macchinario("Panca inclinata","Pettorali","10:16:05");
		statoAttualeMac.add(m1);statoAttualeMac.add(m2);statoAttualeMac.add(m3);
		statoAttualeMac.add(m4);statoAttualeMac.add(m5);statoAttualeMac.add(m6);
		
		List<Macchinario> listaAggiornata = controller.AlgoritmoGreedy(scheda, statoAttualeMac);
		
		System.out.println(listaAggiornata);
		
		assertTrue(listaAggiornata.get(0).getNome().equals("Cavi")
				&& listaAggiornata.get(1).getNome().equals("Colonna")
				&& listaAggiornata.get(2).getNome().equals("Leg-extension")
				&& listaAggiornata.get(3).getNome().equals("Panca Piana"));
	}
	
	@Test
	void testSearchScheda() {
		controller.listaSchede.add(new SchedaFitness(1));
		controller.listaSchede.add(new SchedaFitness(3));
		
		SchedaFitness s1 = controller.searchScheda(3);
		SchedaFitness s2 = controller.searchScheda(5);
		
		assertTrue(s1.getID() == 3);
		assertTrue(s2 == null);
	}

}
