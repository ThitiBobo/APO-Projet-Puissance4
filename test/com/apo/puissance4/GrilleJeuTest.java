/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apo.puissance4;

import com.apo.puissance4.exception.FullColumnException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dorian
 */
public class GrilleJeuTest
{
    
    public GrilleJeuTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Test of resetGrille method, of class GrilleJeu.
     * @throws com.apo.puissance4.exception.FullColumnException
     */
    @Test
    public void testResetGrille() throws IllegalArgumentException, FullColumnException
    {
        System.out.println("resetGrille");
        GrilleJeu instance = new GrilleJeu();
        Joueur joueur = new Joueur('X');
        Jeton j = new Jeton(joueur);
        instance.ajouterJeton(0, j);
        
        assertEquals(instance.getColonneDernierJetonPlace(), 0);
        assertEquals(instance.getNbLignes()*instance.getNbColonnes()-1, instance.getNbCasesVides());
        instance.resetGrille();
        assertEquals(instance.getNbCasesVides(), instance.getNbLignes()*instance.getNbColonnes());
    }

    /**
     * Test of ajouterJeton method, of class GrilleJeu.
     * @throws java.lang.Exception
     */
    @Test
    public void testAjouterJeton() throws Exception
    {
        System.out.println("ajouterJeton");
        int indiceCol = 0;
        Joueur joueur = new Joueur('X');
        Jeton j = new Jeton(joueur);
        GrilleJeu instance = new GrilleJeu();
        instance.ajouterJeton(indiceCol, j);
        assertEquals(instance.getColonneDernierJetonPlace(), 0);
        assertEquals(instance.getNbCasesVides(), instance.getNbColonnes()*instance.getNbLignes() - 1);
    }

    /**
     * Test of getNbColonnes method, of class GrilleJeu.
     */
    @Test
    public void testGetNbColonnes()
    {
        System.out.println("getNbColonnes");
        GrilleJeu instance = new GrilleJeu();
        int expResult = 7;
        int result = instance.getNbColonnes();
        assertEquals(expResult, result);
        
        GrilleJeu instance2 = new GrilleJeu(50, 30);
        assertEquals(50, instance2.getNbColonnes());
    }

    /**
     * Test of getNbLignes method, of class GrilleJeu.
     */
    @Test
    public void testGetNbLignes()
    {
        System.out.println("getNbLignes");
        GrilleJeu instance = new GrilleJeu();
        int expResult = 6;
        int result = instance.getNbLignes();
        assertEquals(expResult, result);
        
        GrilleJeu instance2 = new GrilleJeu(50, 30);
        assertEquals(30, instance2.getNbLignes());
    }

    /**
     * Test of getLigneDernierJetonPlacé method, of class GrilleJeu.
     * @throws com.apo.puissance4.exception.FullColumnException
     */
    @Test
    public void testGetLigneDernierJetonPlacé() throws IllegalArgumentException, FullColumnException
    {
        System.out.println("getLigneDernierJetonPlac\u00e9");
        GrilleJeu instance = new GrilleJeu();
        Joueur joueur = new Joueur('X');
        Jeton j = new Jeton(joueur);
        instance.ajouterJeton(0, j);
        int expResult = 0;
        int result = instance.getColonneDernierJetonPlace();
        assertEquals(expResult, result);
        instance.ajouterJeton(0, j);
        assertEquals(1, instance.getColonneDernierJetonPlace());
    }

    /**
     * Test of getColonneDernierJetonPlacé method, of class GrilleJeu.
     * @throws com.apo.puissance4.exception.FullColumnException
     */
    @Test
    public void testGetColonneDernierJetonPlacé() throws IllegalArgumentException, FullColumnException
    {
        System.out.println("getColonneDernierJetonPlac\u00e9");
        GrilleJeu instance = new GrilleJeu();
        Joueur joueur = new Joueur('X');
        Jeton j = new Jeton(joueur);
        instance.ajouterJeton(4, j);
        int expResult = 4;
        int result = instance.getColonneDernierJetonPlace();
        assertEquals(expResult, result);
        instance.ajouterJeton(1, j);
        assertEquals(1, instance.getColonneDernierJetonPlace());
    }

    /**
     * Test of getSymbole method, of class GrilleJeu.
     * @throws com.apo.puissance4.exception.FullColumnException
     */
    @Test
    public void testGetSymbole() throws IllegalArgumentException, FullColumnException
    {
        System.out.println("getSymbole");
        GrilleJeu instance = new GrilleJeu();
        Joueur joueur = new Joueur('X');
        Jeton j = new Jeton(joueur);
        instance.ajouterJeton(4, j);
        int colonne = 4;
        int ligne = 0;
        char expResult = 'X';
        char result = instance.getSymbole(colonne, ligne);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNbCasesVides method, of class GrilleJeu.
     * @throws com.apo.puissance4.exception.FullColumnException
     */
    @Test
    public void testGetNbCasesVides() throws IllegalArgumentException, FullColumnException
    {
        System.out.println("getNbCasesVides");
        GrilleJeu instance = new GrilleJeu();
        Joueur joueur = new Joueur('X');
        Jeton j = new Jeton(joueur);
        instance.ajouterJeton(4, j);
        int expResult = 6*7-1;
        int result = instance.getNbCasesVides();
        assertEquals(expResult, result);
        
        GrilleJeu instance2 = new GrilleJeu(50, 50);
        instance2.ajouterJeton(4, j);
        int expResult2 = 50*50-1;
        int result2 = instance2.getNbCasesVides();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of vérifVictoire method, of class GrilleJeu.
     */
    @Test
    public void testVérifVictoire() throws IllegalArgumentException, FullColumnException
    {
        System.out.println("v\u00e9rifVictoire");
        int nbPionsAlignésPourVictoire = 4;
        GrilleJeu instance = new GrilleJeu();
        Joueur joueur = new Joueur('X');
        Jeton j = new Jeton(joueur);
        Joueur joueur2 = new Joueur('O');
        Jeton j2 = new Jeton(joueur);
        boolean expResult = false;
        boolean result = instance.verifVictoire(joueur, nbPionsAlignésPourVictoire);
        assertEquals(expResult, result);
       
        instance.ajouterJeton(1, j);
        instance.ajouterJeton(1, j);
        instance.ajouterJeton(1, j);
        instance.ajouterJeton(1, j);

        assertEquals(true, instance.verifVictoire(joueur, nbPionsAlignésPourVictoire));
        
         GrilleJeu instance2 = new GrilleJeu();
        assertEquals(false, instance2.verifVictoire(joueur, nbPionsAlignésPourVictoire));
        
        // on pourrait également faire les tests avec toutes les autres directions, c'est ce qui serait rigoureux.
        // étant donné que les tests ont déjà été réalisés à la main(sans jUnit) on s'abstiendra d'effectuer l'ensemble des tests. (et défaut de temps)
        
    }
}
