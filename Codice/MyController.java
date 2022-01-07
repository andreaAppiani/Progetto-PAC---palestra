package com.example.demo;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {

	@Autowired
	private MyServiceJSON service = new MyServiceJSON();
	
	private final AtomicLong counter = new AtomicLong();
	
	private MyModel modello = new MyModel();
	private ControllerAggregatore aggr= new ControllerAggregatore();
	
	@GetMapping("/getScheda")
	public RisorsaJSON getScheda(int id) {
		return service.rispondi(modello.getSchedaFitness(id), counter);
	}
	
	@GetMapping("/aggiornaScheda")
	public RisorsaJSON aggiornaScheda(int id) {
		
		// va a prendere i dati attuali dei macchinari dall'Aggregatore
		List<Macchinario> lista_mac = aggr.getStatoMacchine(); 
		
		SchedaFitness scheda_ottimale = AlgoritmoGreedy(modello.getSchedaFitness(id), lista_mac);
		
		return service.rispondi(scheda_ottimale, counter);
	}
	
	
	private SchedaFitness AlgoritmoGreedy(SchedaFitness s, List<Macchinario> lm){
		
		SchedaFitness aggiornata = new SchedaFitness(s.getID(), "vuota");
		
		PriorityQueue<Macchinario> braccia = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> gambe = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> pettorali = new PriorityQueue<>(new MyComparator());
		
		for (Iterator<Macchinario> iterator = lm.iterator(); iterator.hasNext();) {
			Macchinario x = (Macchinario) iterator.next();
			switch(x.getTipo()) {
				case "Braccia": braccia.add(x); break; //potrei aggiungere un controllo per cui fa "add" solo se non è guasto
				case "Gambe": gambe.add(x); break;
			}
		}
		
		for(int i=0; i<s.getLunghezzaLista();i++) {
			switch(s.getMacchinario(i).getTipo()) {
				case "Braccia": aggiornata.addMacchinario(braccia.poll()); break;
				case "Gambe": aggiornata.addMacchinario(gambe.poll()); break;
				case "Pettorali": aggiornata.addMacchinario(pettorali.poll()); break;
			}
		}
		return aggiornata;
	}
}
