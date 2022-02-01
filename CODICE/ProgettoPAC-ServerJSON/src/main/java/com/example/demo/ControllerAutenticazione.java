package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerAutenticazione implements ControllerAutenticatoreIF {

	//----------COMPONENTE MOCK---------------
	
	/*
	 * ricevendo la richiesta di autenticazione dall'App dovrebbe passare
	 * i dati al server LDAP, verificare l'autenticazione e comunicare l'esito all'utente
	 */
	
	@GetMapping("/autentica")
	public String login(String nome, String password) {return "AUTENTICATO";}
	
	public String logout(String nome, String password) {return "LOG-OUT EFFETTUATO";}
	
	public void registraUtente(String nome, String password) { }
	
}
