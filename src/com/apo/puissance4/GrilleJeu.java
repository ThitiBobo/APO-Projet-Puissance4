package com.apo.puissance4;

import com.apo.puissance4.exception.FullColumnException;

/**
 * Grille gérant le jeu
 */
public class GrilleJeu 
{
	private static int _nbColonnesDefaut = 7;
	private static int _nbLignesDefaut = 6;
        
    private static int _nbCasesVidesDefault = 7*6;

    //un tableau de colonnes : la grille
    private Colonne[] _grille;
    // nombre de colonnes
    private int _nbColonnes;
    // nombre de lignes
    private int _nbLignes;
    private int _nbCasesVides;
    
    /*****coordonnéees du dernier jeton placé****/
    private int _derniereColJetonPlace = 0;
    private int _derniereLigneJetonPlace = 0;
        
    /**
     * constructeur par défaut
     */
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
        this._nbCasesVides = nbCol*nbLignes;
        this.initGrille();
    }
    
    public void resetGrille() {
    	this.initGrille();
        this._nbCasesVides = this._nbColonnes*this._nbLignes;
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
     * @param j, jeton à placé
     * @param indiceCol, indice de la colonne
     * @throws IllegalArgumentException exception
     * @throws FullColumnException exception
     */
    public void ajouterJeton(int indiceCol, Jeton j) throws IllegalArgumentException, FullColumnException
    {
        this._derniereColJetonPlace = indiceCol;
        this._derniereLigneJetonPlace = this._grille[indiceCol].getIndexCaseVide();
        this._grille[indiceCol].ajouterJeton(j);
        this._nbCasesVides--;
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
    public int getLigneDernierJetonPlace()
    {
        return this._derniereLigneJetonPlace;
    }
    
    /**
     * getter
     * @return : la colonne du dernier jeton placé
     */
    public int getColonneDernierJetonPlace()
    {
        return this._derniereColJetonPlace;
    }
    
    public char getSymbole(int colonne, int ligne) {
    	Case caseSelect = _grille[colonne].getCase(ligne);
    	if(!caseSelect.isEmpty())
    		return caseSelect.getJeton().getSymbole();
    	return Character.MIN_VALUE;
    }
    
    public int getNbCasesVides()
    {
        return this._nbCasesVides;
    }
    
    /**
     * Permet de vérifier si un joueur a bien aligné "nbPionsAlignésPourVictoire", valeur passé en paramètre.
     * Si oui, la fonction renvoie true. Sinon, false.
     * @param joueur : le joueur pour lequel on souhaite effectuer la vérification sur l'alignement des jetons.
     * @param nbPionsAlignesPourVictoire : le nombre de pions devant être alignés par le joueur pour gagner.
     * @return true si le joueur a bien aligné "nbPionsAlignésPourVictoire"
     */
    public boolean verifVictoire(Joueur joueur, int nbPionsAlignesPourVictoire)
    {
        return (this.verifDiagonales(joueur, nbPionsAlignesPourVictoire) || this.verifHorizontale(joueur, nbPionsAlignesPourVictoire)
                || this.verifVerticale(joueur, nbPionsAlignesPourVictoire));
    }
    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés horizontalement
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignesPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean verifHorizontale(Joueur joueur, int nbPionsAlignesPourVictoire)
    {
        // on compte le jeton de départ (en théorie si la vérification est appelé sur un joueur qui n'a posé aucun jeton, la méthode va renvoyer
        // 1 même si le joueur n'a posé aucun jeton. Cela peut poser des problèmes si la condition de victoire est de 1 jeton à aligner mais je 
        // doute que le jeu soit toujours pertinent à jouer avec un unique jeton à aligner...
       int nbAlignes = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant sur la droite de ce dernier.
       nbAlignes += this.verifDroite(joueur);
        // on incrémente le nombre de jeton alignés par le nombre de jetons se situant sur la gauche de ce dernier.
       nbAlignes += this.verifGauche(joueur);
       // si le nombre de jetons alignés est égal (ou supérieur mais ce cas ne devrait pas arriver) au nombre de pions devant être alignés pour la victoire
       if(nbAlignes >= nbPionsAlignesPourVictoire)
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
    private int verifDroite(Joueur joueur)
    {
        // compteur contenant le "décalage à droite" en colonne(s)
        int decalage = 1;
        // le nombre de jetons alignés à droite stocké dans une var
        int nbAlignes = 0;
        // un boolean qui indique si on sort du while
        boolean flag = true;
        // si on ne va pas en dessous de 0
        if(this._derniereColJetonPlace + decalage < this._nbLignes)
        {
            // tant que la colonne du dernier jeton placé + le décalage à droite est supérieur ou égal à 0
            // et que le flag est positionné sur tue
            while((this._derniereColJetonPlace + decalage < this._nbLignes) &&  (flag) )
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlace+decalage].getCase(this._derniereLigneJetonPlace).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlace+decalage].getCase(this._derniereLigneJetonPlace).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignes++;
                        // on incrémente notre variable de décalage à gauche
                        decalage++;
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
        return nbAlignes;
    }
    /**
     * Compte le nombre de jeton placés à gauche du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés à gauche du dernier jeton placé
     */
    private int verifGauche(Joueur joueur)
    {
        // compteur contenant le "décalage à gauche" en colonne(s)
        int decalage = 1;
        // le nombre de jetons alignés à gauche stocké dans une var
        int nbAlignes = 0;
        // un boolean qui indique si on sort du while
        boolean flag = true;
        // si on ne va pas en dessous de 0
        if(this._derniereColJetonPlace - decalage >= 0)
        {
            // tant que la colonne du dernier jeton placé - le décalage à gauche est supérieur ou égal à 0
            // et que le flag est positionné sur tue
            while((this._derniereColJetonPlace - decalage >= 0) &&  (flag) )
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlace - decalage].getCase(this._derniereLigneJetonPlace).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlace - decalage].getCase(this._derniereLigneJetonPlace).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignes++;
                        // on incrémente notre variable de décalage à gauche
                        decalage++;
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
        return nbAlignes;
    }
    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés verticalement
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignesPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean verifVerticale(Joueur joueur, int nbPionsAlignesPourVictoire)
    {
       // on compte le jeton de départ
       int nbAlignes = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant en bas de ce dernier.
       nbAlignes += this.verifBas(joueur);
       // pas besoin de vérifier en haut du jeton car il est impossible que le dernier jeton placé soit en dessous d'un autre.
       // si le nombre de jetons alignés est égal (ou supérieur mais ce cas ne devrait pas arriver) au nombre de pions devant être alignés pour la victoire
       if(nbAlignes >= nbPionsAlignesPourVictoire)
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
    private int verifBas(Joueur joueur)
    {
       // i est un compteur contant le décalage en bas (en lignes)
        int decalage = 1;
        // le nombre de jetons alignés en bas
        int nbAlignes = 0;
        // un boolean qui indique si on sort du while
        boolean flag = true;
        // si on ne va pas en dessous de la ligne 0
        if(this._derniereLigneJetonPlace - decalage >= 0)
        {
            // tant que la ligne du dernier jeton placé - le décalage en bas est supérieur ou égal à 0
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlace - decalage >=0) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlace].getCase(this._derniereLigneJetonPlace - decalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlace].getCase(this._derniereLigneJetonPlace - decalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignes++;
                        // on incrémente notre variable de décalage à gauche
                        decalage++;
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
        return nbAlignes;
    }

    
    /**
     * Vérifie si nbPionsAlignésPourVictoire sont alignés diagonalement (30° et 60° haut et bas)
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignesPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean verifDiagonales(Joueur joueur, int nbPionsAlignesPourVictoire)
    {
        return (this.verifDiagonales30degre(joueur, nbPionsAlignesPourVictoire) || this.verifDiagonales60degres(joueur, nbPionsAlignesPourVictoire));
    }
    
    /**
     * Vérifie si "nbPionsAlignésPourVictoire" sont alignés diagonalement (30°)
     * @param joueur : le joueur pour lequel on effectue la vérification
     * @param nbPionsAlignesPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean verifDiagonales30degre(Joueur joueur, int nbPionsAlignesPourVictoire)
    {
       // on compte le jeton de départ
       int nbAlignes = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 30 degré en haut de ce dernier
       nbAlignes += this.verifDiagonalesHautDroite(joueur);
        // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 30 degré en bas de ce dernier
       nbAlignes += this.verifDiagonalesBasGauche(joueur);
       // si le nombre de jetons alignés est égal (ou supérieur mais ce cas ne devrait pas arriver) au nombre de pions devant être alignés pour la victoire
       if(nbAlignes >= nbPionsAlignesPourVictoire)
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
     * @param nbPionsAlignesPourVictoire : le nombre de pions alignés nécessaire pour gagner
     * @return : true (victoire) ou false
     */
    private boolean verifDiagonales60degres(Joueur joueur, int nbPionsAlignesPourVictoire)
    {
       // on compte le jeton de départ
       int nbAlignes = 1;
       // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 60 degré en haut de ce dernier
       nbAlignes += this.verifDiagonalesHautGauche(joueur);
        // on incrémente le nombre de jeton alignés par le nombre de jetons se situant à 60 degré en bas de ce dernier
       nbAlignes += this.verifDiagonalesBasDroite(joueur);
       // si le nombre de jetons alignés est égal (ou supérieur mais ce cas ne devrait pas arriver) au nombre de pions devant être alignés pour la victoire
       if(nbAlignes >= nbPionsAlignesPourVictoire)
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
    private int verifDiagonalesHautDroite(Joueur joueur)
    {
       // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int decalage = 1;
        // le nombre de jetons alignés en diagonale 30° en haut
        int nbAlignes = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlace + decalage < this._nbLignes)  && (this._derniereColJetonPlace + decalage < this._nbColonnes))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlace + decalage < this._nbLignes)  && (this._derniereColJetonPlace + decalage < this._nbColonnes) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlace + decalage].getCase(this._derniereLigneJetonPlace + decalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlace + decalage].getCase(this._derniereLigneJetonPlace + decalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignes++;
                        // on incrémente notre variable de décalage à gauche
                        decalage++;
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
        return nbAlignes;
    }
    
    /**
     * Compte le nombre jeton alignés diagonalement à 30° en bas du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int verifDiagonalesBasGauche(Joueur joueur)
    {
       // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int decalage = 1;
        // le nombre de jetons alignés en diagonale 30° en bas
        int nbAlignes = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlace - decalage >= 0)  && (this._derniereColJetonPlace - decalage >= 0))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlace - decalage >= 0)  && (this._derniereColJetonPlace - decalage >= 0) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlace - decalage].getCase(this._derniereLigneJetonPlace - decalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlace - decalage].getCase(this._derniereLigneJetonPlace - decalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignes++;
                        // on incrémente notre variable de décalage à gauche
                        decalage++;
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
        return nbAlignes;
    }
    
    /**
     * Compte le nombre jeton alignés diagonalement à 60° en haut du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int verifDiagonalesHautGauche(Joueur joueur)
    {
        // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int decalage = 1;
        // le nombre de jetons alignés en diagonale 60° en haut
        int nbAlignes = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlace + decalage < this._nbLignes)  && (this._derniereColJetonPlace - decalage >= 0))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlace + decalage < this._nbLignes)  && (this._derniereColJetonPlace - decalage >= 0) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlace - decalage].getCase(this._derniereLigneJetonPlace + decalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlace - decalage].getCase(this._derniereLigneJetonPlace + decalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignes++;
                        // on incrémente notre variable de décalage à gauche
                        decalage++;
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
        return nbAlignes;
    }
    
    /**
     * Compte le nombre jeton alignés diagonalement à 60° en haut du dernier jeton placé
     * @param joueur : le joueur pour lequel on souhaite faire la vérification sur l'alignement des jetons
     * @return le nombre de jeton alignés en bas du dernier jeton placé
     */
    private int verifDiagonalesBasDroite(Joueur joueur)
   {
       // compte le décalage horizontal (en haut et en lignes) ainsi que le décalage vertical (en colonnes)
       int decalage = 1;
        // le nombre de jetons alignés en diagonale 30° en bas
        int nbAlignes = 0;
       // un boolean qui indique si on sort du while
        boolean flag = true;
        // Si la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
        if( (this._derniereLigneJetonPlace - decalage >= 0)  && (this._derniereColJetonPlace + decalage < this._nbColonnes))
        {
            // tant que la dernière ligne décalée de i reste en dessous du nombre total de lignes et que la dernière colonne décalée de j reste en dessous du nombre total de colonnes
            // et que le flag est positionné sur tue
            while((this._derniereLigneJetonPlace - decalage >= 0)  && (this._derniereColJetonPlace + decalage < this._nbColonnes) && (flag))
            {
                // s'il y a bien un jeton sur l'endroit que l'on va checker
                if(!this._grille[this._derniereColJetonPlace + decalage].getCase(this._derniereLigneJetonPlace - decalage).isEmpty())
                {
                    // si le jeton appartient bien au joueur donné.
                    if(this._grille[this._derniereColJetonPlace + decalage].getCase(this._derniereLigneJetonPlace - decalage).getJeton().getJoueur() == joueur)
                    {
                        // on incrémente notre compteur de jeton alignés
                        nbAlignes++;
                        // on incrémente notre variable de décalage à gauche
                        decalage++;
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
        return nbAlignes;
    }
    
    public boolean isFull()
    {
        return this._nbCasesVides == 0;
    }
}
