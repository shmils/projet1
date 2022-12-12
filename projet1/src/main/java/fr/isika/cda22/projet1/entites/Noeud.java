package fr.isika.cda22.projet1.entites;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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
	
	public void writeNoeudBin(RandomAccessFile raf, int indiceDebut) {
		try {
			raf.seek(indiceDebut*TAILLE_NOEUD_OCTET);
			this.getCle().writeStagiaireToBin(raf);
			raf.writeInt(this.getIndiceFG());
			raf.writeInt(this.getIndiceFD());
			raf.writeInt(this.getIndiceDB());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override @Deprecated
	public String toString() {
		return "Method deprecated: utiliser la methode String toString(RandomAccessFile raf)";
	}
	
	public String toString(RandomAccessFile raf) {
		String s = "";
		if (this.indiceFG != -1) {
			Noeud fg = Noeud.readNoeudBin(raf, indiceFG);
			s += fg.toString(raf);
		}
		s += this.cle.toString() + " ";
		if (this.indiceDB != -1) {
			Noeud db = Noeud.readNoeudBin(raf, indiceDB);
			s += db.toString(raf);
		}
		if (this.indiceFD != -1) {
			Noeud fd = Noeud.readNoeudBin(raf, indiceFD);
			s += fd.toString(raf);
		}
		return s;
	}
	
	public void ajouterNoeud(Stagiaire cleAjouter, RandomAccessFile raf) {
		try {
			if(this.cle.compareTo(cleAjouter) > 0) { //si cleAjouter > this.cle
				if(this.indiceFD == -1) {
					this.indiceFD = (int) (raf.length() / TAILLE_NOEUD_OCTET);
					raf.seek(raf.getFilePointer() - 2*4);
					raf.writeInt(this.indiceFD);
					Noeud.writeNoeudBin(cleAjouter, raf);
				} else {
					Noeud fd = Noeud.readNoeudBin(raf, this.indiceFD);
					fd.ajouterNoeud(cleAjouter, raf);
				}
			} else if(this.cle.compareTo(cleAjouter) < 0) {
				if(this.indiceFG == -1) {
					this.indiceFG = (int) (raf.length() / TAILLE_NOEUD_OCTET);
					raf.seek(raf.getFilePointer() - 3*4);
					raf.writeInt(this.indiceFG);
					Noeud.writeNoeudBin(cleAjouter, raf);
				} else {
					Noeud fg = Noeud.readNoeudBin(raf, this.indiceFG);
					fg.ajouterNoeud(cleAjouter, raf);
				}
			}else {
				if(this.indiceDB == -1) {
					this.indiceDB = (int) (raf.length() / TAILLE_NOEUD_OCTET);
					raf.seek(raf.getFilePointer() - 1*4);
					raf.writeInt(this.indiceDB);
					Noeud.writeNoeudBin(cleAjouter, raf);
				} else {
					Noeud db = Noeud.readNoeudBin(raf, this.indiceDB);
					db.ajouterNoeud(cleAjouter, raf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toArray(ArrayList<Stagiaire> stgArray, RandomAccessFile raf) {
		if(this.indiceFG != -1) {
			Noeud fg = Noeud.readNoeudBin(raf, indiceFG);
			fg.toArray(stgArray, raf);
		}
		stgArray.add(cle);
		if(this.indiceDB != -1) {
			Noeud db = Noeud.readNoeudBin(raf, indiceDB);
			db.toArray(stgArray, raf);
		}
		if(this.indiceFD != -1) {
			Noeud fd = Noeud.readNoeudBin(raf, indiceFD);
			fd.toArray(stgArray, raf);
		}
	}
	
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
