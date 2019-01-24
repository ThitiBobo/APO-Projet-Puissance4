package com.apo.puissance4;

import com.apo.puissance4.exception.FullColumnException;

/**
 * Grille gérant le jeu
 */
public class GrilleJeu {

        //un tableau de colonnes : la grille
	private Colonne[] _grille;
        // nombre de colonnes
	private int _nbColonnes;
        // nombre de lignes
        private int _nbLignes;
        
        /**
         * Constructeur de la grille de jeu
         * @param nbCol : le nombre de colonnes souhaité pour la partie
         * @param nbLignes : le nombre de lignes par colonne souhaité pour la partie
         */
        public GrilleJeu(int nbCol, int nbLignes)
        {
            this._nbColonnes = nbCol;
            this._nbLignes = nbLignes;
            this.initGrille();
        }
        
        /**
         * Initialise le tableau de colonnes.
         */
        private void initGrille()
        {
            this._grille = new Colonne[this._nbColonnes];
            for(int i = 0; i < this._nbLignes; i++)
            {
                this._grille[i] = new Colonne(this._nbLignes);
            }
        }
        
        /**
         * Ajoute un jeton
         */
        public void ajouterJeton(int indiceCol, Jeton j) throws IllegalArgumentException, FullColumnException
        {
            this._grille[indiceCol].ajouterJeton(j);
        }
        
        /**
         * getter
         * @return : le nombre de colonnes 
         */
        public int getNbColonnes()
        {
            return this._nbColonnes;
        }
        
        /**
         * getter
         * @return : le nombre de lignes 
         */
        public int getNbLignes()
        {
            return this._nbLignes;
        }
}
