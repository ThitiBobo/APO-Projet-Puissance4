package com.apo.puissance4;

public class Jeton {
	
	private Joueur _joueur;
	private char _symbole;
	
	public char getSymbole() {
		return _symbole;
	}

	public Joueur getJoueur() {
		return _joueur;
	}
	public void setSymbole(char symbole) {
		this._symbole = symbole;
	}
	public void setJoueur(Joueur joueur) {
		this._joueur = joueur;
	}

	public Jeton(Joueur joueur) {
		this._joueur = joueur;
		this._symbole = joueur.getSymbole();

	}

}
