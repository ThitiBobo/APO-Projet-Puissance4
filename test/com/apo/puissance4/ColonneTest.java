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
public class ColonneTest
{
    
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Test of getTaille method, of class Colonne.
     */
    @Test
    public void testGetTaille()
    {
        System.out.println("getTaille");
        Colonne instance = new Colonne(9);
        int expResult = 9;
        int result = instance.getTaille();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIndexCaseVide method, of class Colonne.
     */
    @Test
    public void testGetIndexCaseVide()
    {
        System.out.println("");
        Colonne instance = new Colonne(7);
        int expResult = 0;
        int result = instance.getIndexCaseVide();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCase method, of class Colonne.
     */
    @Test
    public void testGetCase()
    {
        System.out.println("getCase");
        int index = 0;
        Colonne instance = new Colonne(7);
        assertEquals(instance.getCase(index), instance.getCase(instance.getIndexCaseVide()));
    }

    /**
     * Test of ajouterJeton method, of class Colonne.
     */
    @Test
    public void testAjouterJeton() throws Exception
    {
        System.out.println("ajouterJeton");
        Joueur joueur = new Joueur('X');
        Jeton jeton = new Jeton(joueur);
        Colonne instance = new Colonne(7);
        instance.ajouterJeton(jeton);
        assertEquals(instance.getIndexCaseVide(), 1);
    }

    /**
     * Test of isFull method, of class Colonne.
     */
    @Test
    public void testIsFull() throws IllegalArgumentException, FullColumnException
    {
        System.out.println("isFull");
        Colonne instance = new Colonne(3);
        Joueur joueur = new Joueur('X');
        Jeton jeton = new Jeton(joueur);
        instance.ajouterJeton(jeton);
        boolean expResult = false;
        boolean result = instance.isFull();
        assertEquals(expResult, result);
        
        instance.ajouterJeton(jeton);
        instance.ajouterJeton(jeton);
        boolean expResult2 = true;
        boolean result2 = instance.isFull();
        assertEquals(expResult2, result2);
    }
    
}
