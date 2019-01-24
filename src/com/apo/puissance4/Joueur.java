package com.apo.puissance4;

public class Joueur {
	
	private char _symbole;
	private int _score;
	private int _scoreTotal;
	
	

	public char getSymbole() {
		return _symbole;
	}
	
	public int getScore() {
		return _score;
	}
	
	public void setSymbole(char symbole) {
		_symbole = symbole;
	}
	
	public Jeton getJeton() {
		return new Jeton(this,_symbole); 
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
