/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.apo.puissance4.GrilleJeu;
import com.apo.puissance4.Jeton;
import com.apo.puissance4.Joueur;
import com.apo.puissance4.exception.FullColumnException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorian : classe de test
 */
public class MainTestGrilleJeu
{
    /**
     * Méthode de test
     * @param args 
     */
    public static void main(String[] args)
    {
        GrilleJeu grille = new GrilleJeu(7,6);
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        Jeton jetonJ1 = new Jeton(j1, 'X');
        try
        {
            grille.ajouterJeton(3, jetonJ1);
            grille.ajouterJeton(3, jetonJ1);
            
            grille.ajouterJeton(2, jetonJ1);
            grille.ajouterJeton(2, jetonJ1);
            grille.ajouterJeton(2, jetonJ1);
            
            grille.ajouterJeton(1, jetonJ1);
            grille.ajouterJeton(1, jetonJ1);
            grille.ajouterJeton(1, jetonJ1);
            grille.ajouterJeton(1, jetonJ1);
            
            grille.ajouterJeton(4, jetonJ1);
            

        } 
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MainTestGrilleJeu.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (FullColumnException ex)
        {
            Logger.getLogger(MainTestGrilleJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean gagne = grille.vérifVictoire(j1, 4);
        System.out.println(gagne);
    }
}
