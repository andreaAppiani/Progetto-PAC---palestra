package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class SchedaFitness {

	private final int ID;
	private List<Esercizio> lista; 
		
	public SchedaFitness(int id, String s) {
		ID = id;
		lista = new ArrayList<>();
	}
	public SchedaFitness(int id) {
		ID = id;
		lista = new ArrayList<>();
		lista.add(new Esercizio("Panca Piana","Pettorali","5x8"));
		lista.add(new Esercizio("Lat Machine","Dorsali","4x8"));
		lista.add(new Esercizio("Curl con Bilanciere","Bicipiti","6x6"));
		lista.add(new Esercizio("Dip","Tricipiti","4x10"));
		lista.add(new Esercizio("Leg-Press","Gambe","5x8"));
		
	}
	int getID() {return ID;}
	
	int getLunghezzaLista() {return lista.size();}
	
	Esercizio getEsercizio(int index) {return lista.get(index);}
	
	//void addMacchinario(Macchinario m) { lista.add(m); }
	
	List<Esercizio> getLista(){return lista;}
}
