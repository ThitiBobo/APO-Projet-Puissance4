/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apo.puissance4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dorian
 */
public class JetonTest
{
    
    public JetonTest()
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
     * Test of getSymbole method, of class Jeton.
     */
    @Test
    public void testGetSymbole()
    {
        System.out.println("getSymbole");
        Joueur j = new Joueur('X');
        Jeton instance = new Jeton(j);
        char expResult = 'X';
        char result = instance.getSymbole();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJoueur method, of class Jeton.
     */
    @Test
    public void testGetJoueur()
    {
        System.out.println("getJoueur");
        Joueur j = new Joueur();
        Jeton instance = new Jeton(j);
        Joueur expResult = j;
        Joueur result = instance.getJoueur();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSymbole method, of class Jeton.
     */
    @Test
    public void testSetSymbole()
    {
        System.out.println("setSymbole");
        Joueur j = new Joueur();
        char symbole = 'X';
        Jeton instance = new Jeton(j);
        instance.setSymbole(symbole);
        assertEquals(symbole, instance.getSymbole());
    }

    /**
     * Test of setJoueur method, of class Jeton.
     */
    @Test
    public void testSetJoueur()
    {
        System.out.println("setJoueur");
        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();
        Jeton instance = new Jeton(joueur1);
        
        assertEquals(instance.getJoueur() == joueur1, true);
        assertEquals(instance.getJoueur() == joueur2, false);
        
        instance.setJoueur(joueur2);
        assertEquals(instance.getJoueur() == joueur2, true);
        assertEquals(instance.getJoueur() == joueur1, false);


        

    }
    
}
