package fr.isika.cda22.projet1.entites;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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
	
	public void ajouterStagiaire(Stagiaire stagiaire) throws IOException {
		if(this.isEmpty()) {
			Noeud.writeNoeudBin(stagiaire, raf);
		}else {
			Noeud n0 = Noeud.readNoeudBin(raf, this.indiceRacine);
			n0.ajouterNoeud(stagiaire, raf);
		}
	}
	
	public ArrayList<Stagiaire> toArray() {
		if(this.isEmpty()) {
			return null;
		} else {
			ArrayList<Stagiaire> stgArray = new ArrayList<>();
			Noeud.readNoeudBin(raf, this.indiceRacine).toArray(stgArray, raf);
			return stgArray;
		}
	}
	
	
}
