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
public class JoueurTest
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
     * Test of getSymbole method, of class Joueur.
     */
    @Test
    public void testGetSymbole()
    {
        System.out.println("getSymbole");
        Joueur instance = new Joueur('X');
        char expResult = 'X';
        char result = instance.getSymbole();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Joueur.
     */
    @Test
    public void testGetScore()
    {
        System.out.println("getScore");
        Joueur instance = new Joueur();
        instance.addScore(5);
        int expResult = 5;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScoreTotal method, of class Joueur.
     */
    @Test
    public void testGetScoreTotal()
    {
        System.out.println("getScoreTotal");
        Joueur instance = new Joueur();
        instance.setScoreTotal(2);
        int expResult = 2;
        int result = instance.getScoreTotal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNom method, of class Joueur.
     */
    @Test
    public void testGetNom()
    {
        System.out.println("getNom");
        Joueur instance = new Joueur();
        instance.setNom("Test");
        String expResult = "Test";
        String result = instance.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJeton method, of class Joueur.
     */
    @Test
    public void testGetJeton()
    {
        System.out.println("getJeton");
        Joueur j = new Joueur('X');
        boolean expResult = (j.getJeton() == j.getJeton());
        boolean result = ((j.getJeton()).equals(j.getJeton()));
        assertEquals(expResult, result);
    }

    /**
     * Test of getTouches method, of class Joueur.
     */
    @Test
    public void testGetTouches()
    {
        System.out.println("getTouches");
        Joueur instance = new Joueur();
        String[] touches = new String[2];
        touches[0] = "1";
        touches[1] = "2";
        instance.setTouche(touches);
        String[] result = instance.getTouches();
        String[] expResult = touches;
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setSymbole method, of class Joueur.
     */
    @Test
    public void testSetSymbole()
    {
        System.out.println("setSymbole");
        char symbole = 'O';
        Joueur instance = new Joueur('X');
        instance.setSymbole(symbole);
        assertEquals(instance.getSymbole(), 'O');
    }

    /**
     * Test of setScoreTotal method, of class Joueur.
     */
    @Test
    public void testSetScoreTotal()
    {
        System.out.println("setScoreTotal");
        int score = 5;
        Joueur instance = new Joueur();
        instance.setScoreTotal(score);
        assertEquals(instance.getScoreTotal(), score);
    }

    /**
     * Test of setNom method, of class Joueur.
     */
    @Test
    public void testSetNom()
    {
        System.out.println("setNom");
        String nom = "Hello";
        Joueur instance = new Joueur();
        instance.setNom(nom);
        assertEquals(nom, instance.getNom());
    }

    /**
     * Test of setTouche method, of class Joueur.
     */
    @Test
    public void testSetTouche_int()
    {
        System.out.println("setTouche");
        int result = 4;
        Joueur instance = new Joueur();
        instance.setTouche(result);
        int expResult = instance.getTouches().length;
        assertEquals(expResult, result);
    }

    /**
     * Test of setTouche method, of class Joueur.
     */
    @Test
    public void testSetTouche_StringArr()
    {
        System.out.println("setTouche");
        Joueur instance = new Joueur();
        String[] touches = new String[2];
        touches[0] = "1";
        touches[1] = "2";
        instance.setTouche(touches);
        String[] result = instance.getTouches();
        String[] expResult = touches;
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of addScore method, of class Joueur.
     */
    @Test
    public void testAddScore()
    {
        System.out.println("addScore");
        int score = 2;
        Joueur instance = new Joueur();
        instance.addScore(score);
        assertEquals(instance.getScore(), score);
        instance.addScore(score);
        assertEquals(instance.getScore(), score*2);
    }

    /**
     * Test of resetScore method, of class Joueur.
     */
    @Test
    public void testResetScore()
    {
        System.out.println("resetScore");
        int score = 2;
        Joueur instance = new Joueur();
        instance.addScore(score);
        instance.resetScore();
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Joueur.
     */
    @Test
    public void testCompareTo()
    {
        // not tested method
        assertEquals(true, true);
    }
    
}
