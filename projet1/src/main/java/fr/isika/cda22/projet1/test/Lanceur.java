package fr.isika.cda22.projet1.test;

import java.io.IOException;

import fr.isika.cda22.projet1.entites.*;

public class Lanceur {

	public static void main(String[] args) {
		Stagiaire lilia = new Stagiaire("flici", "lilia", "78", "cda 22", "2022");
		
//		System.out.println(lilia);
		
		Stagiaire hossein = new Stagiaire("milani", "hossein", "45", "Al 22", "2022");
		Stagiaire adrien = new Stagiaire("cerisier", "adrien", "94", "Al 22", "2022");
		Stagiaire adrien1 = new Stagiaire("cerisier", "adrien", "95", "Al 22", "2022");
		Stagiaire paul = new Stagiaire("paubel", "paul", "75", "Al 22", "2022");
		
		Noeud n1 = new Noeud(lilia);
		
		
		Arbre monArbre = new Arbre("src/main/java/fr/isika/cda22/projet1/fichiers/monArbre.bin");
		
//		System.out.println(monArbre);
//		monArbre.ajouterStagiaire(adrien);
//		monArbre.ajouterStagiaire(paul);
		
//		System.out.println(monArbre);
//		System.out.println(monArbre.rechercherStagiaire(paul));
//		System.out.println(monArbre.rechercherStagiaire(hossein));
//		System.out.println(monArbre.rechercherStagiaire(adrien1));
	}
	
}
