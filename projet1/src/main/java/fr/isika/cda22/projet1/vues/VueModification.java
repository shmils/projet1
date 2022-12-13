package fr.isika.cda22.projet1.vues;

import fr.isika.cda22.projet1.entites.Stagiaire;

public class VueModification extends VueEnregistrement{

	//attributs
	private Stagiaire ancienStagiaire;
	
	public Stagiaire getAncienStagiaire() {
		return ancienStagiaire;
	}

	public void setAncienStagiaire(Stagiaire ancienStagiaire) {
		this.ancienStagiaire = ancienStagiaire;
	}

	public VueModification(String tfNom,String tfPrenom,String tfLocalisation,String tfFormation,String tfAnneePromo) {
		super();
		this.getTitrePage().setText("Modifier le stagiaire");
		this.getTfNom().setText(tfNom);
		this.getTfPrenom().setText(tfPrenom);
		this.getTfLocalisation().setText(tfLocalisation);
		this.getTfFormation().setText(tfFormation);
		this.getTfAnneePromo().setText(tfAnneePromo);
		this.getBtnConfirmation().setText("Modification");
	}
	
	public VueModification() {
		super();
		this.getTitrePage().setText("Modifier le stagiaire");
		this.getBtnConfirmation().setText("Modification");
	}
	
	public void remplirFields() {
		this.getTfNom().setText(ancienStagiaire.getNom());
		this.getTfPrenom().setText(ancienStagiaire.getPrenom());
		this.getTfLocalisation().setText(ancienStagiaire.getLocalisation());
		this.getTfFormation().setText(ancienStagiaire.getNomFormation());
		this.getTfAnneePromo().setText(ancienStagiaire.getAnneePromo());
	}
	
}
