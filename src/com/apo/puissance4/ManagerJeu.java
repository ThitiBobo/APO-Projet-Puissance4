package com.apo.puissance4;

import com.apo.puissance4.exception.FullColumnException;
import com.apo.puissance4.exception.KeyDoNotExistException;

public class ManagerJeu {
	
	// **********************************************
	// 				Attributs statiques
	// **********************************************
	/**
	 * nombre de joueur par défaut
	 */
	private static int _nbJoueursDefaut = 2;
	/**
	 * nombre de pion à aligner par défaut
	 */
	private static int _conditionVictoireDefaut = 4;
	/**
	 * nombre de partie par défaut;
	 */
	private static int _nbPartieDefaut = 1;
	
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

	public int getConditionVictoire() {
		return _conditionVictoire;
	}

	public int getNbPartie() {
		return _nbPartie;
	}

	public int getNbPartieRestante() {
		return _nbPartieRestante;
	}
	
	public int getNumPartie() {
		return _nbPartie - _nbPartieRestante + 1;
	}
	
	public String getNomJoueurCourrant() {
		return _joueurCourrant.getNom();
	}

	public void setNbJoueur(int nbJoueur) {
		this._nbJoueur = nbJoueur;
		_joueurs = new Joueur[_nbJoueur];
		for (int i = 0; i < _nbJoueur; i++) {
			_joueurs[i] = new Joueur();
			_joueurs[i].setTouche(_grille.getNbColonnes());
		}
	}
	
	public void setJoueurCourant(int indexJoueur) {
		indexJoueur = indexJoueur % _nbJoueur;
		_indexJoueurCourrant = indexJoueur;
		_joueurCourrant = _joueurs[indexJoueur];
	}

	public void setConditionVictoire(int conditionVictoire) {
		this._conditionVictoire = conditionVictoire;
	}

	public void setNbPartie(int nbPartie) {
		this._nbPartie = nbPartie;
		this._nbPartieRestante = nbPartie;
	}
	
	// **********************************************
	// 				  Constructeurs
	// **********************************************
	
	/**
	 * 
	 */
	public ManagerJeu() {
		_grille = new GrilleJeu();
		setNbJoueur(_nbJoueursDefaut);
		setJoueurCourant(0);
		setNbPartie(_nbPartieDefaut);
		setConditionVictoire(_conditionVictoireDefaut);
		_joueurs[0].setSymbole('X');
		_joueurs[1].setSymbole('O');
	}

	
	// **********************************************
	// 					Méthodes
	// **********************************************

	/**
	 * @throws FullColumnException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	public void jouer(String touche) throws IllegalArgumentException, FullColumnException, KeyDoNotExistException {
		String[] touches = _joueurCourrant.getTouches();
		int index = -1;
		for (int i = 0; i < touches.length; i++) {
			if (touches[i].compareTo(touche) == 0)
				index = i;
		}
		if(index == -1)
			throw new KeyDoNotExistException();
		_grille.ajouterJeton(index, _joueurCourrant.getJeton());
		setJoueurCourant(++_indexJoueurCourrant);
	}
	
	public boolean partieFinie() {
		return _grille.vérifVictoire(_joueurCourrant, _conditionVictoire);
	}
	
	/**
	 * retourne le rendu graphique du jeur sous forme de String
	 * @return
	 */
	public String affichage() {
		// affichage de la grille
		String affichage = "";
		for (int i = _grille.getNbLignes() - 1; i >= 0 ; i--) {
			for (int j = 0; j < _grille.getNbColonnes(); j++) {
				char symbole = _grille.getSymbole(j, i);
				if (symbole == Character.MIN_VALUE)
					affichage += "| - ";
				else
					affichage += "| " + symbole + " ";
			}
			affichage += "|\n";
		}
		// affichage des racourcis clavier du joueur courrant.
		String[] touches = _joueurCourrant.getTouches();
		affichage += "  ";
		for (String touche : touches) {
			affichage += touche + "   ";
		}
		affichage += "\n";
		
		return affichage;
	}
}
