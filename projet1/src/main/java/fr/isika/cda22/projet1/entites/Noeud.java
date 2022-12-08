package fr.isika.cda22.projet1.entites;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud {
	
	//attributs static
	// Taille Stagiaire Octet + (nbAttributInt)*octet(int) (taille int en octet = 4)
	private final static int TAILLE_NOEUD_OCTET = Stagiaire.getTailleStagiaireOctet() + 3*4;
	
	//attributs
	private Stagiaire cle;
	private int indiceFG;
	private int indiceFD;
	private int indiceDB;
	
	//constructeur
	public Noeud(Stagiaire cle, int indexFG, int indexFD, int indexDB) {
		super();
		this.cle = cle;
		this.indiceFG = indexFG;
		this.indiceFD = indexFD;
		this.indiceDB = indexDB;
	}
	
	public Noeud(Stagiaire cle) {
		this(cle, -1, -1, -1);
	}

	//getters & setters
	
	public static int getTailleNoeudOctet() {
		return TAILLE_NOEUD_OCTET;
	}
	
	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}
	
	public int getIndiceFG() {
		return indiceFG;
	}

	public void setIndiceFG(int indiceFG) {
		this.indiceFG = indiceFG;
	}

	public int getIndiceFD() {
		return indiceFD;
	}

	public void setIndiceFD(int indiceFD) {
		this.indiceFD = indiceFD;
	}

	public int getIndiceDB() {
		return indiceDB;
	}

	public void setIndiceDB(int indiceDB) {
		this.indiceDB = indiceDB;
	}

	//methodes specifique
	public static Noeud readNoeudBin(RandomAccessFile raf, int indiceDebut) {
		try {
			indiceDebut *= TAILLE_NOEUD_OCTET;
			Stagiaire temp = Stagiaire.readStagiaireBin(raf, indiceDebut);
			Noeud n = new Noeud(temp, raf.readInt(), raf.readInt(), raf.readInt());
			return n;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeNoeudBin(Stagiaire cleAjouter, RandomAccessFile raf) {
		try {
			long length = raf.length();
			raf.seek(length);
			cleAjouter.writeStagiaireToBin(raf);
			raf.writeInt(-1);
			raf.writeInt(-1);
			raf.writeInt(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public String toString() {
//		String s = "";
//		if(this.filsGauche != null) {
//			s += this.filsGauche.toString();
//		}
//		s += this.cle.toString();
//		if(this.filsDroit != null) {
//			s += this.filsDroit.toString();
//		}
//		return s;
//	}
	
//	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
//		if(this.cle.compareTo(nouveauStagiaire) < 0) {
//			if(this.filsDroit == null) {
//				this.filsDroit = new Noeud(nouveauStagiaire, null, null);
//			} else {
//				this.filsDroit.ajouterStagiaire(nouveauStagiaire);
//			}
//		} else if(this.cle.compareTo(nouveauStagiaire) > 0){
//			if(this.filsGauche == null) {
//				this.filsGauche  = new Noeud(nouveauStagiaire, null, null);
//			} else {
//				this.filsGauche.ajouterStagiaire(nouveauStagiaire);
//			}
//		} else {
//			System.out.println("!!!" + nouveauStagiaire + " existe déjà! T'es fatiguéeee !!!");
//		}
//	}
//
//	public Noeud rechercherStagiaire(Stagiaire stagiaire) {
//		if (this.cle.compareTo(stagiaire) == 0) {
//			return this;
//		} else if(this.cle.compareTo(stagiaire) < 0) {
//			if(this.filsDroit == null) {
//				return null;
//			} else {
//				return this.filsDroit.rechercherStagiaire(stagiaire);
//			}
//		} else {
//			if(this.filsGauche == null) {
//				return null;
//			} else {
//				return this.filsGauche.rechercherStagiaire(stagiaire);
//			}
//		}
//	}
	
	
}
