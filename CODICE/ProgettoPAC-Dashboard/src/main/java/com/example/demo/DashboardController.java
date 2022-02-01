package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardController {

	private DashboardView view;
	
	public static DashboardController controller;
	private DashboardController(DashboardView V) {
		view = V;	
		ciclo();
		}
	public static DashboardController getController(DashboardView view) { controller = new DashboardController(view); return controller;}
	
	private void ciclo() {
		while(true) {
			getStatoMacchinari();	
			try {TimeUnit.SECONDS.sleep(5);} 
			catch (InterruptedException e) {e.printStackTrace();}
			}
	}
	
	private void getStatoMacchinari() {
		
		String s = "";
		URL url;
		try {
			url = new URL("http://localhost:8080/getStatoMacchinari");
			
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {sb.append(line);}
            bufferedReader.close();
            s = sb.toString();
		}
		catch (Exception ex) { System.out.println("ECCEZIONE catturata: " + ex); }
		System.out.println("STRINGA RICEVUTA: " + s);
        JSONObject o = new JSONObject(s);
		view.update(parsingMacchinari((String)o.get("lista")));	
	}

	public List<Macchinario> parsingMacchinari(String s) {
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
