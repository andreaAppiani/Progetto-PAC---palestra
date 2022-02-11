package com.example.demo;

/**
 * The Interface ControllerManagerSchedeIF.
 */
public interface ControllerManagerSchedeIF {

	/**
	 * Ritorna la lista di esercizi della scheda fitness richiesta dall'utente.
	 *
	 * @param ID dell'utente
	 * @return lista di esercizi 
	 */
	RisorsaJSON getScheda(int id);

	/**
	 * Ritorna la lista di esercizi della scheda fitness richiesta dall'utente
	 * aggiornata in base allo stato corrente dei macchinari.
	 *
	 * @param ID dell'utente
	 * @return lista di esercizi della scheda in formato JSON (con associato l'id della richiesta)
	 */
	RisorsaJSON aggiornaScheda(int id);

}
