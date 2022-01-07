package com.example.demo;

class Macchinario {
	
	private String nome;
	private String tipo;
	private String tempo;
	
	// "tempo" è il timestamp che indica da quando il macchinario è/sarà libero.
	// viene trattato come variabile orario solo quando serve fare confronti (nell'algoritmo sul Server), 
	// per il resto rimarrà sempre una normale Stringa
	
	public Macchinario() {
		nome = ""; tipo = ""; tempo = "";
	}
	
	public Macchinario(String n, String t, String Temp) {
		nome = n; tipo = t; tempo = Temp;	
	}
	
	String getNome() {return nome;}
	String getTipo() {return tipo;}
	String getTempo() {return tempo;}

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
		return "{"+nome + "," + tipo + "," + tempo.toString() + "}";
	}

}
