package com.example.demo;

public class Esercizio {
	
	private String nome;
	
	private String tipologia;
	
	Esercizio(String n, String t){
		nome = n; tipologia = t;
	}

	String getNome() {return nome;}

	void setNome(String nome) {this.nome = nome;}

	String getTipologia() {return tipologia;}

	void setTipologia(String tipologia) {this.tipologia = tipologia;}

	@Override
	public String toString() {
		return nome + " " + tipologia;
	}
}
