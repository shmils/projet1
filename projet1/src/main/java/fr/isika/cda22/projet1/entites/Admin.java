package fr.isika.cda22.projet1.entites;

public class Admin extends Formatteur {

	//constructeurs
	public Admin(String nom, String prenom, String nomUtilisateur, String motDePasse) {
		super(nom, prenom, nomUtilisateur, motDePasse);
	}
	
	//methodes specifiques
	public boolean isAdmin() {
		return true;
	}
	
}
