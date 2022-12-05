package fr.isika.cda22.projet1.entites;

public class Formatteur extends Personne {
	
	//attributs
	private String nomUtilisateur;
	private String motDePasse;

	//constructeur
	public Formatteur(String nom, String prenom, String nomUtilisateur, String motDePasse) {
		super(nom, prenom);
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}

	//getters & setters
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	//methodes specifiques
	public boolean isAdmin() {
		return false;
	}
	
}
