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
	

	// **********************************************
	// 				Getters Setters
	// **********************************************
	
	public int getNbJoueur() {
		return _nbJoueur;
	}

	public int getIndexJoueurCourrant() {
		return _indexJoueurCourrant;
	}

	public Joueur[] getJoueurs() {
		return _joueurs;
	}

	public Joueur getJoueurCourrant() {
		return _joueurCourrant;
	}

	public GrilleJeu getGrille() {
		return _grille;
	}

	public int getConditionVictoire() {
		return _conditionVictoire;
	}

	public int getNbPartie() {
		return _nbPartie;
	}

	public int getNbPartieRestante() {
		return _nbPartieRestante;
	}

	public void setNbJoueur(int _nbJoueur) {
		this._nbJoueur = _nbJoueur;
	}

	public void setIndexJoueurCourrant(int _indexJoueurCourrant) {
		this._indexJoueurCourrant = _indexJoueurCourrant;
	}

	public void setJoueurs(Joueur[] _joueurs) {
		this._joueurs = _joueurs;
	}

	public void setJoueurCourrant(Joueur _joueurCourrant) {
		this._joueurCourrant = _joueurCourrant;
	}

	public void setGrille(GrilleJeu _grille) {
		this._grille = _grille;
	}

	public void setConditionVictoire(int _conditionVictoire) {
		this._conditionVictoire = _conditionVictoire;
	}

	public void setNbPartie(int _nbPartie) {
		this._nbPartie = _nbPartie;
	}

	public void setNbPartieRestante(int _nbPartieRestante) {
		this._nbPartieRestante = _nbPartieRestante;
	}
	
	
	// **********************************************
	// 					Méthodes
	// **********************************************

	/**
	 * 
	 */
	public void Jouer() {
		
	}
	
	/**
	 * retourne le rendu graphique du jeur sous forme de String
	 * @return
	 */
	public String affichageGrille() {
		return null;
	}
}
