package com.apo.puissance4.console;

import java.util.Scanner;
import com.apo.puissance4.ManagerJeu;
import com.apo.puissance4.exception.FullColumnException;
import com.apo.puissance4.exception.KeyDoNotExistException;

public class AffichageConsole {

	private static Scanner sc = new Scanner(System.in);
	
	private static ManagerJeu jeu;
	
	public static void main(String[] args) {
		
		boolean choisie = true;
		
		System.out.println("Lancer une partie local ou distante ?");
		do {
			choisie = true;
			System.out.println("1    : local");
			System.out.println("2    : distante");
			System.out.println("exit : quitter");
			String saisie = sc.nextLine();
			
			switch (saisie) {
			case "1":
				partieLocal();
				break;
			case "2":
				partieDistante();
				break;
			case "exit":
				break;
			default:
				choisie = false;
				System.out.println("saisie incorrecte");
				break;
			}
		}while(!choisie);
		quitter();
		

	}

	private static void quitter() {
		System.out.println("Bisous");
		System.exit(0);
	}

	private static void partieDistante() {
		// TODO Auto-generated method stub
		
	}

	private static void partieLocal() {
		jeu = new ManagerJeu();
		boolean choisie;
		System.out.println("Partie rapide ou personnalisé ?");
		do {
			choisie = true;
			System.out.println("1    : rapide");
			System.out.println("2    : personnalisé");
			System.out.println("exit : quitter");
			String saisie = sc.nextLine();
			
			switch (saisie) {
			case "1":
				break;
			case "2":
				personnalise();
				break;
			case "exit":
				quitter();
				break;
			default:
				choisie = false;
				System.out.println("saisie incorrecte");
				break;
			}
		}while(!choisie);
		jouer();
		
	}
	
	private static void personnalise() {
		
	}
	
	private static void jouer() {
		boolean tourFinie;
		do {
			do {
				tourFinie = true;
				System.out.print("Partie n°" + jeu.getNumPartie() + "/" + jeu.getNbPartie() + " - ");
				System.out.println("Tour de " + jeu.getNomJoueurCourrant());
				System.out.println();
				System.out.println(jeu.affichage());
				System.out.print("Entrée le  nom de la case: ");
				String saisie = sc.nextLine();
				
				try {
					jeu.jouer(saisie);
				} catch (FullColumnException e) {
					// TODO Auto-generated catch block
					System.out.println("la colonne est pleinne ! Gros Beta !! ");
					tourFinie = false;
				} catch (KeyDoNotExistException e) {
					// TODO Auto-generated catch block
					System.out.println("appuie sur une touche qui existe. Patate! -_- ");
					tourFinie = false;
				}catch(IllegalArgumentException e) {
					System.out.println("Une erreur c'est produite");
					quitter();
				}
			}while(!tourFinie);
			
		}while(!jeu.partieFinie());
		
		
	}
	
}

