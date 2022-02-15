package com.example.demo;

import java.time.*;

class Macchinario {

	private String nome;
	private String tipo;
	private String tempo;

	public Macchinario() {
		nome = "";
		tipo = "";
		tempo = "";
	}

	Macchinario(String n, String t, String Temp) {
		nome = n;
		tipo = t;
		tempo = Temp;
		// Conversione Stringa > LocalTime
		// tempo = LocalTime.parse(Temp);
	}

	String getNome() {
		return nome;
	}

	String getTipo() {
		return tipo;
	}

	String getTempo() {
		return tempo;
	}

	void setNome(String nome) {
		this.nome = nome;
	}

	void setTipo(String tipo) {
		this.tipo = tipo;
	}

	void setTempo(String tempo) {
		this.tempo = tempo;
	}

	@Override
	public String toString() {
		return "{" + nome + "," + tipo + "," + tempo.toString() + "}";
	}

	public String getStato() {
		if (tempo.equals("23:59:59"))
			return "GUASTO";
		LocalTime t = LocalTime.parse(tempo);
		return (t.compareTo(LocalTime.now()) > 0) ? "OCCUPATO" : "LIBERO";
	}
}
