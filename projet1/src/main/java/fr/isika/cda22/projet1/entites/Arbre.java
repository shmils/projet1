package fr.isika.cda22.projet1.entites;

public class Arbre {
	
	//attributs
	private Noeud racine;
	
	//constructeur
	public Arbre() {
		super();
		this.racine = null;
	}
	
	//getters & setters
	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}
	
	//methodes specifiques
	public boolean isEmpty() {
		return (this.racine == null);
	}
	
	public String toString() {
		if(this.isEmpty()) {
			return "Arbre vide";
		} else {
			return this.racine.toString();
		}
	}
	
	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
		if(this.isEmpty()) {
			this.racine = new Noeud(nouveauStagiaire, null, null);
		} else {
			this.racine.ajouterStagiaire(nouveauStagiaire);
		}
	}
	
	public Noeud rechercherStagiaire(Stagiaire stagiaire) {
		if(this.isEmpty()) {
			return null;
		} else {
			return this.racine.rechercherStagiaire(stagiaire);
		}
	}
	
}
