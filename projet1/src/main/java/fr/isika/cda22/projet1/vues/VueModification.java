package fr.isika.cda22.projet1.vues;

public class VueModification extends VueEnregistrement{

	//attributs
	
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
	
}
