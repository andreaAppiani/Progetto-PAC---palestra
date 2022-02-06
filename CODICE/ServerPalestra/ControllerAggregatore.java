package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ----------COMPONENTE MOCK----------
 * 
 * dovrebbe ricevere periodicamente info dai macchinari, immagazzinandole
 * nel formato < Nome, Tipologia, Timestamp > dove il "timestamp" indica 
 * l'orario dal quale il componente è/sarà libero, calcolato e inviato
 * autonomamente da ogni macchinario quando viene premuto il tasto occupa o libera.
 */

@RestController
public class ControllerAggregatore implements InfoMachineriIF,StatusIF {

	private JSONParser parser = new JSONParser();
	private final AtomicLong counter = new AtomicLong();
	
	static private List<Macchinario> listaM = new ArrayList<>(); 
	
	public static ControllerAggregatore controller;
	public static ControllerAggregatore getController() {
		controller = new ControllerAggregatore();
		return controller;
				}
	private ControllerAggregatore() {}
	
	public List<Macchinario> getStatoMacchinari(){
		return listaM;
	}
	
	@GetMapping("/getStatoMacchinari")
	public RisorsaJSON getStatoMacchinariRemoto(){
		return parser.parseListaMacchinari(listaM,true, counter);
	}
	
	@GetMapping("/writeStatoMacchinario")
	public void writeStatoMacchinario(Macchinario m){
		boolean trovato = false;
		for (Iterator<Macchinario> iterator = listaM.iterator(); iterator.hasNext();) {
			Macchinario mac = (Macchinario) iterator.next();
			if(mac.getNome().equals(m.getNome())) {
				mac.setTipo(m.getTipo()); mac.setTempo(m.getTempo()); trovato = true;
			}
		}
		if(trovato==false) listaM.add(m);
	}
}



