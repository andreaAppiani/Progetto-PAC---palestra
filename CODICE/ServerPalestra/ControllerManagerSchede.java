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
public class ControllerManagerSchede implements ControllerManagerSchedeIF{

	private JSONParser parser;
	private final AtomicLong counter = new AtomicLong();
	
	private MyModel model;
	private ControllerAggregatore aggr;
	
	// Lista delle schede fitness prelevate dal DB; 
	// permette di ottenerle più velocemente che dal DB per ogni successiva richiesta di aggiornamento.
	public List<SchedaFitness> listaSchede;
	
	private ControllerManagerSchede(){
		listaSchede = new ArrayList<>();
		parser = new JSONParser();
		model = MyModel.getModel();
		aggr= ControllerAggregatore.getController();
		}
		
	public static ControllerManagerSchede controller = null;
	public static ControllerManagerSchede getController() {
		if(controller == null) controller = new ControllerManagerSchede(); 
		return controller;}

	@Override
	@GetMapping("/getScheda")
	public RisorsaJSON getScheda(int id) {
		SchedaFitness scheda = searchScheda(id);
		if(scheda == null) {
			scheda = model.getSchedaByID(id);
			listaSchede.add(scheda);
			System.out.println("Scheda ritornata: " + scheda.getLista().toString());
			return parser.parseListaEsercizi(scheda.getLista(), counter);	
		}
		else return parser.parseListaEsercizi(scheda.getLista(), counter);
	}
	
	@Override
	@GetMapping("/aggiornaScheda")
	public RisorsaJSON aggiornaScheda(int id) {
		List<Macchinario> lista_mac = aggr.getStatoMacchinari(); 

		List<Esercizio> aggiornata = AlgoritmoGreedy(searchScheda(id), lista_mac);
		
		return parser.parseListaEsercizi(aggiornata, counter);
	}
	
	
	List<Esercizio> AlgoritmoGreedy(SchedaFitness s, List<Macchinario> lm){
		
		System.out.println("Scheda trovata: " + s.getLista().toString());
		
		List<Esercizio> aggiornata = new LinkedList<>();
		
		PriorityQueue<Macchinario> bicipiti = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> gambe = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> pettorali = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> tricipiti = new PriorityQueue<>(new MyComparator());
		PriorityQueue<Macchinario> dorsali = new PriorityQueue<>(new MyComparator());
		
		for (Iterator<Macchinario> iterator = lm.iterator(); iterator.hasNext();) {
			Macchinario x = (Macchinario) iterator.next();
			switch(x.getTipo()) {
				case "Bicipiti": bicipiti.add(x); break; 
				case "Tricipiti": tricipiti.add(x); break; 
				case "Dorsali": dorsali.add(x); break; 
				case "Gambe": gambe.add(x); break;
				case "Pettorali": pettorali.add(x); break;
			}
		}
		
		for(int i=0; i<s.getLunghezzaLista();i++) {
			Esercizio E = s.getEsercizio(i);
			Macchinario M = macchinarioLibero(E.getNome(), lm);
			if(M!=null) {aggiornata.add(new Esercizio(M.getNome(), M.getTipo(), E.getSerie())); continue;}  
			
			Macchinario X;
			switch(s.getEsercizio(i).getTipologia()) {
				case "Bicipiti": 
					X = bicipiti.poll(); 
					if(X == null || X.getTempo().equals("23:59:59")) {aggiornata.add(E); break;}
					else {aggiornata.add(new Esercizio(X.getNome(), X.getTipo(), E.getSerie())); break;}
				
				case "Tricipiti": 
					X = tricipiti.poll(); 
					if(X == null || X.getTempo().equals("23:59:59")) {aggiornata.add(E); break;}
					else {aggiornata.add(new Esercizio(X.getNome(), X.getTipo(), E.getSerie())); break;}
				
				case "Dorsali": 
					X = dorsali.poll(); 
					if(X == null || X.getTempo().equals("23:59:59")) {aggiornata.add(E); break;}
					else {aggiornata.add(new Esercizio(X.getNome(), X.getTipo(), E.getSerie())); break;}
					
				case "Gambe": 
					X = gambe.poll(); 
					if(X == null || X.getTempo().equals("23:59:59")) {aggiornata.add(E); break;}
					else {aggiornata.add(new Esercizio(X.getNome(), X.getTipo(), E.getSerie())); break;}
				
				case "Pettorali": 
					X = pettorali.poll(); 
					if(X == null || X.getTempo().equals("23:59:59")) {aggiornata.add(E); break;}
					else {aggiornata.add(new Esercizio(X.getNome(), X.getTipo(), E.getSerie())); break;}
			}
		}
		return aggiornata;
	}
	
	public SchedaFitness searchScheda(int id) {
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
		return null; 
		// Ritorno NULL se l'esercizio non ha un macchinario corrispondente; 
		// lascio che l'algoritmo lo sostituisca con un macchinario libero
	}
}
