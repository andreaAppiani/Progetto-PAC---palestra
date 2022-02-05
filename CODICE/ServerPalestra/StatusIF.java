package com.example.demo;

public interface StatusIF {
	
	/**
	 * Prende le nuove informazioni inviate dal macchinario
	 * trova quelle salvate precedentemente nella lista e le aggiorna.
	 * Se è la prima volta che riceve informazioni da questo
	 * macchinario si limita ad aggiungerlo alla lista.
	 * 
	 * @param Macchinario 
	 */
	void writeStatoMacchinario(Macchinario m);

}
