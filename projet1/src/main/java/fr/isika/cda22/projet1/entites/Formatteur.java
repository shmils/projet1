package fr.isika.cda22.projet1.entites;

/**
 * 
 * cette classe represente un utilisateur Formatteur
 * 
 * extends @see {@link fr.isika.cda22.projet1.entities.Personne}
 * @author shmilany
 *
 */
public class Formatteur extends Personne {
	
	//attributs
	private String nomUtilisateur;
	private String motDePasse;

	//constructeur
	/**
	 * Constructeur permettant d'initialiser un utilisateur Formatteur
	 * 
	 * @param nom nom du formatteur
	 * @param prenom prenom du formatteur
	 * @param nomUtilisateur nom utilisateur du formatteur 
	 * @param motDePasse le mot de Passe du l'utilisateur
	 */
	public Formatteur(String nom, String prenom, String nomUtilisateur, String motDePasse) {
		super(nom, prenom);
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}

	//getters & setters
	/**
	 * methode permettant d'acceder au nom utilisateur
	 * @return nom utilisateur du formatteur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	/**
	 * methode permettant de modifier nom utilisateur
	 * 
	 * @param nomUtilisateur nouveau nom utilisateur
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * methode permettant d'acceder au mot de passe de l'utilisateur
	 * @return mot de passe de l'utilisateur
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	/**
	 * methode permettant de modifier mot de passe de l'utilisateur
	 * @param motDePasse nouveau mot de passe
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	//methodes specifiques
	
	/**
	 * methode permettant de savoir si l'utilisateur est un Admin
	 * @return false
	 */
	public boolean isAdmin() {
		return false;
	}
	
}
