package com.example.demo;
import java.time.*;
import java.util.Comparator;

class Macchinario {
	
	private String nome;
	private String tipo;
	private String tempo;
	
	Macchinario(String n, String t, String Temp) {
		nome = n; tipo = t; tempo = Temp;
		//Conversione Stringa > LocalTime
		//tempo = LocalTime.parse(Temp);	
	}
	String getNome() {return nome;}
	String getTipo() {return tipo;}
	String getTempo() {return tempo;}
	
	void setNome(String s) {nome = s;}
	void setTipo(String s) {tipo = s;}
	void setTempo(String s) {tempo = s;}

	@Override
	public String toString() {
		return "{"+nome + "," + tipo + "," + tempo.toString() + "}";
	}
}

class MyComparator implements Comparator<Macchinario>{
	public int compare(Macchinario o1, Macchinario o2) {
		LocalTime t1 = LocalTime.parse(o1.getTempo()); 
		LocalTime t2 = LocalTime.parse(o2.getTempo()); 
		if(t1.compareTo(t2) > 0) return 1;
		else if (t1.compareTo(t2) < 0) return -1;
		else return 0;
	}
}
