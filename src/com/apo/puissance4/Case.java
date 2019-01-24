package com.apo.puissance4;

import com.apo.puissance4.exception.CaseNotEmptyException;

public class Case {

	/**
	 * Jeton contenue (ou non) dans la case 
	 */
	private Jeton _jeton;
	
	/**
	 * V�rifie si la case ne contient aucune instance de 
	 * type @see Jeton, renvoie un boolean
	 * @return true si la case est vide, sinon false
	 */
	public boolean isEmpty() {
		return _jeton == null?true:false;
	}
	
	/**
	 * Ajoute un jeton dans la case si celle-ci n'est pas d�ja
	 * occup�e par un autre jeton
	 * @param le jeton à ajouter
	 * @throws CaseNotEmptyException si la case est d�ja occup�
	 * @throws IllegalArgumentException si le jeton n'est pas instanci�
	 */
	public void ajouterJeton(Jeton jeton) throws CaseNotEmptyException, IllegalArgumentException {
		if(!isEmpty())
			throw new CaseNotEmptyException();
		if(jeton == null)
			throw new IllegalArgumentException();
		_jeton = jeton;
	}

	/**
	 * R��criture de la m�thode toString
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [_jeton=").append(_jeton).append("]");
		return builder.toString();
	}
	
	

}
