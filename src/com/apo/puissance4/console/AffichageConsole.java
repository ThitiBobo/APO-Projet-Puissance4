package com.apo.puissance4.console;

import java.util.Scanner;

public class AffichageConsole {

	private static Scanner sc = new Scanner(System.in);
	
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
		System.out.print("Saisissez le nombre de joueur : ");
		String saisie = sc.nextLine();
		
	}

}
