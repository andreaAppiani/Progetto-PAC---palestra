package com.example.demo;

import java.awt.event.ActionListener;

public interface AppViewIF {

	void addListenerAccedi(ActionListener a);

	void addListenerSchedaOriginale(ActionListener a);

	void addListenerSchedaAggiornata(ActionListener a);

	void switchPanel(String s);

	void loginErrato();

	String getNome();

	String getPassword();

}