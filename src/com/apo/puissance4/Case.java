package com.apo.puissance4;

import com.apo.puissance4.exception.CaseNotEmptyException;

/**
 * Représente des instances de cases de grille de puissance4,
 * pouvant contenir ou non un Jeton 
 * @author Thibaud DELPLANQUE, Akram BOUQSIMI, Dorian NAAJI
 *
 */
public class Case {

	// **********************************************
	// 					Attributs
	// **********************************************
	/**
	 * Jeton contenu (ou non) dans la case 
	 */
	private Jeton _jeton;
	
	// **********************************************
	// 				Getters Setters
	// **********************************************
	/**
     * Obtient le Jeton de la case (s'il existe)
     * @return : le Jeton de la case courante, ou retourne null
     */
	public Jeton getJeton(){
        return this._jeton;
    }

	// **********************************************
	// 					Méthodes
	// **********************************************
	/**
	 * Vérifie si la case ne contienr aucune instance de
	 * type Jeton, renvoie un boolean
	 * @return true si la case est vide, sinon false
	 */
	public boolean isEmpty() {
		return _jeton == null?true:false;
	}
	
	/**
	 * Ajoute un jeton dans la case si celle-ci n'est pas déja
	 * occupée par un autre jeton
	 * @param jeton, le jeton à ajouter
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
	 * Réécriture de la méthode toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [_jeton=").append(_jeton).append("]");
		return builder.toString();
	}

}
