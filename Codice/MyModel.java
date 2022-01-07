package com.example.demo;

public class MyModel {
	
	//Dovrebbe essere in comunicazione con il DATABASE contenente le schede fitness
	
	
	//Dovrebbe inviare una Query al DB richiedendo una specifica Scheda tramite ID
	SchedaFitness getSchedaFitness(int i){
		
		System.out.println("ricerca ID:"+i);
		SchedaFitness S = new SchedaFitness(i);
		return S;
	}
	
	
}
