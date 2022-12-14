package fr.isika.cda22.projet1.entites;

/**
 * 
 * cette classe represente un formatteur Admin
 * 
 * extends @see {@link fr.isika.cda22.projet1.entities.Formatteur}
 * 
 * @author shmilany
 *
 */
public class Admin extends Formatteur {

	//constructeurs
	/**
	 * Constructeur permettant d'initialiser un admin
	 * 
	 * @param nom nom de l'admin
	 * @param prenom prenom de l'admin
	 * @param nomUtilisateur nom utilisateur de l'admin 
	 * @param motDePasse le mot de Passe de l'admin
	 */
	public Admin(String nom, String prenom, String nomUtilisateur, String motDePasse) {
		super(nom, prenom, nomUtilisateur, motDePasse);
	}
	
	//methodes specifiques
	
	/**
	 * methode permettant de savoir si l'utilisateur est un Admin
	 * @return true
	 */
	@Override
	public boolean isAdmin() {
		return true;
	}
	
}
