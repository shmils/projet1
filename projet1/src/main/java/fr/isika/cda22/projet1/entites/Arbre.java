package fr.isika.cda22.projet1.entites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Map;

/**
 * Cette classe represente l'arbre binaire et la gestion de cette arbre dans un fichier binaire 
 * @author shmilany
 *
 */
public class Arbre {
	
	//attributs
	private RandomAccessFile raf;
	private int indiceRacine;
	private String fileName; 
	
	//constructeur
	/**
	 * Constructeur permettant d'initialiser un arbre avec un RandomAccessFile (raf)
	 * si le raf n'existe pas, il le crée 
	 * @param fileName le path vers le fichier binaire 
	 */
	public Arbre(String fileName) {
		super();
		try {
			this.fileName = fileName; 
			raf = new RandomAccessFile(new File(fileName), "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.indiceRacine = 0;
	}
	
	//getters & setters
	
	/**
	 * methode permettant d'acceder au RAF
	 * @return RandomAccessFile
	 */
	public RandomAccessFile getRaf() {
		return raf;
	}

	/**
	 * methode permettant de changer le RAF
	 * @param raf nouveau RandomAccessFile à associer à l'arbre
	 */
	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}
	
	/**
	 * methode permettant d'acceder à l'indiceRacine de l'arbre
	 * @return int
	 */
	public int getIndiceRacine() {
		return indiceRacine;
	}

	//methodes specifiques
	
	/**
	 * methode permettant de verifier si l'arbre est vide
	 * @return false si la taille de RAF different que 0
	 * @return true si la taille de RAF est 0
	 */
	public boolean isEmpty() {
		try {
			return (this.raf.length() == 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * methode permettant d'afficher tous les noeuds de l'arbre dans l'ordre defini
	 * @return String
	 */
	@Override
	public String toString() {
		if (this.isEmpty()) { //verfier si l'abre est vide
			return "Arbre est vide";
		}else {
			return Noeud.readNoeudBin(raf, this.indiceRacine).toString(raf); //lire le 1er noeud et appel de la methode toString sur lui
		}
	}
	
	/**
	 * methode permettant d'ajouter un stagiaire selon l'ordre de l'arbre
	 * @param stagiaire le stagiaire à ajouter
	 */
	public void ajouterStagiaire(Stagiaire stagiaire){
		if(this.isEmpty()) { //verfier si l'abre est vide
			Noeud.writeNoeudBin(stagiaire, raf); //ecrire le stagiaire à la fin
		}else {
			Noeud n0 = Noeud.readNoeudBin(raf, this.indiceRacine); //lire le 1er noeud
			n0.ajouterNoeud(stagiaire, raf); //appeler la methode ajouterNoeud sur le 1er noeud
		}
	}
	
	/**
	 * methode permettant d'ajouter un stagiaire dans un ArrayList selon l'ordre de l'arbre
	 * @return un ArrayList contenant tous les stagiaires dans l'ordre
	 */
	public ArrayList<Stagiaire> toArray() {
		ArrayList<Stagiaire> stgArray = new ArrayList<>(); //initialiser un ArrayList vide
		if(!this.isEmpty()) { //verfier si l'abre est vide
			Noeud.readNoeudBin(raf, this.indiceRacine).toArray(stgArray, raf); //appeler la methode toArray sur le 1er noeud
		}
		return stgArray;
	}
	
	/**
	 * methode permettant de recuperer les Stagiaires correspondants aux criteres de recherche et les ajouter dans un ArrayList selon
	 * l'ordre de l'arbre
	 * @param mapCriteres un Map content les differents attributs de recherche
	 * @return un ArrayList contenant les Stagiaires correspondants aux criteres
	 */
	public ArrayList<Stagiaire> rechercheCritere(Map<String, String> mapCriteres){
		ArrayList<Stagiaire> stgArray = new ArrayList<>(); //initialiser un ArrayList vide
		if(!this.isEmpty()) { //verifier si l'arbre est vide 
			Noeud.readNoeudBin(raf, this.indiceRacine).rechercheCritere(raf, stgArray, new Stagiaire(mapCriteres)); //lire le 1er noeud et faire la recherche sur lui
		}
		return stgArray; //retourner l'ArrayList
	}

	/**
	 * methode permettant de recuperer les Stagiaires correspondants aux criteres de recherche et les ajouter dans un ArrayList selon
	 * l'ordre de l'arbre et selon le mode de recherche souhaité parmis les trois option:
	 * - contient : l'attribut de stagiaire recherché contient le critere de recherche
	 * - debut : l'attribut de stagiaire recherché commence avec le critere de recherche
	 * - exacte : l'attribut de stagiare est le critere recherché
	 * @param mapCriteres un Map content les differents attributs de recherche
	 * @param String representant le mode de recherche (contient, debut, exacte)
	 * @return un ArrayList contenant les Stagiaires correspondants aux criteres
	 */
	public ArrayList<Stagiaire> rechercheCritere(Map<String, String> mapCriteres, String mode){
		ArrayList<Stagiaire> stgArray = new ArrayList<>(); //initialiser un ArrayList vide
		if(!this.isEmpty() && (mode.equals("contient") || mode.equals("debut") || mode.equals("exacte"))) { //verifier si l'arbre est vide 
			Noeud.readNoeudBin(raf, this.indiceRacine).rechercheCritere(raf, stgArray, new Stagiaire(mapCriteres), mode); //lire le 1er noeud et faire la recherche sur lui
		}
		return stgArray; //retourner l'ArrayList
	}
	
	/**
	 * methode permettant de recherche un Stagiarie et retouner l'indice de son 1er occurance dans l'arbre
	 * @param cleRecherche
	 * @return l'indice de 1er occurence
	 */
	public int rechercheNoeud(Stagiaire cleRecherche){
		if(this.isEmpty()) { //verifier si arbre est vide
			return -1; //retourner -1 pour non existant
		} else {
			try {
				return Noeud.readNoeudBin(raf, this.indiceRacine).rechercheNoeud(raf, cleRecherche); //lire le 1er noeud et faire la recherche sur lui
			} catch (IOException e) {
				e.printStackTrace();
				return -1;//retourner -1 pour non existant
			}
		}
	}
	
	/**
	 * methode qui retourne l'indice de noeud contenant le cle Max à partir d'un indice specifique 
	 * Max = le noeud le plus à droit
	 * @param indiceDebut l'indice à partir de lequel le recherche doit commencer
	 * @return l'indice de noeud contenant le cle Max à partir d'un indice specifique 
	 */
	public int getIndiceMax(int indiceDebut) {
		if(this.isEmpty()) { //verifier si arbre est vide
			return -1; //retourner -1
		} else {
			return Noeud.readNoeudBin(raf, indiceDebut).getIndiceMax(raf); //lire le noeud à l'indiceDebut et faire la recherche sur lui
		}
	}
	
	/**
	 * methode qui retourne l'indice de noeud contenant le cle Min à partir d'un indice specifique 
	 * Min = le noeud le plus à gauche
	 * @param indiceDebut l'indice à partir de lequel le recherche doit commencer
	 * @return l'indice de noeud contenant le cle Min à partir d'un indice specifique 
	 */
	public int getIndiceMin(int indiceDebut) {
		if(this.isEmpty()) { //verifier si arbre est vide
			return -1; //retourner -1
		} else {
			return Noeud.readNoeudBin(raf, indiceDebut).getIndiceMin(raf); //lire le noeud à l'indiceDebut et faire la recherche sur lui
		}
	}

	/**
	 * methode permettant de supprimer un cle de l'arbre
	 * @param cleSupprimer le cle à supprimer
	 */
	public void supprimerNoeud(Stagiaire cleSupprimer) {
		if(this.isEmpty()) { //verifier si arbre est vide
			return; //arret de l'execution
		}
		this.indiceRacine = supprimerNoeud(this.indiceRacine, cleSupprimer); //appeler la methode de supprimer sur le noeud racine et stocker le resultat dans l'indiceRacine
		if(this.indiceRacine == -1) { //si le nouveau indiceRacine = -1, c.a.d on a supprimer tous les noeuds dans l'arbre (aucun noued n'est desormais accessible sur le fichier binaire)
			this.clear(); //appel à la methode clear pour reinitialiser l'arbre et le fichier associé
		}
	}
	
	
	/**
	 * methode permettant de supprimer un cle du sous arbre dont la racine se trouve à l'indiceRacine et maintenir l'ordre dans l'arbre si besoin
	 * @param indiceRacine indice de debut du sous arbre
	 * @param cleSupprimer le cle à supprimer
	 * @return nouveau indiceRacine du sous arbre
	 */
	private int supprimerNoeud(int indiceRacine, Stagiaire cleSupprimer) {
		if(indiceRacine == -1) return indiceRacine; //verifier si l'indice Racine est -1 et le retouner
		Noeud noeudActuel = Noeud.readNoeudBin(raf, indiceRacine); //lire le noeud à l'indiceRacine dans le raf
		if(noeudActuel.getCle().compareTo(cleSupprimer) > 0) { //si cleSupprimer > cle actuel ; on cherche à Droit
			noeudActuel.setIndiceFD(supprimerNoeud(noeudActuel.getIndiceFD(), cleSupprimer)); //appel recursif à partir de fils droit et affecter le resultat à l'indiceFD actuel
			noeudActuel.writeNoeudBin(raf, indiceRacine); //ecrire le noeudActuel (modifié ou pas) 

		} else if(noeudActuel.getCle().compareTo(cleSupprimer) < 0) { //si cleSupprimer > cle actuel ; on cherche à Gauche
			noeudActuel.setIndiceFG(supprimerNoeud(noeudActuel.getIndiceFG(), cleSupprimer)); //appel recursif à partir de fils gauche et affecter le resultat à l'indiceFG actuel
			noeudActuel.writeNoeudBin(raf, indiceRacine); //ecrire le noeudActuel (modifié ou pas) 

		} else { // cle trouvé selon ordre de comparaison
			if( noeudActuel.getIndiceDB() != -1) { // cle a des doublons ?
				if (noeudActuel.getCle().verifierCritere(cleSupprimer)) { // verifier si le doublon est la cle recherche selon tous les criteres/attributs
					// ce doublon est la cle à supprimer
					int indiceDB = noeudActuel.getIndiceDB(); //chercher l'indice de son suivant dans sa liste des doublons 
					Noeud noeudDB = Noeud.readNoeudBin(raf, indiceDB); //chercher son suivant
					noeudActuel.setCle(noeudDB.getCle()); //remplacer la cle a supprimer par celui de son suivant
					noeudActuel.setIndiceDB(supprimerNoeud(noeudActuel.getIndiceDB(), noeudActuel.getCle())); //supprimer la cle de son suivant et remplacer l'indice DB par celui de son suivant
					noeudActuel.writeNoeudBin(raf, indiceRacine); //ecrire le noeud modifie dans le fichier
				} else {
					// ce doublon n'est pas la cle à supprimer
					noeudActuel.setIndiceDB(supprimerNoeud(noeudActuel.getIndiceDB(), cleSupprimer)); //chercher la cle de son suivant et le supprimer si elle la cle à supprimer et retourner l'indice de son suivant
					noeudActuel.writeNoeudBin(raf, indiceRacine);
				}
			}else if(noeudActuel.getIndiceFD() == -1 && noeudActuel.getIndiceFG() == -1) { //la cle n'a pas des doublons, est-qu'il une feuille? 
				indiceRacine = -1; //c'est une feuille, je retourne -1;
			} else if(noeudActuel.getIndiceFD() != -1) { //valeur non feuille, on va chercher la valeur Min de la sous arbre droite
				int indiceMinSAD = getIndiceMin(noeudActuel.getIndiceFD()); // chercher l'indice de Min de la sous arbre droite
				Noeud noeudMinSAD = Noeud.readNoeudBin(raf, indiceMinSAD); // lire le noeud à l'indice Min
				noeudActuel.setCle(noeudMinSAD.getCle()); // remplacer la cle à supprimer par celui de Min
				int newIndiceFD = supprimerNoeud(noeudActuel.getIndiceFD(), noeudActuel.getCle()); //supprimer la cle Min de la sous arbre droite et retourner le nouveau indiceFD
				noeudActuel.setIndiceFD(newIndiceFD); // remplacer l'indiceFD
				noeudActuel.writeNoeudBin(raf,indiceRacine); //ecire le noeud modifié

			} else if(noeudActuel.getIndiceFG() != -1) { //valeur non feuille, on va chercher la valeur Max de la sous arbre gauche
				int indiceMaxSAG = getIndiceMax(noeudActuel.getIndiceFG()); // chercher l'indice de Max de la sous arbre gauche
				Noeud noeudMaxSAG = Noeud.readNoeudBin(raf, indiceMaxSAG); // lire le noeud à l'indice Max
				noeudActuel.setCle(noeudMaxSAG.getCle()); // remplacer la cle à supprimer par celui de Max
				int newIndiceFG = supprimerNoeud(noeudActuel.getIndiceFG(), noeudActuel.getCle()); //supprimer la cle Max de la sous arbre gauche et retourner le nouveau indiceFG
				noeudActuel.setIndiceFG(newIndiceFG); // remplacer l'indiceFG
				noeudActuel.writeNoeudBin(raf, indiceRacine);//ecire le noeud modifié
			} 
		}
		return indiceRacine; //retourner l'indice du noeud actuel
	}

	/**
	 * methode permettant de modifier un cle et maintenir l'ordre dans l'arbre si besoin
	 * @param ancienStg le stagiaire a modifier
	 * @param nvStg le stagiaire modifié
	 */
	public void modifierNoeud(Stagiaire ancienStg, Stagiaire nvStg) {
		if(!this.isEmpty()) { //verifier si arbre vide
			int indiceNoeud = rechercheNoeud(ancienStg); //chercher l'indice auquel le stagiaire modifié se trouve
			if (indiceNoeud != -1) { // si le stagiaire est trouvé (-1 veut dire qu'il n'existe pas)
				if( ancienStg.compareTo(nvStg) == 0) { //verifier si la nvStg differe de l'ancienStg selon l'ordre de l'arbre
					try {
						raf.seek(indiceNoeud*Noeud.getTailleNoeudOctet()); //placer le cursor sur l'indice 
						nvStg.writeStagiaireToBin(raf); //ecrire les modifcations dans le fichier binaire
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else { //pas le meme ordre
					supprimerNoeud(ancienStg); //supprimer l'ancienStg
					ajouterStagiaire(nvStg); //ajouter le nvStg
				}
			}
		}
		
	}

	/**
	 * methode permettant d'importer les Stagiaires d'un fichier et les ajouter dans l'arbre selon l'ordre de l'arbre
	 * @param fichierOrigine un File source de donneées
	 */
	public void importerFile(File fichierOrigine) {
    	try {
    		//inititaliser BufferedReader
			FileReader fr = new FileReader(fichierOrigine);
			BufferedReader br = new BufferedReader(fr);
			
			//initialiser attributs Stagiaire
			ArrayList<String> attributs = new ArrayList<>();
			//lire 1ere ligne (String)
			String s = br.readLine();
			while( s != null) { //tant que la ligne lue n'est pas null
				if(!s.strip().equals("*")) { //verifier si la ligne lue est egale à "*" (séparateur des stagiaires), on supprimer tous les white spaces au debut et à la fin de la ligne lue s'ils existent
					attributs.add(s); //ajouter la ligne lue à l'arrayList
				} else {
					Stagiaire stg = new Stagiaire(attributs.get(0), attributs.get(1), attributs.get(2),attributs.get(3),attributs.get(4)); //creer le stagiaire
					this.ajouterStagiaire(stg); //ajouter le stagiaire
					attributs.clear(); //vider l'ArrayList
				}
				s = br.readLine(); //lire ligne suivate
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * methode permettant de supprimer reinitaliser le fichier binaire à vide
	 */
	public void clear() {
		try {
			raf.close();//fermer le RandomAccessFile
			File f = new File(fileName); //recupere le fichier à partir du path fileName
			f.delete(); //supprimer le fichier
			raf = new RandomAccessFile(new File(fileName), "rw"); //creer un nouveau RandomAccessFile avec le meme fileName
			this.indiceRacine = 0; //reinitialiser indiceRacine à 0
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
