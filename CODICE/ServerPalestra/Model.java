package com.example.demo;

public class Model implements ModelIF{
	
	//----------COMPONENTE MOCK----------------
	
	//Dovrebbe inviare una Query al DB richiedendo una specifica Scheda tramite ID
	
	private Model(){}
	public static Model model;
	public static Model getModel() {
		model = new Model();
		return model;
	}
	
	public SchedaFitness getSchedaByID(int i){
		// ritorna la SchedaFitness
	}
	
}
