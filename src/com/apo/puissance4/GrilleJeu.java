package com.apo.puissance4;

import com.apo.puissance4.exception.FullColumnException;

/**
 * Grille gérant le jeu
 */
public class GrilleJeu 
{

    //un tableau de colonnes : la grille
    private Colonne[] _grille;
    // nombre de colonnes
    private int _nbColonnes;
    // nombre de lignes
    private int _nbLignes;
    
    /*****coordonnéees du dernier jeton placé****/
    private int _derniereColJetonPlacé = 0;
    private int _derniereLigneJetonPlacé = 0;
        
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
        for(int i = 0; i < this._nbColonnes; i++)
        {
            this._grille[i] = new Colonne(this._nbLignes);
        }
    }

    /**
     * Récupère les coordonnées de la case où le jeton va être placé
     * et ajoute un jeton
     */
    public void ajouterJeton(int indiceCol, Jeton j) throws IllegalArgumentException, FullColumnException
    {
        this._derniereColJetonPlacé = indiceCol;
        this._derniereLigneJetonPlacé = this._grille[indiceCol].getIndexCaseVide();
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
    
    /**
     * getter
     * @return : la ligne du dernier jeton placé
     */
    public int getLigneDernierJetonPlacé()
    {
        return this._derniereLigneJetonPlacé;
    }
    
    /**
     * getter
     * @return : la colonne du dernier jeton placé
     */
    public int getColonneDernierJetonPlacé()
    {
        return this._derniereColJetonPlacé;
    }
    
    /**
     * 
     * @param joueur
     * @param nbPionsAlignésPourVictoire
     * @return 
     */
    public boolean vérifVictoire(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        return (this.vérifDiagonales(joueur, nbPionsAlignésPourVictoire) && this.vérifHorizontale(joueur, nbPionsAlignésPourVictoire)
                && this.vérifVerticale(joueur, nbPionsAlignésPourVictoire));
    }
    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés horizontalement
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignésPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    public boolean vérifHorizontale(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        // on compte le jeton de départ
       int nbAlignés = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant sur la droite de ce dernier.
       nbAlignés += this.vérifDroite(joueur);
        // on incrémente le nombre de jeton alignés par le nombre de jetons se situant sur la gauche de ce dernier.
       nbAlignés += this.vérifGauche(joueur);
       // si le nombre de jetons alignés est égal (ou supérieur mais ce cas ne devrait pas arriver) au nombre de pions devant être alignés pour la victoire
       if(nbAlignés >= nbPionsAlignésPourVictoire)
       {
           // le joueur a gagné
           return true;
       }
       //sinon
       else
       {
           // le joueur n'a pas (encore) gagné
           return false;
       }
    }
    
    /**
     * 
     * @param joueur
     * @return le nombre de jeton alignés à droite du dernier jeton placé
     */
    public int vérifDroite(Joueur joueur)
    {
        // i est un compteur contenant le "décalage à droite" en colonne(s)
        int i = 1;
        // le nombre de jetons alignés à droite;
        int nbAlignés = 0;
        // si on ne dépasse pas le nombre de lignes maximum dès le début
        if(this._derniereColJetonPlacé+i < this._nbLignes)
        {
            while((this._derniereColJetonPlacé + i < this._nbLignes) && 
            (this._grille[this._derniereColJetonPlacé+i].getCase(this._derniereLigneJetonPlacé).getJeton().getJoueur() == joueur))
            {
                nbAlignés++;
                i++;
            }
            return nbAlignés;
        }
        else
        {
            return nbAlignés;
        }
    }
    
    public int vérifGauche(Joueur joueur)
    {
        return 0;
    }
    
    /**
     * 
     * @param joueur
     * @param nbPionsAlignésPourVictoire
     * @return 
     */
    public boolean vérifVerticale(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        return false;
    }
    
    
    /**
     * 
     * @param joueur
     * @param nbPionsAlignésPourVictoire
     * @return 
     */
    public boolean vérifDiagonales(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        return (this.vérifDiagonales30degré(joueur, nbPionsAlignésPourVictoire) && this.vérifDiagonales60degrés(joueur, nbPionsAlignésPourVictoire));
    }
    
    /**
     * 
     * @param joueur
     * @param nbPionsAlignésPourVictoire
     * @return 
     */
    private boolean vérifDiagonales30degré(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        return false;
    }
    
    /**
     * 
     * @param joueur
     * @param nbPionsAlignésPourVictoire
     * @return 
     */
    private boolean vérifDiagonales60degrés(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        return false;
    }
}
