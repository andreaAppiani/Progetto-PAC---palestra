package com.example.demo;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {
	
	//Riceve la lista di macchinari e un ID per creare la RisorsaJSON da inviare al Client
	
	public RisorsaJSON parseListaMacchinari(List<Macchinario> l, boolean tempo,  AtomicLong counter) {

		
		JSONArray ja = new JSONArray();
		
		for(int i=0; i<l.size(); i++) {
			if(l.get(i) == null) continue;
			JSONObject o = new JSONObject();
			o.put("nome", l.get(i).getNome());
			o.put("tipo", l.get(i).getTipo());		
			if(tempo) o.put("tempo", l.get(i).getTempo());
			ja.put(o);
		}
		System.out.println("JSONARRAY RITORNATO: " + ja.toString());
		return new RisorsaJSON(ja.toString(), counter.incrementAndGet());
	}

	public RisorsaJSON parseListaMacchinariEs(List<Esercizio> l, AtomicLong counter) {
			
			JSONArray ja = new JSONArray();
			
			for(int i=0; i<l.size(); i++) {
				if(l.get(i) == null) continue;
				JSONObject o = new JSONObject();
				o.put("nome", l.get(i).getNome());
				o.put("tipo", l.get(i).getTipologia());
				ja.put(o);
			}
		
			return new RisorsaJSON(ja.toString(), counter.incrementAndGet());
		}
}
