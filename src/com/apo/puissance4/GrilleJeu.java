package com.apo.puissance4;

import com.apo.puissance4.exception.FullColumnException;

/**
 * Grille gérant le jeu
 */
public class GrilleJeu 
{
	private static int _nbColonnesDefaut = 7;
	private static int _nbLignesDefaut = 6;

    //un tableau de colonnes : la grille
    private Colonne[] _grille;
    // nombre de colonnes
    private int _nbColonnes;
    // nombre de lignes
    private int _nbLignes;
    
    /*****coordonnéees du dernier jeton placé****/
    private int _derniereColJetonPlacé = 0;
    private int _derniereLigneJetonPlacé = 0;
        
    public GrilleJeu() {
    	this(_nbColonnesDefaut, _nbLignesDefaut);
    }
    
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
     * Initialise le tableau de colonnes. Méthode appelée par le constructeur.
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
    
    public char getSymbole(int colonne, int ligne) {
    	Case caseSelect = _grille[colonne].getCase(ligne);
    	if(!caseSelect.isEmpty())
    		return caseSelect.getJeton().getSymbole();
    	return Character.MIN_VALUE;
    }
    
    /**
     * Permet de vérifier si un joueur a bien aligné "nbPionsAlignésPourVictoire", valeur passé en paramètre.
     * Si oui, la fonction renvoie true. Sinon, false.
     * @param joueur : le joueur pour lequel on souhaite effectuer la vérification sur l'alignement des jetons.
     * @param nbPionsAlignésPourVictoire : le nombre de pions devant être alignés par le joueur pour gagner.
     * @return true si le joueur a bien aligné "nbPionsAlignésPourVictoire"
     */
    public boolean vérifVictoire(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        return (this.vérifDiagonales(joueur, nbPionsAlignésPourVictoire) || this.vérifHorizontale(joueur, nbPionsAlignésPourVictoire)
                || this.vérifVerticale(joueur, nbPionsAlignésPourVictoire));
    }
    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés horizontalement
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignésPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean vérifHorizontale(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        // on compte le jeton de départ (en théorie si la vérification est appelé sur un joueur qui n'a posé aucun jeton, la méthode va renvoyer
        // 1 même si le joueur n'a posé aucun jeton. Cela peut poser des problèmes si la condition de victoire est de 1 jeton à aligner mais je 
        // doute que le jeu soit toujours pertinent à jouer avec un unique jeton à aligner...
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
     * Compte le nombre de jetons alignés à droite du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés à droite du dernier jeton placé
     */
    private int vérifDroite(Joueur joueur)
    {
        // compteur contenant le "décalage à droite" en colonne(s)
        int décalage = 1;
        // le nombre de jetons alignés à droite stocké dans une var
        int nbAlignés = 0;
        // un boolean qui indique si on sort du while
        boolean flag = true;
        // si on ne va pas en dessous de 0
        if(this._derniereColJetonPlacé + décalage <= this._nbLignes)
        {
            // tant que la colonne du dernier jeton placé + le décalage à droite est supérieur ou égal à 0
            // et que le flag est positionné sur tue
            while((this._derniereColJetonPlacé + décalage <= this._nbLignes) &&  (flag) )
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlacé+décalage].getCase(this._derniereLigneJetonPlacé).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlacé+décalage].getCase(this._derniereLigneJetonPlacé).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignés++;
                        // on incrémente notre variable de décalage à gauche
                        décalage++;
                    }
                    // sinon
                    else
                    {
                        // si le jeton n'appartient pas au joueur, on positionne notre flag pour sortir da la boucle while
                        flag = false;
                    }
                }
                // sinon
                else
                {
                    // on ne prend pas le risque d'accéder à un index nul. On positionne donc notre flag sur false
                    // pour sortir sans risque de la boucle while. Un break aurait été possible mais il me semble
                    // que cette méthode est plus appropriée.
                    flag = false;
                }
            }
        }
        // on retourne le nombre de jetons alignés à droite du jeton initial, sans compter ce dernier.
        return nbAlignés;
    }
    /**
     * Compte le nombre de jeton placés à gauche du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés à gauche du dernier jeton placé
     */
    private int vérifGauche(Joueur joueur)
    {
        // compteur contenant le "décalage à gauche" en colonne(s)
        int décalage = 1;
        // le nombre de jetons alignés à gauche stocké dans une var
        int nbAlignés = 0;
        // un boolean qui indique si on sort du while
        boolean flag = true;
        // si on ne va pas en dessous de 0
        if(this._derniereColJetonPlacé - décalage >= 0)
        {
            // tant que la colonne du dernier jeton placé - le décalage à gauche est supérieur ou égal à 0
            // et que le flag est positionné sur tue
            while((this._derniereColJetonPlacé - décalage >= 0) &&  (flag) )
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlacé - décalage].getCase(this._derniereLigneJetonPlacé).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlacé - décalage].getCase(this._derniereLigneJetonPlacé).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignés++;
                        // on incrémente notre variable de décalage à gauche
                        décalage++;
                    }
                    else
                    {
                        // si le jeton n'appartient pas au joueur, on positionne notre flag pour sortir da la boucle while
                        flag = false;
                    }
                }
                // sinon
                else
                {
                    // on ne prend pas le risque d'accéder à un index nul. On positionne donc notre flag sur false
                    // pour sortir sans risque de la boucle while. Un break aurait été possible mais il me semble
                    // que cette méthode est plus appropriée.
                    flag = false;
                }
            }
        }
        // on retourne le nombre de jetons alignés à gauche du jeton initial, sans compter ce dernier.
        return nbAlignés;
    }
    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés verticalement
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignésPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean vérifVerticale(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
       // on compte le jeton de départ
       int nbAlignés = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant en bas de ce dernier.
       nbAlignés += this.vérifBas(joueur);
       // pas besoin de vérifier en haut du jeton car il est impossible que le dernier jeton placé soit en dessous d'un autre.
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
     * Compte le nombre jeton alignés en bas du dernier jeton placé.
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int vérifBas(Joueur joueur)
    {
       // i est un compteur contant le décalage en bas (en lignes)
        int décalage = 1;
        // le nombre de jetons alignés en bas
        int nbAlignés = 0;
        // un boolean qui indique si on sort du while
        boolean flag = true;
        // si on ne va pas en dessous de la ligne 0
        if(this._derniereLigneJetonPlacé - décalage >= 0)
        {
            // tant que la ligne du dernier jeton placé - le décalage en bas est supérieur ou égal à 0
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlacé - décalage >=0) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlacé].getCase(this._derniereLigneJetonPlacé - décalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlacé].getCase(this._derniereLigneJetonPlacé - décalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignés++;
                        // on incrémente notre variable de décalage à gauche
                        décalage++;
                    }
                    else
                    {
                        // si le jeton n'appartient pas au joueur, on positionne notre flag pour sortir da la boucle while
                        flag = false;
                    }
                }
                else
                {
                    // on ne prend pas le risque d'accéder à un index nul. On positionne donc notre flag sur false
                    // pour sortir sans risque de la boucle while. Un break aurait été possible mais il me semble
                    // que cette méthode est plus appropriée.
                    flag = false;
                }
            }
            
        }
        // on retourne le nombre de jetons alignés en bas du jeton initial, sans compter ce dernier.
        return nbAlignés;
    }

    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés diagonalement (30° et 60° haut & bas)
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignésPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean vérifDiagonales(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
        return (this.vérifDiagonales30degré(joueur, nbPionsAlignésPourVictoire) && this.vérifDiagonales60degrés(joueur, nbPionsAlignésPourVictoire));
    }
    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés diagonalement (30°)
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignésPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean vérifDiagonales30degré(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
       // on compte le jeton de départ
       int nbAlignés = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 30 degré en haut de ce dernier
       nbAlignés += this.vérifDiagonalesHautDroite(joueur);
        // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 30 degré en bas de ce dernier
       nbAlignés += this.vérifDiagonalesBasGauche(joueur);
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
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés diagonalement (60°)
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignésPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean vérifDiagonales60degrés(Joueur joueur, int nbPionsAlignésPourVictoire)
    {
       // on compte le jeton de départ
       int nbAlignés = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 60 degré en haut de ce dernier
       nbAlignés += this.vérifDiagonalesHautGauche(joueur);
        // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 60 degré en bas de ce dernier
       nbAlignés += this.vérifDiagonalesBasDroite(joueur);
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
     * Compte le nombre jeton alignés diagonalement à 30° en haut du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int vérifDiagonalesHautDroite(Joueur joueur)
    {
       // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int décalage = 1;
        // le nombre de jetons alignés en diagonale 30° en haut
        int nbAlignés = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlacé + décalage <= this._nbLignes)  && (this._derniereColJetonPlacé + décalage <= this._nbColonnes))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlacé + décalage <= this._nbLignes)  && (this._derniereColJetonPlacé + décalage <= this._nbColonnes) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlacé + décalage].getCase(this._derniereLigneJetonPlacé + décalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlacé + décalage].getCase(this._derniereLigneJetonPlacé + décalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignés++;
                        // on incrémente notre variable de décalage à gauche
                        décalage++;
                    }
                    else
                    {
                        // si le jeton n'appartient pas au joueur, on positionne notre flag pour sortir da la boucle while
                        flag = false;
                    }
                }
                else
                {
                    // on ne prend pas le risque d'accéder à un index nul. On positionne donc notre flag sur false
                    // pour sortir sans risque de la boucle while. Un break aurait été possible mais il me semble
                    // que cette méthode est plus appropriée.
                    flag = false;
                }
            }
        }
        // on retourne le nombre de jetons alignés en bas du jeton initial, sans compter ce dernier.
        return nbAlignés;
    }
    
    /**
     * Compte le nombre jeton alignés diagonalement à 30° en bas du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int vérifDiagonalesBasGauche(Joueur joueur)
    {
       // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int décalage = 1;
        // le nombre de jetons alignés en diagonale 30° en bas
        int nbAlignés = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlacé - décalage >= 0)  && (this._derniereColJetonPlacé - décalage >= 0))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlacé - décalage >= 0)  && (this._derniereColJetonPlacé - décalage >= 0) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlacé - décalage].getCase(this._derniereLigneJetonPlacé - décalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlacé - décalage].getCase(this._derniereLigneJetonPlacé - décalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignés++;
                        // on incrémente notre variable de décalage à gauche
                        décalage++;
                    }
                    else
                    {
                        // si le jeton n'appartient pas au joueur, on positionne notre flag pour sortir da la boucle while
                        flag = false;
                    }
                }
                else
                {
                    // on ne prend pas le risque d'accéder à un index nul. On positionne donc notre flag sur false
                    // pour sortir sans risque de la boucle while. Un break aurait été possible mais il me semble
                    // que cette méthode est plus appropriée.
                    flag = false;
                }
            }
        }
        // on retourne le nombre de jetons alignés en bas du jeton initial, sans compter ce dernier.
        return nbAlignés;
    }
    
    /**
     * Compte le nombre jeton alignés diagonalement à 60° en haut du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int vérifDiagonalesHautGauche(Joueur joueur)
    {
        // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int décalage = 1;
        // le nombre de jetons alignés en diagonale 60° en haut
        int nbAlignés = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlacé + décalage <= this._nbLignes)  && (this._derniereColJetonPlacé - décalage >= 0))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlacé + décalage <= this._nbLignes)  && (this._derniereColJetonPlacé - décalage >= 0) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlacé - décalage].getCase(this._derniereLigneJetonPlacé + décalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlacé - décalage].getCase(this._derniereLigneJetonPlacé + décalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignés++;
                        // on incrémente notre variable de décalage à gauche
                        décalage++;
                    }
                    else
                    {
                        // si le jeton n'appartient pas au joueur, on positionne notre flag pour sortir da la boucle while
                        flag = false;
                    }
                }
                else
                {
                    // on ne prend pas le risque d'accéder à un index nul. On positionne donc notre flag sur false
                    // pour sortir sans risque de la boucle while. Un break aurait été possible mais il me semble
                    // que cette méthode est plus appropriée.
                    flag = false;
                }
            }
        }
        // on retourne le nombre de jetons alignés en bas du jeton initial, sans compter ce dernier.
        return nbAlignés;
    }
    
    /**
     * Compte le nombre jeton alignés diagonalement à 60° en haut du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int vérifDiagonalesBasDroite(Joueur joueur)
   {
       // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int décalage = 1;
        // le nombre de jetons alignés en diagonale 30° en bas
        int nbAlignés = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlacé - décalage >= 0)  && (this._derniereColJetonPlacé + décalage <= this._nbColonnes))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlacé - décalage >= 0)  && (this._derniereColJetonPlacé + décalage <= this._nbColonnes) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlacé + décalage].getCase(this._derniereLigneJetonPlacé - décalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlacé + décalage].getCase(this._derniereLigneJetonPlacé - décalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignés++;
                        // on incrémente notre variable de décalage à gauche
                        décalage++;
                    }
                    else
                    {
                        // si le jeton n'appartient pas au joueur, on positionne notre flag pour sortir da la boucle while
                        flag = false;
                    }
                }
                else
                {
                    // on ne prend pas le risque d'accéder à un index nul. On positionne donc notre flag sur false
                    // pour sortir sans risque de la boucle while. Un break aurait été possible mais il me semble
                    // que cette méthode est plus appropriée.
                    flag = false;
                }
            }
        }
        // on retourne le nombre de jetons alignés en bas du jeton initial, sans compter ce dernier.
        return nbAlignés;
    }
}
