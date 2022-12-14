package fr.isika.cda22.projet1.vues;

import fr.isika.cda22.projet1.entites.Stagiaire;
/**
 * Classe representant la scene de modifier d'un Stagiaire
 * 
 * @author PAUBEL Paul
 *
 */
public class VueModification extends VueEnregistrement{

	//attributs
	private Stagiaire ancienStagiaire;
	
	/**
	 * methode permettant d'acceder au ancienStagiaire
	 * @return Stagiaire
	 */
	public Stagiaire getAncienStagiaire() {
		return ancienStagiaire;
	}
	
	/**
	 * methode permettant de modifier ancienStagiaire
	 * @param ancienStagiaire
	 */
	public void setAncienStagiaire(Stagiaire ancienStagiaire) {
		this.ancienStagiaire = ancienStagiaire;
	}

	/**
	 * Constructeur permettant d'initialiser un VueModifcation prerempli d'une texte generique
	 * @param nom 
	 * @param prenom
	 * @param localisation
	 * @param formation
	 * @param anneePromo
	 */
	public VueModification(String nom,String prenom,String localisation,String formation,String anneePromo) {
		super(); //constructeur par default du vueEnregistrement
		this.getTitrePage().setText("Modifier le stagiaire"); //changer Titre 
		this.getTfNom().setText(nom); //
		this.getTfPrenom().setText(prenom);
		this.getTfLocalisation().setText(localisation);
		this.getTfFormation().setText(formation);
		this.getTfAnneePromo().setText(anneePromo);
		this.getBtnConfirmation().setText("Modification"); //changer text du button
	}
	
	/**
	 * Constructeur permettant d'initialiser un VueModification pas prerempli
	 */
	public VueModification() {
		super(); //constructeur par default du vueEnregistrement
		this.getTitrePage().setText("Modifier le stagiaire"); //changer Titre
		this.getBtnConfirmation().setText("Modification");//changer text du button
	}
	
	/**
	 * methode permetant de remplir les differents fields avec les attributs d'ancienStagiaire
	 */
	public void remplirFields() {
		this.getTfNom().setText(ancienStagiaire.getNom());
		this.getTfPrenom().setText(ancienStagiaire.getPrenom());
		this.getTfLocalisation().setText(ancienStagiaire.getLocalisation());
		this.getTfFormation().setText(ancienStagiaire.getNomFormation());
		this.getTfAnneePromo().setText(ancienStagiaire.getAnneePromo());
	}
	
}
