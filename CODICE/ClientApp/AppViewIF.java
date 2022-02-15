package com.example.demo;

import java.awt.event.ActionListener;

/**
 * The Interface AppViewIF.
 */
public interface AppViewIF {

	/**
	 * Aggiunge un metodo Listener al pulsante "accedi".
	 *
	 * @param ActionListener
	 */
	void addListenerAccedi(ActionListener a);

	/**
	 * Aggiunge un metodo Listener al pulsante "scheda originale".
	 *
	 * @param ActionListener
	 */
	void addListenerSchedaOriginale(ActionListener a);

	/**
	 * Aggiunge un metodo Listener al pulsante "scheda aggiornata".
	 *
	 * @param ActionListener
	 */
	void addListenerSchedaAggiornata(ActionListener a);

	/**
	 * Aggiunge un metodo Listener al pulsante "Logout".
	 *
	 * @param ActionListener
	 */
	void addListenerLogout(ActionListener a);

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