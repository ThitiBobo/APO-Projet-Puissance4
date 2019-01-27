package com.apo.puissance4;

/**
 * Repésente des instance de Jeton de puissance4
 * avec un symbole et un joueur a titré
 * @author Thibaut Delplanque, Akram Bouqisimi, Dorian Nadjiii
 *
 */
public class Jeton {
	
	// **********************************************
	// 					Attributs
	// **********************************************
	/**
	 * instance du propriétaire du jeton de type Joueur
	 */
	private Joueur _joueur;
	/**
	 * le symbole du jeton, utilisé lors d'un affichage console
	 */
	private char _symbole;
	
	// **********************************************
	// 				Getters Setters
	// **********************************************
	/**
	 * Permet de retourner le symbole du jeton
	 * @return retourne un caractère
	 */
	public char getSymbole() {
		return _symbole;
	}
	/**
	 * Permet de retourner l'instance du joueur, propriétaire
	 * du jeton
	 * @return retourne une instance de type Joueur
	 */
	public Joueur getJoueur() {
		return _joueur;
	}
	/**
	 * Permet de modifier le symbole du jeton
	 * @param symbole, le nouveau symbole
	 */
	public void setSymbole(char symbole) {
		this._symbole = symbole;
	}
	/**
	 * Permet de changer le propriétaire du jeton
	 * @param joueur, le nouveau joueur propriétaire
	 */
	public void setJoueur(Joueur joueur) {
		this._joueur = joueur;
	}

	// **********************************************
	// 				 Constructeurs
	// **********************************************
	/**
	 * Initialise une nouvelle instance de la classe Jeton,
	 * avec son joueur
	 * @param joueur propriétaire du jeton
	 */
	public Jeton(Joueur joueur) {
		this._joueur = joueur;
		this._symbole = joueur.getSymbole();

	}
	/**
	 * Initialise une nouvelle instance de la classe Jeton,
	 * avec un joueur et un symbole
	 * @param joueur propriétaire du jeton
	 * @param symbole du jeton 
	 */
	public Jeton(Joueur joueur, char symbole) {
		this._joueur = joueur;
		this._symbole = symbole;
	}

}
