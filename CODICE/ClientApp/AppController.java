package com.example.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class AppController {

	private AppView view;
	
	public static AppController controller;
	
	private AppController(AppView v) {
		view = v;
		view.addListenerAccedi(new Autenticazione());
		view.addListenerSchedaOriginale(new getScheda());
		view.addListenerSchedaAggiornata(new getSchedaAggiornata());
		view.addListenerLogout(new Logout());
	}
	
	public static AppController getController(AppView view) {controller = new AppController(view); return controller;}
	
	class getScheda implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = "";
			URL url;
			try {
				url = new URL("http://localhost:8080/getScheda?id=1");
				
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {sb.append(line);}
	            bufferedReader.close();
	            s = sb.toString();
			}
			catch (Exception ex) { System.out.println("ECCEZIONE catturata: " + ex); }
            JSONObject o = new JSONObject(s);   
			view.updateTabella(parsingEsercizi((String)o.get("lista")));
		}	
	}
	
	class getSchedaAggiornata implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = "";
			URL url;
			try {
				url = new URL("http://localhost:8080/aggiornaScheda?id=1");
				
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {sb.append(line);}
	            bufferedReader.close();
	            s = sb.toString();
			}
			catch (Exception ex) { System.out.println("ECCEZIONE catturata: " + ex); }
			
			if(s.charAt(0)!='{') { System.out.println("Risposta server errata: " + s); return; }
            JSONObject o = new JSONObject(s);
            view.switchPanel("schermata scheda");
			view.updateTabella(parsingEsercizi((String)o.get("lista")));	
		}
	}
	
	class Autenticazione implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = "";
			URL url;
			String MyUrl = "http://localhost:8080/autentica?" + "nome=" + view.getNome() + "&password=" + view.getPassword();
			try {
				url = new URL(MyUrl);			
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {sb.append(line);}
	            bufferedReader.close();
	            s = sb.toString();
			}
			catch (Exception ex) { System.out.println("ECCEZIONE catturata: " + ex); }
			
			if(s.equals("AUTENTICATO")) {
				view.switchPanel("schermata scheda");
				getScheda get = new getScheda(); 
				get.actionPerformed(e); //va a eseguire la normale richiesta per la Scheda Originale
				}
			else view.loginErrato();
			}
		}
	
	class Logout implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			view.switchPanel("schermata login");
		}	
	}
	
	public List<Esercizio> parsingEsercizi(String s) {
		System.out.println("o.get(lista) ottenuta: " + s);
		JSONArray Array = new JSONArray(s);
		List<Esercizio> lista = new ArrayList<>();
		
		for(int n = 0; n < Array.length(); n++)
		{
		    JSONObject obj = Array.getJSONObject(n);
		    Esercizio e = new Esercizio();
		    e.setNome(obj.getString("nome"));
			e.setTipologia(obj.getString("tipo"));
			lista.add(e);
		}
		return lista;
	}
}
