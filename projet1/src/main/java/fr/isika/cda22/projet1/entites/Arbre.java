package fr.isika.cda22.projet1.entites;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Arbre {
	
	//attributs
	private RandomAccessFile raf;
	private int indiceRacine;
	
	//constructeur
	public Arbre(String fileName) throws IOException {
		super();
		raf = new RandomAccessFile(new File(fileName), "rw");
		this.indiceRacine = 0;
	}
	
	//getters & setters
	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}
	
	public int getIndiceRacine() {
		return indiceRacine;
	}

	//methodes specifiques
	public boolean isEmpty() {
		try {
			return (this.raf.length() == 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "Arbre est vide";
		}else {
			return Noeud.readNoeudBin(raf, this.indiceRacine).toString(raf);
		}
	}
	
//	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
//		if(this.isEmpty()) {
//			this.racine = new Noeud(nouveauStagiaire, null, null);
//		} else {
//			this.racine.ajouterStagiaire(nouveauStagiaire);
//		}
//	}
//	
//	public Noeud rechercherStagiaire(Stagiaire stagiaire) {
//		if(this.isEmpty()) {
//			return null;
//		} else {
//			return this.racine.rechercherStagiaire(stagiaire);
//		}
//	}
	
}
