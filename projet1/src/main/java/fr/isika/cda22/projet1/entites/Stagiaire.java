package fr.isika.cda22.projet1.entites;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Pattern;

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
		super(nom.toUpperCase(), prenom);
		this.localisation = localisation;
		this.nomFormation = nomFormation.toUpperCase();
		if(isNumeric(anneePromo)) {
			this.anneePromo = anneePromo;
		} else {
			this.anneePromo = "0";
		}
	}
	
	public Stagiaire(Map<String, String> map) {
		this(map.get("Nom"), map.get("Prenom"), map.get("Localisation"), 
				map.get("Nom de la Formation"), map.get("Annee Promo"));
	}
	
	private String capitalize(String original) {
		if(original.contains(" ")) {
			String[] s = original.split(" ");
			if(s.length == 1) {
				return capitalizeWord(original);
			} else {
				String capitalized = "";
				for(String sub: s) {
					capitalized += capitalizeWord(sub) + " ";
				}
				return capitalized.substring(0,capitalized.length()-1);
			}
		} else if(original.contains("-")) {
			String[] s = original.split("-");
			if(s.length == 1) {
				return capitalizeWord(original);
			} else {
				String capitalized = "";
				for(String sub: s) {
					capitalized += capitalizeWord(sub) + "-";
				}
				return capitalized.substring(0,capitalized.length()-1);
			}
		}
		return capitalizeWord(original);
	}
	
	private String capitalizeWord(String word) {
		if(word.length() > 1) {
			return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();	
		}
		return word;
	}
	
	public boolean isNumeric(String strNum) {
    	Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?"); 
    	if(strNum == null) {
    		return false;
    	} else {
    		return pattern.matcher(strNum).matches();
    	}
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
	
	public String longString(String s) {
		String sLong = s;
		if(sLong.length() > TAILLE_MAX_STRING) {
			sLong  = sLong.substring(0,TAILLE_MAX_STRING);
		}else {
			for(int i = sLong.length(); i < TAILLE_MAX_STRING; i++) {
				sLong += " ";
			}
		}
		return sLong;
	}
	
	public String longToString() {
		return longString(this.getNom()) + longString(this.getPrenom()) + longString(this.localisation) +
				longString(this.nomFormation) + longString(this.anneePromo);
	}
	
	public void writeStagiaireToBin(RandomAccessFile raf) {
		try {
			raf.writeChars(this.longToString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Stagiaire readStagiaireBin(RandomAccessFile raf, int indiceDebut) {
		try {
			raf.seek(indiceDebut);
			String nom = "";
			for(int j = 0; j < Stagiaire.getTailleMaxString(); j++) {
				nom += raf.readChar();
			}
			String prenom = "";
			for(int j = 0; j < Stagiaire.getTailleMaxString(); j++) {
				prenom += raf.readChar();
			}
			String localisation = "";
			for(int j = 0; j < Stagiaire.getTailleMaxString(); j++) {
				localisation += raf.readChar();
			}
			String nomFormation = "";
			for(int j = 0; j < Stagiaire.getTailleMaxString(); j++) {
				nomFormation += raf.readChar();
			}
			String anneePromo = "";
			for(int j = 0; j < Stagiaire.getTailleMaxString(); j++) {
				anneePromo += raf.readChar();
			}
			return new Stagiaire(nom.trim(), prenom.trim(), localisation.trim(), nomFormation.trim(), anneePromo.trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean verifierCritere(Stagiaire cleCritere) {
		if( (cleCritere.getNom() == null || this.getNom().equals(cleCritere.getNom())) &&
				(cleCritere.getPrenom() == null || this.getPrenom().equals(cleCritere.getPrenom()))	&&
				(cleCritere.localisation == null || this.localisation.equals(cleCritere.localisation)) &&
				(cleCritere.nomFormation == null || this.nomFormation.equals(cleCritere.nomFormation)) &&
				(cleCritere.anneePromo == null || this.anneePromo.equals(cleCritere.anneePromo))) {
			return true;
		}
		return false;
	}

		
	
}
