package com.example.demo;

public class MyModel implements MyModelIF{
	
	//----------COMPONENTE MOCK----------------
	
	
	//Dovrebbe inviare una Query al DB richiedendo una specifica Scheda tramite ID
	public SchedaFitness getSchedaFitness(int i){
		
		System.out.println("ricerca ID:"+i);
		SchedaFitness S = new SchedaFitness(i);
		return S;
	}
	
	
}
