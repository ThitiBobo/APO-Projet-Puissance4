package com.apo.puissance4;

public class ManagerJeu {
	
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
	private int _indexJoueurCourrant;
	/**
	 * la liste des joueurs jouant dans la partie
	 */
	private Joueur[] _joueurs;
	/**
	 * l'instance du joueur courrant
	 */
	private Joueur _joueurCourrant;
	/**
	 * l'instance de la grille de jeu
	 */
	private GrilleJeu _grille;
	/**
	 * le nombre de jeton à aligner dans la grille pour gagner
	 */
	private int _conditionVictoire;
	/**
	 * nombre de partie qui vont être jouer par les joueurs.
	 */
	private int _nbPartie;
	/**
	 * nombre de partie restante à jouer.
	 */
	private int _nbPartieRestante;
	
	

}
