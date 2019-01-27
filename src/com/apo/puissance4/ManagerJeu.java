package com.apo.puissance4;

import java.util.ArrayList;
import java.util.Collections;

import com.apo.puissance4.exception.FullColumnException;
import com.apo.puissance4.exception.KeyDoNotExistException;

/**
 * Représente une partie de puissance4, en gérant les joueurs, le nombre de
 * parties et les conditions de victoire
 * @author Thibaud DELPLANQUE, Akram BOUQSIMI, Dorian NAAJI
 */
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
	/**
	 * Obtient le nombre de joueurs dans la partie
	 * @return le nombre de joueurs en int
	 */
	public int getNbJoueur() {
		return _nbJoueur;
	}
	/**
	 * Obtient l'index du joueur courant dans la partie
	 * index compris entre 0 et nbJoueur - 1
	 * @return l'index en int
	 */
	public int getIndexJoueurCourrant() {
		return _indexJoueurCourrant;
	}
	/**
	 * Obtient le nombre de piont à aligner pour 
	 * gagner une manche
	 * @return nombre d'alignement en int
	 */
	public int getConditionVictoire() {
		return _conditionVictoire;
	}
	/**
	 * Obtient le nombre de manche dans la partie
	 * @return le nombre de manche
	 */
	public int getNbPartie() {
		return _nbPartie;
	}
	/**
	 * Obtient le nombre de manche restante à jouer
	 * @return retourne un int qui est le nombre de manche restante
	 */
	public int getNbPartieRestante() {
		return _nbPartieRestante;
	}
	/**
	 * Obtient le numéro de la manche en cours
	 * numéro compris entre 1 et nbPartie
	 * @return retourne un int
	 */
	public int getNumPartie() {
		return _nbPartie - _nbPartieRestante + 1;
	}
	/**
	 * Obtient le nom du joueur courrant
	 * @return retourne un string
	 */
	public String getNomJoueurCourrant() {
		return _joueurCourrant.getNom();
	}
	/**
	 * Obtient le symbole du joueur courrant
	 * @return retourne un char
	 */
	public char getSymboleJoueurCourrant() {
		return _joueurCourrant.getSymbole();
	}
	/**
	 * Obtient le joueur à l'indice précisé, 
	 * indice compris entre 0 et nbJoueurs - 1
	 * @param index du joueur
	 * @return retourne l'instance du joueur à l'indice demandé
	 */
	public Joueur getJoueur(int index) {
		return _joueurs[index];
	}
	/**
	 * Obtient le grille de jeu de la partie
	 * @return retourne une instance de GrilleJeu
	 */
	public GrilleJeu getGrille() {
		return _grille;
	}
	/**
	 * Permet de créer une nouvelle grille de jeu avec 
	 * une taille
	 * @param nbColonnes, le nombre de colonnes
	 * @param nbLignes, le nombrede lignes
	 */
	public void setGrille(int nbColonnes, int nbLignes) {
		_grille = new GrilleJeu(nbColonnes, nbLignes);
	}
	/**
	 * Permet d'initialisé le nombre de joueur
	 * @param nbJoueur, lenombre de joueur
	 */
	public void setNbJoueur(int nbJoueur) {
		this._nbJoueur = nbJoueur;
		_joueurs = new Joueur[_nbJoueur];
		for (int i = 0; i < _nbJoueur; i++) {
			_joueurs[i] = new Joueur();
			_joueurs[i].setTouche(_grille.getNbColonnes());
		}
		setJoueurCourant(0);
	}
	/**
	 * Permet d'initialisé le numéro du joueur courrant
	 * @param indexJoueur, numéro du joueur
	 */
	public void setJoueurCourant(int indexJoueur) {
		indexJoueur = indexJoueur % _nbJoueur;
		_indexJoueurCourrant = indexJoueur;
		_joueurCourrant = _joueurs[indexJoueur];
	}
	/**
	 * Permet d'initialisé le nombre de pièces à aligner pour gagner
	 * @param conditionVictoire, nombre de pièces à alignées
	 */
	public void setConditionVictoire(int conditionVictoire) {
		this._conditionVictoire = conditionVictoire;
	}
	/**
	 * Permet d'initialisé le nombre de manche à jouer
	 * @param nbPartie, le nombre de manches
	 */
	public void setNbPartie(int nbPartie) {
		this._nbPartie = nbPartie;
		this._nbPartieRestante = nbPartie;
	}
	
	// **********************************************
	// 				  Constructeurs
	// **********************************************
	
	/**
	 * Initialise une nouvelle instance de ManagerJeu, représentant 
	 * une partie de puissance4 classique, 2 joueurs, une grille de 6x7, ...
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
	 * Permet de placé un jeton à la case damandé, le paramètre touche en entrée
	 * doit correspondre à un des racourcis clavier du joueur courrant. 
	 * @param touche, string représentant un des racourcis du joueur courrant
	 * @throws FullColumnException si la colonne choisie est pleinne, une exception de type FullColumnException est renvoyé 
	 * @throws KeyDoNotExistException si la touche n'existe pas, une exception de type KeyDoNotExistException est renvoyé 
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
	}
	/**
	 * Vérifie si le joueur courrant à gagné la manche en cours,
	 * s'il à gagné, le joueur courrant marque un point et on arrete de passé au joueur d'après
	 * sinon le joueur courrant est incrémenté.
	 * @return retourne true si la manche est gagné, sinon false
	 */
	public boolean partieFinie() {
		boolean test = _grille.verifVictoire(_joueurCourrant, _conditionVictoire) || _grille.isFull();
		if(!test) 
			setJoueurCourant(++_indexJoueurCourrant);
		return test;
	}
	
	/**
	 * Initialise une nouvelle manche
	 */
	public void nouvellePartie() {
		_grille.resetGrille();
		_nbPartieRestante--; 
		_joueurCourrant.addScore(1);
	}
	/**
	 * Permet de récupéré le classement des joueurs sur la partie
	 * @return retourne un string
	 */
	public String infoScore() {
		
		ArrayList<Joueur> classement = new ArrayList<Joueur>();
		for (int i = 0; i < _joueurs.length; i++) {
			classement.add(_joueurs[i]);
		}
		Collections.sort((java.util.List<Joueur>) classement);
		
		String infoScore = "";
		infoScore += "Le Joueur " + classement.get(0).getNom() + " est le grand vainqueur !\n";
		infoScore += "score :  " + classement.get(0).getScore() + " / " + this._nbPartie + "gagnée(s)\n";
		infoScore += "----  classment ----\n";
		for (int i = 0; i < classement.size(); i++) {
			infoScore += " - " + i + " : " + classement.get(i).getNom() + " " 
					+  classement.get(i).getScore() + " / " + this._nbPartie + "gagnée(s)\n";
		}
		return infoScore;
		
	}
	
	/**
	 * retourne le rendu graphique du jeur sous forme de String
	 * @return retourne un string
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
