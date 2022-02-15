package com.example.demo;

/**
 * The Interface MyModelIF.
 */
public interface Model_IF {

	/**
	 * Ritorna la scheda fitness richiesta dall'utente recuperandola dal Database
	 *
	 * @param ID dell'utente
	 * @return la scheda fitness
	 */
	SchedaFitness getSchedaByID(int i);

}
