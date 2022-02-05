package com.example.demo;

/**
 * The Interface ControllerAutenticatoreIF.
 */
public interface ControllerAutenticatoreIF {

	/**
	 * Login.
	 *
	 * @param nome the nome
	 * @param password the password
	 * @return esito della richiesta
	 */
	String login(String nome, String password);
	
	/**
	 * Logout.
	 *
	 * @param nome utente
	 * @param password 
	 * @return esito della richiesta
	 */
	String logout(String nome, String password);
	
	/**
	 * Registra utente.
	 *
	 * @param nome utente
	 * @param password 
	 */
	void registraUtente(String nome, String password);
	
}