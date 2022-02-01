package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_Aggregatore {

	ControllerAggregatore controller = ControllerAggregatore.getController();
	@Test
	void test() {
		Macchinario m1 = new Macchinario("Leg-extension","Gambe","10:15:30");
		Macchinario m2 = new Macchinario("Leg-press","Gambe","16:00:00");
		
		controller.writeStatoMacchinario(m1);
		controller.writeStatoMacchinario(m2);
		
		System.out.println(controller.getStatoMacchinari().toString());
		
		Macchinario m3 = new Macchinario("Leg-press","Gambe","18:00:00");
		
		controller.writeStatoMacchinario(m3);
		
		System.out.println(controller.getStatoMacchinari().toString());
		
		assertTrue(controller.getStatoMacchinari().get(1).getTempo().equals("18:00:00"));
	}

}
