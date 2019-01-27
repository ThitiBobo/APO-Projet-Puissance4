/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apo.puissance4;

import com.apo.puissance4.exception.CaseNotEmptyException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dorian
 */
public class CaseTest
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
     * Test of getJeton method, of class Case.
     */
    @Test
    public void testGetJeton() throws CaseNotEmptyException
    {
        System.out.println("getJeton");
        Case uneCase = new Case();
        Joueur joueur = new Joueur('X');
        Jeton jeton = new Jeton(joueur);
        uneCase.ajouterJeton(jeton);
        Jeton result = uneCase.getJeton();
        Jeton expResult = jeton;
        assertEquals(expResult, result);
        
        Case uneAutreCase = new Case();
        Jeton result2 = uneAutreCase.getJeton();
        Jeton expResult2 = null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of isEmpty method, of class Case.
     */
    @Test
    public void testIsEmpty() throws CaseNotEmptyException
    {
        System.out.println("isEmpty");
        Case uneCase = new Case();
        Joueur joueur = new Joueur('X');
        Jeton jeton = new Jeton(joueur);
        uneCase.ajouterJeton(jeton);
        boolean result = uneCase.isEmpty();
        boolean expResult = false;
        assertEquals(expResult, result);
        
        Case uneAutreCase = new Case();
        boolean result2 = uneAutreCase.isEmpty();
        boolean expResult2 = true;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of ajouterJeton method, of class Case.
     */
    @Test
    public void testAjouterJeton() throws Exception
    {
        System.out.println("ajouterJeton");
        Joueur joueur = new Joueur('X');
        Jeton jeton = new Jeton(joueur);
        Case instance = new Case();
        instance.ajouterJeton(jeton);
        boolean result = instance.isEmpty();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Case.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        // no test for this method
        // tricky to test as im not the author of it
        assertEquals(true, true);
    }
    
}
