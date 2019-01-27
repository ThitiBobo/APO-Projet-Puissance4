package com.apo.puissance4;

/**
 * Permet de créer des instances représentant des juoeurs de puissance4,
 * définit par un nom, un score, un symbole de pion, ect...
 * @author Thibaud DELPLANQUE, Akram BOUQSIMI, Dorian NAAJI
 */
public class Joueur implements Comparable<Joueur>{
	
	// **********************************************
	// 					Attributs
	// **********************************************
	/**
	 * le symbole du jeton du joueur
	 */
	private char _symbole;
	/**
	 * enregistre le score de la partie courante
	 */
	private int _score;
	/**
	 * permet d'enregistrer le nombre de parties gagnées depuis le début
	 */
	private int _scoreTotal;
	/**
	 * nom du joueur 
	 */
	private String _nom;
	/**
	 * tableau représentant les saisies que doit faire le joueur pour jouer dans
	 * telle colonne, l'indice de la colonne est retrouvé avec l'indice du tableau
	 */
	private String[] _toucheColonnes;
	
	// **********************************************
	// 				Getters Setters
	// **********************************************
	
	/**
	 * Obtient le symbole assigné au joueur 
	 * @return retourne le symbole sous forme de caractère
	 */
	public char getSymbole() {
		return _symbole;
	}
	/**
	 * Obtient le score de la partie courante
	 * @return retourne un entier
	 */
	public int getScore() {
		return _score;
	}
	/**
	 * Obtient le score du joueur depuis le début
	 * (score qu'il a cumulé durant toutes ses parties)
	 * @return retourne un entier
	 */
	public int getScoreTotal() {
		return _scoreTotal;
	}
	/**
	 * Obtient le nom du joueur
	 * @return retourne une chaine de caractère
	 */
	public String getNom() {
		return _nom;
	}
	/**
	 * Obtient une nouvelle instance de Jeton, au couleur
	 * du joueur
	 * @return retourne une instance de type Jeton
	 */
	public Jeton getJeton() {
		return new Jeton(this,_symbole); 
	}
	/**
	 * Obtient les racourcis claviers du joueur
	 * @return retourne un tableau de String
	 */
	public String[] getTouches() {
		return _toucheColonnes;
	}
	/**
	 * Modifie le symbole du joueur
	 * @param symbole, nouveau symbole
	 */
	public void setSymbole(char symbole) {
		_symbole = symbole;
	}
	/**
	 * Modifie le score total du joueur
	 * @param score, nouveau score total 
	 */
	public void setScoreTotal(int score) {
		_scoreTotal = score; 
	}
	/**
	 * Modifie le nom du joueur
	 * @param nom le nom du joueur
	 */
	public void setNom(String nom) {
		_nom = nom;
	}
	/**
	 * Permet d'initialisé les racourcis claviers du joueur
	 * avec des valeurs allant de 1 à n cases
	 * @param nbTouches, nombre de touche à assigner
	 */
	public void setTouche(int nbTouches) {
		_toucheColonnes = new String[nbTouches];
		for (int i = 0; i < nbTouches; i++) {
			_toucheColonnes[i] = String.valueOf(i + 1);
		}
	}
	/**
	 * Modifie les racourcis claviers avec un tableau 
	 * de String contenant les racourcis personnalisés
	 * @param touches personnalisés
	 */
	public void setTouche(String[] touches) {
		_toucheColonnes = touches;
	}
	
	// **********************************************
	// 				Constructors
	// **********************************************

	/**
	 * Instancie une nouvelle instance de Joueur avec tous les paramètres
	 * @param symbole le symbole des jetons du joueur
	 * @param scoreTotal le nombre de partie gagnée depuis le début
	 * @param nom son nom
	 * @param toucheColonnes les saisie racourcis clavier du joueur 
	 */
	public Joueur(String nom, char symbole, int scoreTotal, String[] toucheColonnes) {
		this._symbole = symbole;
		this._scoreTotal = scoreTotal;
		this._nom = nom;
		this._toucheColonnes = toucheColonnes;
	}

	/**
	 * Initialise une nouvelle instance de joueur avec un symbole 
	 * @param symbole, le symbole du joueur
	 */
	public Joueur(char symbole) {
		_symbole = symbole;
	}
	
	/**
	 * Initialise une nouvelle instance vide de Joueur
	 */
	public Joueur() {}
	
	
	// **********************************************
	// 					Méthodes
	// **********************************************
	
	/**
	 * Rajoute des points au score du joueur
	 * @param score à rajouter en plus
	 */
	public void addScore(int score) {
		_score += score;
	}
	
	/**
	 * Permet de remettre à 0 le score du joueur sur la partie
	 * en cours, le score de la partie est rajouté au score total
	 * pour ne pas être perdu
	 */
	public void resetScore() {
		saveScore();
		_score = 0;
	}
	
	/**
	 * Rajoute le score de la partie au score total
	 */
	private void saveScore() {
		_scoreTotal += _score;
	}
	
	/**
	 * Compare deux joueur entre eux grâce à leur score
	 * @param joueur, le joueur à comparé
	 * @return -1 s'il est inférieur, 0 si égal, et 1 si supérieur
	 */
	@Override
	public int compareTo(Joueur joueur) {
		if (joueur.getScore() >= this.getScore())
			if(joueur.getScore() > this.getScore())
				return 1;
			else
			return 0;
		else
			return -1;	
	}

}
