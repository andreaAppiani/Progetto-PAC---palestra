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

	private AppView  view;
	
	public AppController(AppView v) {
		view = v;
		view.addListener(new getSchedulazione());
	}
	// Classe con metodo per "ascoltare" il pulsante che richiede la Scheda originale.
	// quando viene cliccato invia la richiesta al server, prende la risposta e la passa alla view tramite view.update()
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
			view.update(parsingMacchinari((String)o.get("lista")));	
		}	
	}
	//Stessa cosa ma per il pulsante che richiede la Schedulazione aggiornata
	class getSchedulazione implements ActionListener{
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
			view.update(parsingMacchinari((String)o.get("lista")));	
		}
	}
	
	// Metodo per convertire la stringa ottenuta come risposta del server
	// in un'effettiva lista di macchinari utilizzabile dal client (in particolare dalla View)
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
