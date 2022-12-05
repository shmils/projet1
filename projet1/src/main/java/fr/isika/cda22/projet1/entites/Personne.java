package fr.isika.cda22.projet1.entites;

public abstract class Personne {
	
	//attributs
	private String nom;
	private String prenom;
	
	//contsructeur
	
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	
	//getters and setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
		
}
