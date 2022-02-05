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
		lista.add(new Esercizio("Flessioni","Braccia"));
		lista.add(new Esercizio("Sollevamento Manubri","Braccia"));
		lista.add(new Esercizio("Corsa","Gambe"));
		lista.add(new Esercizio("Panca Piana","Pettorali"));
	}
	int getID() {return ID;}
	
	int getLunghezzaLista() {return lista.size();}
	
	Esercizio getEsercizio(int index) {return lista.get(index);}
	
	//void addMacchinario(Macchinario m) { lista.add(m); }
	
	List<Esercizio> getLista(){return lista;}
}
