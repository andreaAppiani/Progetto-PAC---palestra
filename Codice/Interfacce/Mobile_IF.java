package com.example.demo;

import java.awt.event.ActionListener;

/**
 * The Interface AppViewIF.
 */
public interface AppViewIF {

	/**
	 * Aggiunge un metodo Listener al pulsante "accedi".
	 *
	 * @param a the a
	 */
	void addListenerAccedi(ActionListener a);

	/**
	 * Aggiunge un metodo Listener al pulsante "scheda originale".
	 *
	 * @param a the a
	 */
	void addListenerSchedaOriginale(ActionListener a);

	/**
	 * Aggiunge un metodo Listener al pulsante "scheda aggiornata".
	 *
	 * @param a the a
	 */
	void addListenerSchedaAggiornata(ActionListener a);

	/**
	 * Modifica la schermata visualizzata sulla View
	 *
	 * @param nome della schermata da visualizzare
	 */
	void switchPanel(String s);

	/**
	 * Mostra sulla View un messaggio di errore.
	 */
	void loginErrato();

	/**
	 * Ritorna il nome inserito.
	 *
	 * @return nome
	 */
	String getNome();

	/**
	 * Ritorna la password inserita.
	 *
	 * @return password
	 */
	String getPassword();

}