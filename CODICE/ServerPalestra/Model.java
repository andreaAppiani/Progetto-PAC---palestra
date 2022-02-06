package com.example.demo;

public class MyModel implements MyModelIF{
	
	//----------COMPONENTE MOCK----------------
	
	//Dovrebbe inviare una Query al DB richiedendo una specifica Scheda tramite ID
	
	private MyModel(){}
	public static MyModel model;
	public static MyModel getModel() {
		model = new MyModel();
		return model;
	}
	
	public SchedaFitness getSchedaByID(int i){
		// ritorna la SchedaFitness
	}
	
}
