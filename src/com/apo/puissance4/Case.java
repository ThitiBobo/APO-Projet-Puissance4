package com.apo.puissance4;

import com.apo.puissance4.exception.CaseNotEmptyException;

public class Case {

	/**
	 * Jeton contenue (ou non) dans la case 
	 */
	private Jeton _jeton;
	
	/**
	 * Vérifie si la case ne contient aucune instance de 
	 * type @see Jeton, renvoie un boolean
	 * @return true si la case est vide, sinon false
	 */
	public boolean isEmpty() {
		return _jeton == null?true:false;
	}
	
	/**
	 * Ajoute un jeton dans la case si celle-ci n'est pas déja
	 * occupée par un autre jeton
	 * @param le jeton à ajouter
	 * @throws CaseNotEmptyException si la case est déja occupé
	 * @throws IllegalArgumentException si le jeton n'est pas instancié
	 */
	public void ajouterJeton(Jeton jeton) throws CaseNotEmptyException, IllegalArgumentException {
		if(!isEmpty())
			throw new CaseNotEmptyException();
		if(jeton == null)
			throw new IllegalArgumentException();
		_jeton = jeton;
	}

	/**
	 * Réécriture de la méthode toString
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [_jeton=").append(_jeton).append("]");
		return builder.toString();
	}
	
	

}
