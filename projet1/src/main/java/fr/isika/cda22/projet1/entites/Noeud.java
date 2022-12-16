package fr.isika.cda22.projet1.entites;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
/**
 * Cette classe represente un Noeud d'une arbre binaire 
 * @author shmilany
 *
 */
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
	/**
	 * Constructeur permettant d'initaliser un noeud avec descendants
	 * 
	 * @param cle le stagiaire contenu dans le noeud
	 * @param indexFG indice vers le fils gauche du noeud 
	 * @param indexFD indice vers le fils droit du noeud 
	 * @param indexDB indice vers le doublon du noeud 
	 */
	public Noeud(Stagiaire cle, int indexFG, int indexFD, int indexDB) {
		super();
		this.cle = cle;
		this.indiceFG = indexFG;
		this.indiceFD = indexFD;
		this.indiceDB = indexDB;
	}
	
	/**
	 * Constructeur permettant d'initaliser un noeud sans descendants
	 * initialise les indices en -1 (non-existants)
	 * @param cle le stagiaire contenu dans le noeud
	 */
	public Noeud(Stagiaire cle) {
		this(cle, -1, -1, -1);
	}

	//getters & setters
	/**
	 * methode permettant d'acceder au TAILLE_NOEUD_OCTET
	 * @return TAILLE_NOEUD_OCTET
	 */
	public static int getTailleNoeudOctet() {
		return TAILLE_NOEUD_OCTET;
	}
	
	/**
	 * methode permettant d'acceder au stagiaire contenu dans le noeud
	 * @return le stagiair
	 */
	public Stagiaire getCle() {
		return cle;
	}
	
	/**
	 * methode permettant de modifier le stagiaire contenu dans le noeud
	 * @param cle le nouveau stagiaire 
	 */
	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}
	
	/**
	 * methode permettant d'acceder à l'indice du fils gauche
	 * @return indiceFG
	 */
	public int getIndiceFG() {
		return indiceFG;
	}

	/**
	 * methode permettant de modifier l'indice du fils gauche 
	 * @param nouveau indiceFG
	 */
	public void setIndiceFG(int indiceFG) {
		this.indiceFG = indiceFG;
	}
	
	/**
	 * methode permettant d'acceder à l'indice du fils droit
	 * @return indiceFD
	 */
	public int getIndiceFD() {
		return indiceFD;
	}

	/**
	 * methode permettant de modifier l'indice du fils droit
	 * @param nouveau indiceFD
	 */
	public void setIndiceFD(int indiceFD) {
		this.indiceFD = indiceFD;
	}

	/**
	 * methode permettant d'acceder à l'indice du doublon
	 * @return indiceDB
	 */
	public int getIndiceDB() {
		return indiceDB;
	}
	
	/**
	 * methode permettant de modifier l'indice du doublon 
	 * @param nouveau indiceDB
	 */
	public void setIndiceDB(int indiceDB) {
		this.indiceDB = indiceDB;
	}

	//methodes specifique
	/**
	 * methode permettant de lire un noeud d'un fichier binaire à partir d'un indice
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @param indiceDebut un int represenant l'indice de noeud à lire
	 * @return un Noeud
	 */
	public static Noeud readNoeudBin(RandomAccessFile raf, int indiceDebut) {
		try {
			indiceDebut *= TAILLE_NOEUD_OCTET; //convertir l'indice en octet
			Stagiaire temp = Stagiaire.readStagiaireBin(raf, indiceDebut); //lire le stagiaire à partir du l'indice
			Noeud n = new Noeud(temp, raf.readInt(), raf.readInt(), raf.readInt()); //lire les indices et initialiser le Noeud
			return n; 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * methode static permettant d'ecrire un noeud à la fin du fichier binaire
	 * @param cleAjouter le cle à ecrire dans le fichier
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 */
	public static void writeNoeudBin(Stagiaire cleAjouter, RandomAccessFile raf) {
		try {
			long length = raf.length(); //recuperer la taille de fichier binaire
			raf.seek(length); //depalcer le cursor vers la fin du fichier
			cleAjouter.writeStagiaireToBin(raf); //ecrire le stagiare
			raf.writeInt(-1); //ecrire les indices à -1 par défault
			raf.writeInt(-1);
			raf.writeInt(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * methode permettant d'ecrire le noeud dans un fichier binaire à une indice specifique
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @param indiceDebut int representant l'indice de debut
	 */
	public void writeNoeudBin(RandomAccessFile raf, int indiceDebut) {
		try {
			raf.seek(indiceDebut*TAILLE_NOEUD_OCTET); //depalcer le cursor vers l'indiceDebut
			this.getCle().writeStagiaireToBin(raf); //ecrire le stagiare
			raf.writeInt(this.getIndiceFG()); //ecrire les indices
			raf.writeInt(this.getIndiceFD()); 
			raf.writeInt(this.getIndiceDB());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @deprecated
	 * methode d'affichage n'est plus fonctionnelle pour la class Noeud
	 */
	@Override @Deprecated
	public String toString() {
		return "Method deprecated: utiliser la methode String toString(RandomAccessFile raf)";
	}
	
	/**
	 * methode d'affiche le noeud actuelle suivé par ses descendants
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @return String
	 */
	public String toString(RandomAccessFile raf) {
		String s = ""; //initialiser le String à retourner
		if (this.indiceFG != -1) { //verifier si FG existe
			Noeud fg = Noeud.readNoeudBin(raf, indiceFG); //lire le fils gauche
			s += fg.toString(raf); //appeler toString sur le FG et le concatener sur le string à retourner 
		}
		s += this.cle.toString() + " "; //concatener le cle sur le string à retourner 
		if (this.indiceDB != -1) { //cas de doublons
			Noeud db = Noeud.readNoeudBin(raf, indiceDB);
			s += db.toString(raf);
		}
		if (this.indiceFD != -1) { //cas de Fils Droit
			Noeud fd = Noeud.readNoeudBin(raf, indiceFD);
			s += fd.toString(raf);
		}
		return s;
	}
	
	/**
	 * methode permettant d'ajouter un Stagiaire selon l'ordre défini et l'ecrire à la fin du fichier
	 * @param cleAjouter Stagiaire à ajouter
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 */
	public void ajouterNoeud(Stagiaire cleAjouter, RandomAccessFile raf) {
		try {
			if(this.cle.compareTo(cleAjouter) > 0) { //si cleAjouter > this.cle
				if(this.indiceFD == -1) { //verifier si FD existe
					//FD n'existe pas
					this.indiceFD = (int) (raf.length() / TAILLE_NOEUD_OCTET); //convertir la taille du fichier en indice et l'affecter à l'indiceFD
					raf.seek(raf.getFilePointer() - 2*4); //deplacer le cursor jusqu'à la place de l'indiceFD
					raf.writeInt(this.indiceFD);// ecirer le nouveau indiceFD
					Noeud.writeNoeudBin(cleAjouter, raf); //ecrire le nouveau noeud à la fin du fichier
				} else { //FD existe
					Noeud fd = Noeud.readNoeudBin(raf, this.indiceFD); //lire le FD
					fd.ajouterNoeud(cleAjouter, raf); //faire l'appel recursif sur le FD
				}
			} else if(this.cle.compareTo(cleAjouter) < 0) { //si cleAjouter < this.cle
				if(this.indiceFG == -1) {
					this.indiceFG = (int) (raf.length() / TAILLE_NOEUD_OCTET);
					raf.seek(raf.getFilePointer() - 3*4);
					raf.writeInt(this.indiceFG);
					Noeud.writeNoeudBin(cleAjouter, raf);
				} else {
					Noeud fg = Noeud.readNoeudBin(raf, this.indiceFG);
					fg.ajouterNoeud(cleAjouter, raf);
				}
			}else { //meme ordre alphabetique
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

	/**
	 * methode pour ajouter le noeud et ses descendants en ArrayList tri selon l'ordre defini
	 * @param stgArray l'ArrayList à remplir avec le noeud et ses descendants
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 */
	public void toArray(ArrayList<Stagiaire> stgArray, RandomAccessFile raf) {
		if(this.indiceFG != -1) { //verifier si FG existe
			Noeud fg = Noeud.readNoeudBin(raf, indiceFG); //lire le fils gauche
			fg.toArray(stgArray, raf); //appel recursif sur le FG
		}
		stgArray.add(cle); //ajouter le cle actuel à l'ArrayList
		if(this.indiceDB != -1) { //cas de doublons
			Noeud db = Noeud.readNoeudBin(raf, indiceDB);
			db.toArray(stgArray, raf);
		}
		if(this.indiceFD != -1) { //cas de Fils Droit
			Noeud fd = Noeud.readNoeudBin(raf, indiceFD);
			fd.toArray(stgArray, raf);
		}
	}
	
	/**
	 * methode pour verifier si le noeud correspond aux criteres de recherche et l'ajouter en ArrayList
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @param stgArray stgArray l'ArrayList à remplir avec le noeud et ses descendants selon les criteres de recherche
	 * @param cleCritere Stagiaire avec les differents attributs recherchés
	 */
	public void rechercheCritere(RandomAccessFile raf, ArrayList<Stagiaire> stgArray, Stagiaire cleCritere) {
		if(this.indiceFG != -1) { //verifier si FG existe
			Noeud fg = Noeud.readNoeudBin(raf, indiceFG); //lire le fils gauche
			fg.rechercheCritere(raf, stgArray, cleCritere); //appel recursif sur le FG
		}
		if( this.cle.verifierCritere(cleCritere) ) { //verifier si le cle actuel correspond aux criteres
			stgArray.add(cle); //ajouter le cle actuel à l'ArrayList
		}
		if(this.indiceDB != -1) { //cas de doublons
			Noeud db = Noeud.readNoeudBin(raf, indiceDB);
			db.rechercheCritere(raf, stgArray, cleCritere);
		}
		if(this.indiceFD != -1) { //cas de Fils Droit
			Noeud fd = Noeud.readNoeudBin(raf, indiceFD);
			fd.rechercheCritere(raf, stgArray, cleCritere);
		}
	}
	
	/**
	 * methode qui retourne l'indice de noeud contenant le cle Max parmi le noeud et ses descendants 
	 * Max = le noeud le plus à droit
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @return l'indice de noeud contenant le cle Max parmi le noeud et ses descendants
	 */
	public int getIndiceMax(RandomAccessFile raf) {
		if(this.indiceFD == -1) { //verifier si FD
			try {
				return (int) raf.getFilePointer()/TAILLE_NOEUD_OCTET-1; //convertir la place actuel du cursos en indice et le retouner
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		} else { //FD existe
			Noeud fd = Noeud.readNoeudBin(raf, this.indiceFD); //lire le FD
			return fd.getIndiceMax(raf); //appel recursif
		}
	}
	
	/**
	 * methode qui retourne l'indice de noeud contenant le cle Min parmi le noeud et ses descendants
	 * Min = le noeud le plus à gauche
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @return l'indice de noeud contenant le cle Min parmi le noeud et ses descendants
	 */
	public int getIndiceMin(RandomAccessFile raf) {
		if(this.indiceFG == -1) { //verifier si FG
			try {
				return (int) raf.getFilePointer()/TAILLE_NOEUD_OCTET-1; //convertir la place actuel du cursos en indice et le retouner
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			Noeud fg = Noeud.readNoeudBin(raf, this.indiceFG); //lire le FG
			return fg.getIndiceMin(raf); //appel recursif
		}
	}
	
	/**
	 * methode qui retourne l'indice de noeud contenant le dernier double parmi le noeud et ses descendants
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @return l'indice de noeud contenant le dernier double parmi le noeud et ses descendants
	 */
	public int getIndiceDouble(RandomAccessFile raf) {
		if(this.indiceDB == -1) { //verifier si FG
			try {
				return (int) raf.getFilePointer()/TAILLE_NOEUD_OCTET; //convertir la place actuel du cursos en indice et le retouner
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			Noeud db = Noeud.readNoeudBin(raf, indiceDB); //lire le FG
			return db.getIndiceDouble(raf); //appel recursif
		}
	}

	/**
	 * methode permettant de rechercher un Stagiaire et retourer l'indice de 1er cle le correspondant
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @param cleRecherche Stagiaire avec les attributs à rechercher
	 * @return l'indice du 1er noued contenat le cle correspondant aux criteres
	 * @throws IOException
	 */
	public int rechercheNoeud(RandomAccessFile raf, Stagiaire cleRecherche) throws IOException {
		if(this.getCle().compareTo(cleRecherche) > 0 && this.getIndiceFD() != -1) { //verifier si cleRecherche > this.cle et si FD existe
			return Noeud.readNoeudBin(raf, this.indiceFD).rechercheNoeud(raf, cleRecherche); //appel recursif sur le FD
		} else if(this.getCle().compareTo(cleRecherche) < 0 && this.getIndiceFG() != -1) { //verifier si cleRecherche > this.cle et si FG existe
			return Noeud.readNoeudBin(raf, this.indiceFG).rechercheNoeud(raf, cleRecherche); //appel recursif sur le FG
		} else { //meme ordre difini
			if(this.getCle().verifierCritere(cleRecherche)) {//verifier correspondance à tous les criteres
				return (int) raf.getFilePointer()/TAILLE_NOEUD_OCTET-1; //convertir la place actuel du cursos en indice et le retouner
			} else if (this.getIndiceDB() != -1){ //verifier dans les doublons
				return Noeud.readNoeudBin(raf, this.indiceDB).rechercheNoeud(raf, cleRecherche); //appel recursif sur le FG
			}
		}
		return -1;
	}

	/**
	 * methode pour verifier si le noeud correspond aux criteres de recherche et l'ajouter en ArrayList et 
	 * selon le mode de recherche souhaité parmis les trois option:
	 * - contient : l'attribut de stagiaire recherché contient le critere de recherche
	 * - debut : l'attribut de stagiaire recherché commence avec le critere de recherche
	 * - exacte : l'attribut de stagiare est le critere recherché
	 * @param raf un RandomAccessFile donant accès au fichier binaire ouvert
	 * @param stgArray stgArray l'ArrayList à remplir avec le noeud et ses descendants selon les criteres de recherche
	 * @param String representant le mode de recherche (contient, debut, exacte)
	 * @param cleCritere Stagiaire avec les differents attributs recherchés
	 */
	public void rechercheCritere(RandomAccessFile raf, ArrayList<Stagiaire> stgArray, Stagiaire cleCritere,
			String mode) {
		if(this.indiceFG != -1) { //verifier si FG existe
			Noeud fg = Noeud.readNoeudBin(raf, indiceFG); //lire le fils gauche
			fg.rechercheCritere(raf, stgArray, cleCritere, mode); //appel recursif sur le FG
		}
		if( this.cle.verifierCritere(cleCritere, mode) ) { //verifier si le cle actuel correspond aux criteres
			stgArray.add(cle); //ajouter le cle actuel à l'ArrayList
		}
		if(this.indiceDB != -1) { //cas de doublons
			Noeud db = Noeud.readNoeudBin(raf, indiceDB);
			db.rechercheCritere(raf, stgArray, cleCritere, mode);
		}
		if(this.indiceFD != -1) { //cas de Fils Droit
			Noeud fd = Noeud.readNoeudBin(raf, indiceFD);
			fd.rechercheCritere(raf, stgArray, cleCritere, mode);
		}
	}	
	
}
