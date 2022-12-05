package fr.isika.cda22.projet1.entites;

public class Noeud {
	
	//attributs
	private Stagiaire cle;
	private Noeud filsGauche;
	private Noeud filsDroit;
	
	//constructeur
	public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
		this.cle = cle;
		this.filsDroit = filsDroit;
		this.filsGauche = filsGauche;
	}

	//getters & setters
	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}
	
	//methodes specifique
	public String toString() {
		String s = "";
		if(this.filsGauche != null) {
			s += this.filsGauche.toString();
		}
		s += this.cle.toString();
		if(this.filsDroit != null) {
			s += this.filsDroit.toString();
		}
		return s;
	}
	
	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
		if(this.cle.compareTo(nouveauStagiaire) < 0) {
			if(this.filsDroit == null) {
				this.filsDroit = new Noeud(nouveauStagiaire, null, null);
			} else {
				this.filsDroit.ajouterStagiaire(nouveauStagiaire);
			}
		} else if(this.cle.compareTo(nouveauStagiaire) > 0){
			if(this.filsGauche == null) {
				this.filsGauche  = new Noeud(nouveauStagiaire, null, null);
			} else {
				this.filsGauche.ajouterStagiaire(nouveauStagiaire);
			}
		} else {
			System.out.println("!!!" + nouveauStagiaire + " existe déjà! T'es fatiguéeee !!!");
		}
	}
	
	
}
