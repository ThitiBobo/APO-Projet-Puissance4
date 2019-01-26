package com.apo.puissance4;

/**
 * Permet de créer des instances représentant des juoeurs de puissance4,
 *  définit par un nom, un score, un symbole de pion, ect...
 * @author Thibaut Delplanque 
 */
public class Joueur {
	
	// **********************************************
	// 					Attributs
	// **********************************************
	/**
	 * le symbole du jeton du joueur
	 */
	private char _symbole;
	/**
	 * enregistre le score de la partie courrante
	 */
	private int _score;
	/**
	 * permet d'enregistrer le nombre de partie gagner depuis le début
	 */
	private int _scoreTotal;
	/**
	 * nom du joueur 
	 */
	private String _nom;
	/**
	 * tableau représentant les saisie que doit faire le joueur pour jouer dans
	 * telle colonne, l'indice de la colonne est retrouvé avec l'indice du tableau
	 */
	private String[] _toucheColonnes;
	
	// **********************************************
	// 				Getters Setters
	// **********************************************
	
	public char getSymbole() {
		return _symbole;
	}
	
	public int getScore() {
		return _score;
	}
	
	public String[] getTouches() {
		return _toucheColonnes;
	}
	
	public void setTouche(int nbTouches) {
		_toucheColonnes = new String[nbTouches];
		for (int i = 0; i < nbTouches; i++) {
			_toucheColonnes[i] = String.valueOf(i + 1);
		}
	}
	
	public String getNom() {
		return _nom;
	}
	
	public Jeton getJeton() {
		return new Jeton(this,_symbole); 
	}
	
	public void setSymbole(char symbole) {
		_symbole = symbole;
	}
	
	// **********************************************
	// 				Constructors
	// **********************************************

	/**
	 * Instancie une nouvelle instance de Joueur avec tout les paramètres
	 * @param le symbole des jetons du joueur
	 * @param le nombre de partie gagnée depuis le début
	 * @param son nom
	 * @param les saisie racourcis clavier du joueur 
	 */
	public Joueur(String nom, char symbole, int scoreTotal, String[] toucheColonnes) {
		this._symbole = symbole;
		this._scoreTotal = scoreTotal;
		this._nom = nom;
		this._toucheColonnes = toucheColonnes;
	}
	
	
	public Joueur(int score, char symbole) {
		_score = score;
		setSymbole(symbole);
	}

	/**
	 * @param _symbole
	 */
	public Joueur(char symbole) {
		this(0, symbole);
	}
	
	public Joueur() {}
	
	public void addScore(int score) {
		_score += score;
	}
	

}
