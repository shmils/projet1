package fr.isika.cda22.projet1.entites;

public class Stagiaire extends Personne{
	
	//attributs
	private int localisation;
	private String nomFormation;
	private int anneePromo;
	
	//constructeur
	public Stagiaire(String nom, String prenom, int localisation, String nomFormation, int anneePromo) {
		super(nom, prenom);
		this.localisation = localisation;
		this.nomFormation = nomFormation;
		this.anneePromo = anneePromo;
	}
	
	//getters & setters
	public int getLocalisation() {
		return localisation;
	}

	public void setLocalisation(int localisation) {
		this.localisation = localisation;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public int getAnneePromo() {
		return anneePromo;
	}

	public void setAnneePromo(int anneePromo) {
		this.anneePromo = anneePromo;
	}

		
	//methodes specifiques
	@Override
	public String toString() {
		return "Stagiaire [nom =" + this.getNom() + ", prenom ="+ this.getPrenom() + ", localisation=" + localisation + ", nomFormation=" + nomFormation + ", anneePromo="
				+ anneePromo + "]";
	}
	
	public int compareTo(Stagiaire autreStagiaire) {
		if(this.getNom().compareTo(autreStagiaire.getNom()) == 0) {
			if(this.getPrenom().compareTo(autreStagiaire.getPrenom()) == 0) {
				if(this.getLocalisation() == autreStagiaire.getLocalisation()) {
					if(this.nomFormation.compareTo(autreStagiaire.nomFormation) == 0) {
						return this.anneePromo - autreStagiaire.anneePromo;
					}else {
						return this.nomFormation.compareTo(autreStagiaire.nomFormation);
					}
				} else {
					return this.localisation - autreStagiaire.localisation;
				}
			} else {
				this.getPrenom().compareTo(autreStagiaire.getPrenom());
			} 
		} return this.getNom().compareTo(autreStagiaire.getNom());
		
	}
	
}
