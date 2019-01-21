package com.apo.puissance4;

public class Jeton {
	private Joueur _joueur;
	private char _symbole;

	public void Jeton(Joueur _joueur) {
		this._joueur = _joueur;
		this._symbole = _joueur.getSymbole();

	}
	public char getSymbole() {
		return _symbole;
	}

	public Joueur getJoueur() {
		return _joueur;
	}
	public void setSymbole(char _symbole) {
		this._symbole=_symbole;
	}
	public void setJoueur(Joueur joueur) {
		this._joueur=_joueur;
	}
}
