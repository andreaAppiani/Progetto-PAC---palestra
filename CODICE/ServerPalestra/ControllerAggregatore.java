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
	
	public static ControllerAggregatore controller = null;
	public static ControllerAggregatore getController() {
		if(controller == null) controller = new ControllerAggregatore();
		return controller;
				}
	private ControllerAggregatore() {
		// per motivi di Testing, questi simulano gli stati attuali (fissi) dei macchinari
		
		Macchinario m1 = new Macchinario("Panca Piana","Pettorali","17:00:00");
		Macchinario m2 = new Macchinario("Panca Inclinata","Pettorali","09:00:00");
		
		Macchinario m3 = new Macchinario("Pulley","Dorsali","10:17:00");
		Macchinario m4 = new Macchinario("Lat Machine","Dorsali","10:17:00");
		Macchinario m5 = new Macchinario("Trazioni al Castello","Dorsali","23:59:59");
		
		Macchinario m6 = new Macchinario("Curl Machine","Bicipiti","09:17:00");	
		
		Macchinario m7 = new Macchinario("Military-Press","Tricipiti","17:10:00");
		Macchinario m8 = new Macchinario("Colonna con Cavi","Tricipiti","14:10:00");
		
		Macchinario m9 = new Macchinario("Leg-Press","Gambe","16:20:30");
		Macchinario m10 = new Macchinario("Leg-Extension","Gambe","23:59:59");	
		Macchinario m11 = new Macchinario("Leg-Curl","Gambe","10:10:30");
		
		listaM.removeAll(listaM);
		listaM.add(m1);listaM.add(m2);listaM.add(m3);listaM.add(m4);listaM.add(m5);
		listaM.add(m6);	listaM.add(m7);listaM.add(m8);listaM.add(m9);listaM.add(m10);listaM.add(m11);
	}
	
	
	public List<Macchinario> getStatoMacchinari(){
		return listaM;
	}
	
	@GetMapping("/getStatoMacchinari")
	public RisorsaJSON getStatoMacchinariRemoto(){
		return parser.parseListaMacchinari(listaM, counter);
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



