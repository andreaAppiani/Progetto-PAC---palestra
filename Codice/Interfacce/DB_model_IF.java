package com.example.demo;

/**
 * The Interface MyModelIF.
 */
public interface MyModelIF {

	/**
	 * Ritorna la scheda fitness richiesta dall'utente recuperandola dal Database
	 *
	 * @param ID dell'utente
	 * @return la scheda fitness
	 */
	SchedaFitness getSchedaFitness(int i);
	
}
