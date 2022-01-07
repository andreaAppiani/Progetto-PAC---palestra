package com.example.demo;

public class MyModel {
	
	// ------------- COMPONENTE MOCK -------------
	
	//Dovrebbe essere in comunicazione con il DATABASE contenente le schede fitness
	
	//Dovrebbe inviare una Query al DB richiedendo una specifica Scheda tramite il parametro ID
	SchedaFitness getSchedaFitness(int i){
		
		System.out.println("ricerca ID:"+i);
		SchedaFitness S = new SchedaFitness(i); // in realtà crea al momento una scheda con già degli esercizi dentro
		return S;
	}

}
