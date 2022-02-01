package com.example.demo;

import java.util.List;

/**
 * The Interface ControllerAggregatoreIF.
 */
public interface InfoMachineriIF {

	/**
	 * Ritorna lo stato corrente dei macchinari.
	 *
	 * @return lista di macchinari
	 */
	List<Macchinario> getStatoMacchinari();
	
	/**
	 * Ritorna lo stato corrente dei macchinari.
	 *
	 * @return oggetto RisorsaJSON contenente un ID e una stringa
	 * rappresentante la lista di macchinari in formato JSON. 
	 */
	RisorsaJSON getStatoMacchinariRemoto();
	
}
