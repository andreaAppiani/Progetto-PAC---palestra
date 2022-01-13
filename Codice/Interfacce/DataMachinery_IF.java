package com.example.demo;

import java.util.List;

/**
 * The Interface ControllerAggregatoreIF.
 */
public interface ControllerAggregatoreIF {

	/**
	 * Ritorna lo stato corrente dei macchinari.
	 *
	 * @return lista contenente lo stato di ogni macchinario
	 */
	List<Macchinario> getStatoMacchinari();
	
	/**
	 * Ritorna lo stato corrente dei macchinari.
	 *
	 * @return lista contenente lo stato di ogni macchinario in formato JSON (con associato l'id della richiesta)
	 */
	RisorsaJSON getStatoMacchinariRemoto();
	
}
