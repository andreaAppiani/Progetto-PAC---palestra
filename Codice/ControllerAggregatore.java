package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ----------COMPONENTE MOCK----------
 * 
 * dovrebbe ricevere i dati da tutti i macchinari tenendo salvate in una lista
 * le informazioni aggiornate di ognuno nel formato < Nome, Tipologia, Timestamp >
 * dove il "timestamp" indica l'orario dal quale il componente è/sarà libero, calcolato e inviato
 * autonomamente da ogni macchinario quando viene premuto il tasto occupa o libera.
 */
@RestController
public class ControllerAggregatore implements ControllerAggregatoreIF {

	@Autowired
	private MyServiceJSON service = new MyServiceJSON();
	private final AtomicLong counter = new AtomicLong();
	
	static private List<Macchinario> listaM = new ArrayList<>(); 
	
	public ControllerAggregatore() {
		Macchinario m1 = new Macchinario("Panca piana","Pettorali","10:15:00");
		Macchinario m2 = new Macchinario("Colonna","Braccia","10:17:00");
		Macchinario m3 = new Macchinario("Colonna","Braccia","10:10:00");
		Macchinario m4 = new Macchinario("Leg-press","Gambe","10:20:30");
		Macchinario m5 = new Macchinario("Leg-press","Gambe","10:15:30");
		Macchinario m6 = new Macchinario("Panca inclinata","Pettorali","10:16:05");
		
		listaM.removeAll(listaM);
		listaM.add(m1);listaM.add(m2);listaM.add(m3);listaM.add(m4);listaM.add(m5);listaM.add(m6);
	}
	
	
	public List<Macchinario> getStatoMacchinari(){
		return listaM;
	}
	
	@GetMapping("/getStatoMacchinari")
	public RisorsaJSON getStatoMacchinariRemoto(){
		return service.rispondi(listaM, counter);
	}
}
