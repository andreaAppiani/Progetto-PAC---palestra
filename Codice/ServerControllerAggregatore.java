package com.example.demo;

import java.util.ArrayList;
import java.util.List;

/*
 * ----------COMPONENTE MOCK----------
 * 
 * dovrebbe ricevere i dati da tutti i macchinari tenendo salvate in una lista
 * le informazioni aggiornate di ognuno nel formato < Nome, Tipologia, Timestamp >
 * dove il "timestamp" indica l'orario dal quale il componente è/sarà libero, calcolato e inviato
 * autonomamente da ogni macchinario quando viene premuto il tasto occupa o libera.
 */

public class ControllerAggregatore {

	static private List<Macchinario> listaM = new ArrayList<>(); 
	
	public ControllerAggregatore() {
		Macchinario m1 = new Macchinario("Panca piana","Pettorali","10:15:00");
		Macchinario m2 = new Macchinario("Colonna","Braccia","10:17:00");
		Macchinario m3 = new Macchinario("Colonna","Braccia","10:10:00");
		Macchinario m4 = new Macchinario("Leg-press","Gambe","10:20:30");
		Macchinario m5 = new Macchinario("Leg-press","Gambe","10:15:30");
		
		listaM.removeAll(listaM);
		listaM.add(m1);listaM.add(m2);listaM.add(m3);listaM.add(m4);listaM.add(m5);
	}
	
	List<Macchinario> getStatoMacchine(){
		return listaM;
	}
	
}
