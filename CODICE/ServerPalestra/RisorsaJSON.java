package com.example.demo;

public class RisorsaJSON {

	final long id_risorsa;
	final String lista;

	public RisorsaJSON(String s, Long x) {
		id_risorsa = x;
		lista = s;
	}

	public long getId() {
		return id_risorsa;
	}

	public String getLista() {
		return lista;
	}

}
