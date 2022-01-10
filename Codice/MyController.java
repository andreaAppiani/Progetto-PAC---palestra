package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController implements MyControllerIF {

	@Autowired
	private MyServiceJSON service = new MyServiceJSON();
	
	private final AtomicLong counter = new AtomicLong();
	
	private MyModel modello = new MyModel();
	private ControllerAggregatore aggr= new ControllerAggregatore();
	
	@GetMapping("/getScheda")
	public RisorsaJSON getScheda(int id) {
		return service.rispondi(modello.getSchedaFitness(id).getLista(), counter);
	}
	
	@GetMapping("/aggiornaScheda")
	public RisorsaJSON aggiornaScheda(int id) {
		
		// va a prendere i dati attuali dei macchinari dall'Aggregatore
		List<Macchinario> lista_mac = aggr.getStatoMacchinari(); 
		
		List<Macchinario> aggiornata = AlgoritmoGreedy(modello.getSchedaFitness(id), lista_mac);
		
		return service.rispondi(aggiornata, counter);
	}
	
	
	private List<Macchinario> AlgoritmoGreedy(SchedaFitness s, List<Macchinario> lm){
		
		List<Macchinario> aggiornata = new ArrayList<>();
		
		PriorityQueue<Macchinario> braccia = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> gambe = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> pettorali = new PriorityQueue<>(new MyComparator());
		
		for (Iterator<Macchinario> iterator = lm.iterator(); iterator.hasNext();) {
			Macchinario x = (Macchinario) iterator.next();
			switch(x.getTipo()) {
				case "Braccia": braccia.add(x); break; 
				case "Gambe": gambe.add(x); break;
				case "Pettorali": pettorali.add(x); break;
			}
		}
		
		for(int i=0; i<s.getLunghezzaLista();i++) {
			switch(s.getMacchinario(i).getTipo()) {
				case "Braccia": aggiornata.add(braccia.poll()); break;
				case "Gambe": aggiornata.add(gambe.poll()); break;
				case "Pettorali": aggiornata.add(pettorali.poll()); break;
			}
		}
		return aggiornata;
	}
}
