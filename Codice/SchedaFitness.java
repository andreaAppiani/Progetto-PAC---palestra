package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class SchedaFitness {

	static int counter = 0;
	final int ID;
	
	List<Macchinario> lista; 
	
	public SchedaFitness() {
		counter++;
		ID = counter;
		lista = new ArrayList<>();
		lista.add(new Macchinario("Es1","",""));
		lista.add(new Macchinario("Es2","",""));
		lista.add(new Macchinario("Es3","",""));
	}
	
	@Override
	public String toString() {
		return "Scheda n°" + ID +": " + lista.toString();
	}
	
}
