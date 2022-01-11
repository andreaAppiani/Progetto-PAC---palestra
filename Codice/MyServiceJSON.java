package com.example.demo;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MyServiceJSON {
	
	//Riceve la Scheda Ottimale appena calcolata dal Controller, la converte in JSON e invia
	
	public RisorsaJSON rispondi(List<Macchinario> l, AtomicLong counter) {
		
		JSONArray ja = new JSONArray();
		
		for(int i=0; i<l.size(); i++) {
			JSONObject o = new JSONObject();
			o.put("nome", l.get(i).getNome());
			o.put("tipo", l.get(i).getTipo());
			o.put("tempo", l.get(i).getTempo());
			ja.put(o);
		}
	
		return new RisorsaJSON(ja.toString(), counter.incrementAndGet());
	}

}
