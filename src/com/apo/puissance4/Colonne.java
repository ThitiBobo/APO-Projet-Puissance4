package com.apo.puissance4;

import com.apo.puissance4.exception.CaseNotEmptyException;
import com.apo.puissance4.exception.FullColumnException;

/**	
 * La classe Colonne permet de gérer une colonne dans un jeu de "puissance 4"
 * à l'aide de tableau @see Case.
 * @author Thibaut Delplanque
 */
public class Colonne {
	
	// **********************************************
	// 					Attributs
	// **********************************************
	/**
	 * tableau de @see Case qui représente une colonne d'un jeu de puissance 4
	 * le bas de la colonne se trouve à l'indice 0, et le haut à l'indice n 
	 */
	private Case[] _colonne;
	/**
	 * la taille de la colonne
	 */
	private int _tailleColonne;
	/**
	 * l'index de la case vide la plus en bas dans la colonne
	 */
	private int _indexCaseVide;
	
	// **********************************************
	// 				Getters Setters
	// **********************************************
	/**
	 * Permet de récupérer la taille de la colonne
	 * @return retourne un int pour la taille
	 */
	public int getTaille() {
		return _tailleColonne;
	}
	
	/**
	 * Permet de récupérer l'index de la case vide la plus en bas dans la colonne
	 * @return retourne un int pour l'index 
	 */
	public int getIndexCaseVide() {
		return _indexCaseVide;
	}

	/**
	 * Initialise une instance de la classe @see Colonne avec une taille 
	 * @param taille la taille de l'instance  
	 */
	public Colonne(int taille) {
		_tailleColonne = taille;
		_indexCaseVide = 0;
		for (int i = 0; i < taille; i++) {
			_colonne[i] = new Case();			
		}
	}
	
	/**
	 * Ajoute un @see Jeton dans la colonne, à la case la plus en bas 
	 * @param jeton, le jeton à ajouter
	 * @throws FullColumnException est renvoyé si la colunne est pleinne
	 * @throws IllegalArgumentException est renvoyé si le jeton passé en paramètre est null 
	 */
	public void ajouterJeton(Jeton jeton) throws IllegalArgumentException, FullColumnException {
		if(_indexCaseVide > _tailleColonne )
			throw new FullColumnException();
		try {
			_colonne[_indexCaseVide].ajouterJeton(jeton);
		} catch (CaseNotEmptyException e) {
			_indexCaseVide++;
			this.ajouterJeton(jeton);
		}
		_indexCaseVide++;
	}
	
	
}
