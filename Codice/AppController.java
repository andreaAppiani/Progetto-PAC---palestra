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
	
	public AppController(AppView v) {
		view = v;
		view.addListenerAccedi(new Autenticazione());
		view.addListenerSchedaOriginale(new getScheda());
		view.addListenerSchedaAggiornata(new getSchedaAggiornata());
	}
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
            view.switchPanel("schermata scheda");
			view.updateTabella(parsingMacchinari((String)o.get("lista")));	
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
            JSONObject o = new JSONObject(s);
            view.switchPanel("schermata scheda");
			view.updateTabella(parsingMacchinari((String)o.get("lista")));	
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
				getScheda get = new getScheda(); 
				get.actionPerformed(e); //va a eseguire la normale richiesta per la Scheda Originale
				}
			else view.loginErrato();
			}
		}
	
	private List<Macchinario> parsingMacchinari(String s) {
		JSONArray Array = new JSONArray(s);
		List<Macchinario> lista = new ArrayList<>();
		for(int n = 0; n < Array.length(); n++)
		{
		    JSONObject obj = Array.getJSONObject(n);
		    Macchinario m = new Macchinario();
		    m.setNome(obj.getString("nome"));
			m.setTipo(obj.getString("tipo"));
			m.setTempo(obj.getString("tempo"));
			lista.add(m);
		}
		return lista;
	}
}
