package fr.isika.cda22.projet1.entites;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Cette classe represente un Stagiaire
 * 
 * extends @see {@link fr.isika.cda22.projet1.entities.Personne}
 * 
 * @author shmilany
 *
 */
public class Stagiaire extends Personne{
	
	//attributs static
	private final static int TAILLE_MAX_STRING = 25; //un string ne contient pas plus que 12 characters
	private final static int TAILLE_STAGIAIRE_OCTET = 5*(2*TAILLE_MAX_STRING); //nb Attribut String* (octet(Char)*Taille Max)
	
	//attributs
	private String localisation;
	private String nomFormation;
	private String anneePromo;
	
	//constructeur
	/**
	 * Constructuer permetteant d'initialiser un Stagiaire
	 * 
	 * @param nom nom du stagiaire est transformé en UpperCase
	 * @param prenom prenom du stagiaire est transformé en Titre
	 * @param localisation Departement ou Ville du stagiaire
	 * @param nomFormation nom de la formation est transformeé en UpperCase
	 * @param anneePromo annee de la promotion est verifié si numeric
	 */
	public Stagiaire(String nom, String prenom, String localisation, String nomFormation, String anneePromo) {
		super(nom, prenom);
		this.localisation = localisation;
		this.nomFormation = Verificateur.upperCase(nomFormation);
		if(Verificateur.isNumeric(anneePromo)) {
			this.anneePromo = anneePromo;
		} 
	}
	
	/**
	 * Constructeur permettant d'initialiser un Stagiaire à partir d'un @see {@link java.util.Map}
	 * 
	 * @param map
	 */
	public Stagiaire(Map<String, String> map) {
		this(map.get("Nom"), map.get("Prenom"), map.get("Localisation"), 
				map.get("Nom de la Formation"), map.get("Annee Promo"));
	}
	
	/**
	 * Constructeur permettant de creer un copy d'un stagiaire
	 * @param this Stagiaire a copié
	 */
	public Stagiaire(Stagiaire stg){
		this(stg.getNom(), stg.getPrenom(), stg.getLocalisation(), stg.getNomFormation(), stg.getAnneePromo());
	}
	
	//getters & setters
	/**
	 * methode statique permettant d'aceder à la taille max d'un string
	 * @return TAILLE_MAX_STRING
	 */
	public static int getTailleMaxString() {
		return TAILLE_MAX_STRING;
	}

	/**
	 * methode statique permettant d'aceder à la taille en octet d'un stagiaire
	 * 
	 * @return TAILLE_STAGIAIRE_OCTET
	 */
	public static int getTailleStagiaireOctet() {
		return TAILLE_STAGIAIRE_OCTET;
	}
		
	/**
	 * methode permettant d'acceder à la loclalisation du stagiaire
	 * @return localisation
	 */
	public String getLocalisation() {
		return localisation;
	}

	/**
	 * methode permettat de changer la localisation du stagiaire
	 * @param localisation
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**
	 * methode permettant d'acceder au nom de la foramtion du stagiaire
	 * @return nomFormation
	 */
	public String getNomFormation() {
		return nomFormation;
	}

	/**
	 * methode permettat de changer le nom de la formation du stagiaire
	 * @param nomFormation
	 */
	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}
	
	/**
	 * methode permettant d'acceder à l'année du stagiaire
	 * @return anneePromo
	 */
	public String getAnneePromo() {
		return anneePromo;
	}
	
	/**
	 * methode permettat de changer l'annee de la promotion du stagiaire
	 * @param anneePromo
	 */
	public void setAnneePromo(String anneePromo) {
		this.anneePromo = anneePromo;
	}

	//methodes specifiques
	/**
	 * methode permettant d'afficher le stagiaire 
	 * 
	 * return String
	 */
	@Override
	public String toString() {
		return "(" + super.getNom() + " " + super.getPrenom() + " " + localisation + " " + nomFormation + " " + anneePromo + ")";
	}
	
	//comparaison : ordre alphabetique du nom
	/**
	 * methode permettant de comparer le stagiaire à un autre selon l'ordre alphabetique du nom
	 * 
	 * @param autreStagiaire
	 * @return un entier negatif si le stagiaire succede l'autre dans l'ordre alphabetique du nom
	 * un entier positif si le stagiaire precede l'autre dans l'ordre alphabetique du nom
	 * 0 si les deux stagiare ont le même nom
	 */
	public int compareTo(Stagiaire autreStagiaire) {
		return autreStagiaire.getNom().compareTo(this.getNom());
	}
	
	 /**
	  * methode permettant de mettre un String à la taille max d'un String
	  * @param s
	  * @return un String d'une taille du TAILLE_MAX_STRING
	  * si taille de s est < TAILLE_MAX_STRING, la methode retourne s concatinées avec des espaces
	  * si taille d s est > TAILLE_MAX_STRING, les premiers TAILLE_MAX_STRING caracteres de s sont retourné
	  */
	public String longString(String s) {
		String sLong = s; //stocker une copie de string dans sLong
		if(sLong.length() > TAILLE_MAX_STRING) { //verifier la taille de sLong vs TAILLE_MAX_STRING
			sLong  = sLong.substring(0,TAILLE_MAX_STRING); //prendre les premiers TAILLE_MAX_STRING caracteres
		}else { // taille sLong < TAILLE_MAX_STRING
			for(int i = sLong.length(); i < TAILLE_MAX_STRING; i++) { 
				sLong += " "; //ajouter un " " à sLong jusqu'à attendre TAILLE_MAX_STRING
			}
		}
		return sLong; //retourner sLong
	}
	
	/**
	 * methode permettant de transformer le stagiaire en String composé de ses attributs mis à la taille max d'un string
	 * @return String
	 */
	public String longToString() {
		return longString(this.getNom()) + longString(this.getPrenom()) + longString(this.localisation) +
				longString(this.nomFormation) + longString(this.anneePromo);
	}
	
	/**
	 * methode permettant d'ecrire un stagiaire dans un fichier binaire
	 * @param raf un RandomAccessFile donant acces au fichier binaire ouvert
	 */
	public void writeStagiaireToBin(RandomAccessFile raf) {
		try {
			raf.writeChars(this.longToString()); //ecrire le stagiaire (mis à taille) dans le fichier binaire
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * methode permettant de construire un Stagiaire à partir d'un fichier binaire
	 * @param raf un RandomAccessFile donant acces au fichier binaire ouvert
	 * @param indiceDebut l'indice de debut de lecture dans le fichier
	 * @return un Stagiaire
	 */
	public static Stagiaire readStagiaireBin(RandomAccessFile raf, int indiceDebut) {
		try {
			raf.seek(indiceDebut); //deplacer le cursor ver l'indiceDebut
			String nom = ""; //initialiser un nom
			for(int j = 0; j < Stagiaire.getTailleMaxString(); j++) {
				nom += raf.readChar(); //lire un char du fichier et l'ajouter au nom
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
			//retourner un stagiaire initialisé à partir des differents variables après la suppresion des espaces en + 
			return new Stagiaire(nom.trim(), prenom.trim(), localisation.trim(), nomFormation.trim(), anneePromo.trim());  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * methode permettant de verifier si les attributs du stagiaire commence 
	 * avec les attributs d'un stagiaire recherché s'ils ne sont pas null 
	 * 
	 * @param cleCritere un stagiare contenant les attributs recherché
	 * @return true si tous les attribut du stagiaire commence avec les attributs (pas null) de cleCritere 
	 * false si non
	 */
	public boolean verifierCritere(Stagiaire cleCritere) {
		//verifier si chaque attribut du stagiaire est egal aux attributs non null de cleCritere
//		return ((cleCritere.getNom() == null || this.getNom().equals(cleCritere.getNom())) &&
//				(cleCritere.getPrenom() == null || this.getPrenom().equals(cleCritere.getPrenom()))	&&
//				(cleCritere.localisation == null || this.localisation.equals(cleCritere.localisation)) &&
//				(cleCritere.nomFormation == null || this.nomFormation.equals(cleCritere.nomFormation)) &&
//				(cleCritere.anneePromo == null || this.anneePromo.equals(cleCritere.anneePromo)));

		//verifier si chaque attribut du stagiaire contient avec les attributs non null de cleCritere
//		return ((cleCritere.getNom() == null || this.getNom().contains(cleCritere.getNom())) &&
//				(cleCritere.getPrenom() == null || this.getPrenom().contains(cleCritere.getPrenom()))	&&
//				(cleCritere.localisation == null || this.localisation.contains(cleCritere.localisation)) &&
//				(cleCritere.nomFormation == null || this.nomFormation.contains(cleCritere.nomFormation)) &&
//				(cleCritere.anneePromo == null || this.anneePromo.equals(cleCritere.anneePromo)));
		
		//verifier si chaque attribut du stagiaire commence avec les attributs non null de cleCritere
		return ((cleCritere.getNom() == null || this.getNom().startsWith(cleCritere.getNom())) &&
				(cleCritere.getPrenom() == null || this.getPrenom().startsWith(cleCritere.getPrenom()))	&&
				(cleCritere.localisation == null || this.localisation.startsWith(cleCritere.localisation)) &&
				(cleCritere.nomFormation == null || this.nomFormation.startsWith(cleCritere.nomFormation)) &&
				(cleCritere.anneePromo == null || this.anneePromo.startsWith(cleCritere.anneePromo)));
	}

}
