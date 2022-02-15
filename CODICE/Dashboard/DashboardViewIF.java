package com.example.demo;

import java.util.List;

/**
 * The Interface DashboardViewIF.
 */
public interface DashboardViewIF {

	/**
	 * Aggiorna la View della dashboard con le informazioni sui macchinari passati
	 * per parametro.
	 *
	 * @param lista dei macchinari
	 */
	void update(List<Macchinario> lista);

}