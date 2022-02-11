package com.example.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerManagerSchede implements MyControllerIF{

	private JSONParser parser;
	private final AtomicLong counter = new AtomicLong();
	private Model model;
	private ControllerAggregatore aggr;
	
	public List<SchedaFitness> listaSchede; //Le schede prelevate dal DB vengono qui salvate per un accesso più rapido alla prossima richiesta
	
	private ControllerManagerSchede(){
		listaSchede = new ArrayList<>();
		parser = new JSONParser();
		model = Model.getModel();
		aggr= ControllerAggregatore.getController();
		}
		
	public static ControllerManagerSchede controller;
	public static ControllerManagerSchede getController() {controller = new ControllerManagerSchede(); return controller;}

	@Override
	@GetMapping("/getScheda")
	public RisorsaJSON getScheda(int id) {
		SchedaFitness scheda = searchScheda(id);
		if(scheda == null) {
			scheda = model.getSchedaByID(id);
			listaSchede.add(scheda);
			return parser.parseListaMacchinariEs(scheda.getLista(), counter);	
		}
		else return parser.parseListaMacchinariEs(scheda.getLista(), counter);
	}
	
	@Override
	@GetMapping("/aggiornaScheda")
	public RisorsaJSON aggiornaScheda(int id) {
		List<Macchinario> lista_mac = aggr.getStatoMacchinari(); 

		List<Macchinario> aggiornata = AlgoritmoGreedy(searchScheda(id), lista_mac);
		
		return parser.parseListaMacchinari(aggiornata,false, counter);
	}
	
	
	public List<Macchinario> AlgoritmoGreedy(SchedaFitness s, List<Macchinario> lm){
		
		List<Macchinario> aggiornata = new LinkedList<>();
		
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
			
			Macchinario M = macchinarioLibero(s.getEsercizio(i).getNome(), lm);
			if(M!=null) {aggiornata.add(M); continue;}  
			
			switch(s.getEsercizio(i).getTipologia()) {
				case "Braccia": aggiornata.add(braccia.poll()); break;
				case "Gambe": aggiornata.add(gambe.poll()); break;
				case "Pettorali": aggiornata.add(pettorali.poll()); break;
			}
		}
		return aggiornata;
	}
	
	private SchedaFitness searchScheda(int id) {
		for (Iterator<SchedaFitness> iterator = listaSchede.iterator(); iterator.hasNext();) {
			SchedaFitness s = (SchedaFitness) iterator.next();
			if(s.getID()==id) {return s;}
		}
		return null;
	}
	
	private Macchinario macchinarioLibero(String nome, List<Macchinario> lm) {
		for (Iterator<Macchinario> iterator = lm.iterator(); iterator.hasNext();) {
			Macchinario m = (Macchinario) iterator.next();
			if(m.getNome().equals(nome)) {
				LocalTime tempoMac = LocalTime.parse(m.getTempo());
				return (tempoMac.compareTo(LocalTime.now()) > 0) ? null : m;
			}			
		}
		
		// l'esercizio non ha un macchinario corrispondente: ritorno NULL 
		// così che l'algoritmo lo sostituisca con un macchinario libero
		return null;
	}
}
