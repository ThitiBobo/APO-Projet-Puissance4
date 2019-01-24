package com.apo.puissance4;

public class ParametrePartie {
	
	// **********************************************
	// 					Attributs
	// **********************************************
	/**
	 * nombre de joueur dans la partie, de base c'est deux
	 * mais on peux imaginer plus ^^
	 */
	private int _nbJoueur;
	/**
	 * index du joeur jouant dans la liste de tout les joueurs
	 */
	private int _indexPremierJoueur;
	/**
	 * la liste des joueurs jouant dans la partie
	 */
	private Joueur[] _joueurs;	
	/**
	 * le nombre de jeton à aligner dans la grille pour gagner
	 */
	private int _conditionVictoire;
	/**
	* nombre de partie qui vont être jouer par les joueurs.
	 */
	private int _nbPartie;


}
