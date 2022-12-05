package fr.isika.cda22.projet1.test;

import fr.isika.cda22.projet1.entites.*;

public class Lanceur {

	public static void main(String[] args) {
		Stagiaire lilia = new Stagiaire("flici", "lilia", 78, "cda 22", 2022);
		
//		System.out.println(lilia);
		
		Stagiaire hossein = new Stagiaire("milani", "hossein", 45, "Al 22", 2022);
		Stagiaire adrien = new Stagiaire("cerisier", "adrien", 94, "Al 22", 2022);
		Stagiaire adrien1 = new Stagiaire("cerisier", "adrien", 95, "Al 22", 2022);
		Stagiaire paul = new Stagiaire("paubel", "paul", 75, "Al 22", 2022);
		
		Noeud n1 = new Noeud(lilia, null, null);
		
//		System.out.println(n1);
		
		n1.ajouterStagiaire(hossein);
//		n1.ajouterStagiaire(adrien);
//		n1.ajouterStagiaire(paul);
//		n1.ajouterStagiaire(adrien1);
		
		Arbre monArbre = new Arbre();
		System.out.println(monArbre);
		monArbre.setRacine(n1);
//		monArbre.ajouterStagiaire(lilia);
//		System.out.println(n1);
		monArbre.ajouterStagiaire(adrien);
		monArbre.ajouterStagiaire(paul);
		
		System.out.println(monArbre);
		System.out.println(monArbre.rechercherStagiaire(paul));
		System.out.println(monArbre.rechercherStagiaire(hossein));
		System.out.println(monArbre.rechercherStagiaire(adrien1));
	}
	
}
