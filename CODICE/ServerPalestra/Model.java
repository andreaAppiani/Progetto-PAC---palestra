package com.example.demo;

public class Model implements Model_IF {

	// ----------COMPONENTE MOCK----------------

	// Dovrebbe inviare una Query al DB richiedendo una specifica Scheda tramite ID

	private Model() {
	}

	public static Model model = null;

	public static Model getModel() {
		if (model == null)
			model = new Model();
		return model;
	}

	public SchedaFitness getSchedaByID(int i) {
		SchedaFitness S = new SchedaFitness(i);
		return S;
	}

}
