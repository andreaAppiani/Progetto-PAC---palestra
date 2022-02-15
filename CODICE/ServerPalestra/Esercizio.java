package com.example.demo;

public class Esercizio {

	private String nome;

	private String tipologia;

	private String serie;

	Esercizio(String n, String t, String s) {
		nome = n;
		tipologia = t;
		serie = s;
	}

	String getNome() {
		return nome;
	}

	void setNome(String nome) {
		this.nome = nome;
	}

	String getTipologia() {
		return tipologia;
	}

	void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	String getSerie() {
		return serie;
	}

	void setSerie(String s) {
		this.serie = s;
	}

	@Override
	public String toString() {
		return nome + " " + tipologia;
	}

}
