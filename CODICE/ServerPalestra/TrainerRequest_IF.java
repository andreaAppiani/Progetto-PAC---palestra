package com.example.demo;

public interface TrainerRequest_IF {

	/**
	 * Ritorna la lista di esercizi della scheda fitness richiesta dall'utente
	 *
	 * @param ID dell'utente
	 * @return lista di esercizi
	 */
	RisorsaJSON getScheda(int id);

	/**
	 * Aggiunge una nuova scheda nel DB
	 *
	 * @param SchedaFitness da aggiungere
	 * @return void
	 */
	void addScheda(SchedaFitness s);

	/**
	 * Rimuove una scheda del DB
	 *
	 * @param ID della scheda da rimuovere
	 * @return void
	 */
	void removeScheda(int id);

}
