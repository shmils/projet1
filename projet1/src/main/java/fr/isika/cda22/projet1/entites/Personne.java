package fr.isika.cda22.projet1.entites;

import java.util.regex.Pattern;

/**
 * cette classe represente une Personne
 * 
 * @author shmilany
 *
 */
public abstract class Personne {
	
	//attributs
	private String nom;
	private String prenom;
	
	//contsructeur
	
	
	/**
	 * Contstructeur permettant d'initialiser une Personne 
	 * 
	 * @param nom le nom de la personne
	 * @param prenom le prenom de la personne
	 */
	public Personne(String nom, String prenom) {
		this.nom = Verificateur.upperCase(nom);
		this.prenom = Verificateur.capitalize(prenom);
	}

	
	//getters and setters
	
	/**
	 * une methode permettant d'acceder au nom de la personne
	 * @return nom de la personne
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * une methode permettant de modifier le nom de la personne
	 * @param nom nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * une methode permettant d'acceder au prenom de la personne
	 * @return prenom de la personne
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * une methode permettant de modifier le prenom de la personne
	 * @param prenom nouveau prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
