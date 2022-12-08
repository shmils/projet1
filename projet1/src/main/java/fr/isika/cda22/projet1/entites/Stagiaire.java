package fr.isika.cda22.projet1.entites;

public class Stagiaire extends Personne{
	
	//attributs static
	private final static int TAILLE_MAX_STRING = 12; //un string ne contient pas plus que 12 characters
	private final static int TAILLE_STAGIAIRE_OCTET = 5*(2*TAILLE_MAX_STRING); //nb Attribut String* (octet(Char)*Taille Max)
	
	//attributs
	private String localisation;
	private String nomFormation;
	private String anneePromo;
	
	//constructeur
	public Stagiaire(String nom, String prenom, String localisation, String nomFormation, String anneePromo) {
		super(nom, prenom);
		this.localisation = localisation;
		this.nomFormation = nomFormation;
		this.anneePromo = anneePromo;
	}
	
	//getters & setters
	
	public static int getTailleMaxString() {
		return TAILLE_MAX_STRING;
	}

	public static int getTailleStagiaireOctet() {
		return TAILLE_STAGIAIRE_OCTET;
	}
		
	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public String getAnneePromo() {
		return anneePromo;
	}

	public void setAnneePromo(String anneePromo) {
		this.anneePromo = anneePromo;
	}

	//methodes specifiques
	@Override
	public String toString() {
		return "(" + super.getNom() + " " + super.getPrenom() + " " + localisation + " " + nomFormation + " " + anneePromo + ")";
	}
	
	//comparaison : ordre alphabetique du nom
	public int compareTo(Stagiaire autreStagiaire) {
		return autreStagiaire.getNom().compareTo(this.getNom());
	}
	
}
