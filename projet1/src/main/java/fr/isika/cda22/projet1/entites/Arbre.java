package fr.isika.cda22.projet1.entites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Map;

public class Arbre {
	
	//attributs
	private RandomAccessFile raf;
	private int indiceRacine;
	private String fileName; 
	
	//constructeur
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
	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}
	
	public int getIndiceRacine() {
		return indiceRacine;
	}

	//methodes specifiques
	public boolean isEmpty() {
		try {
			return (this.raf.length() == 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "Arbre est vide";
		}else {
			return Noeud.readNoeudBin(raf, this.indiceRacine).toString(raf);
		}
	}
	
	public void ajouterStagiaire(Stagiaire stagiaire){
		if(this.isEmpty()) {
			Noeud.writeNoeudBin(stagiaire, raf);
		}else {
			Noeud n0 = Noeud.readNoeudBin(raf, this.indiceRacine);
			n0.ajouterNoeud(stagiaire, raf);
		}
	}
	
	public ArrayList<Stagiaire> toArray() {
		ArrayList<Stagiaire> stgArray = new ArrayList<>();
		if(!this.isEmpty()) {
			Noeud.readNoeudBin(raf, this.indiceRacine).toArray(stgArray, raf);
		}
		return stgArray;
	}
	
	public ArrayList<Stagiaire> rechercheCritere(Map<String, String> mapCriteres){
		if(this.isEmpty()) {
			return null;
		} else {
			ArrayList<Stagiaire> stgArray = new ArrayList<>();
			Noeud.readNoeudBin(raf, this.indiceRacine).rechercheCritere(raf, stgArray, new Stagiaire(mapCriteres));
			return stgArray;
		}
	}
	
	public int rechercheNoeud(Stagiaire cleRecherche){
		if(this.isEmpty()) {
			return -1;
		} else {
			try {
				return Noeud.readNoeudBin(raf, this.indiceRacine).rechercheNoeud(raf, cleRecherche);
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		}
	}
	
	public int getIndiceMax(int indiceDebut) {
		if(this.isEmpty()) {
			return -1;
		} else {
			return Noeud.readNoeudBin(raf, indiceDebut).getIndiceMax(raf);
		}
	}
	
	public int getIndiceMin(int indiceDebut) {
		if(this.isEmpty()) {
			return -1;
		} else {
			return Noeud.readNoeudBin(raf, indiceDebut).getIndiceMin(raf);
		}
	}

	public void supprimerNoeud(Stagiaire cleSupprimer) {
		if(this.isEmpty()) {
			return;
		}
		this.indiceRacine = supprimerNoeud(this.indiceRacine, cleSupprimer);
		if(this.indiceRacine == -1) {
			try {
			File f = new File(fileName);
			f.delete();
				raf = new RandomAccessFile(f, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private int supprimerNoeud(int indiceRacine, Stagiaire cleSupprimer) {
		if(indiceRacine == -1) return indiceRacine;
		Noeud noeudActuel = Noeud.readNoeudBin(raf, indiceRacine);
		if(noeudActuel.getCle().compareTo(cleSupprimer) > 0) { //si > ; on cherche à Droit
			noeudActuel.setIndiceFD(supprimerNoeud(noeudActuel.getIndiceFD(), cleSupprimer));
			noeudActuel.writeNoeudBin(raf, indiceRacine);

		} else if(noeudActuel.getCle().compareTo(cleSupprimer) < 0) { //si < ; on cherche à Gauche 
			noeudActuel.setIndiceFG(supprimerNoeud(noeudActuel.getIndiceFG(), cleSupprimer));
			noeudActuel.writeNoeudBin(raf, indiceRacine);

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

	public void modifierNoeud(Stagiaire ancienStg, Stagiaire nvStg) {
		if(!this.isEmpty()) {
			int indiceNoeud = rechercheNoeud(ancienStg);
			if (indiceNoeud != -1) {
				if( ancienStg.compareTo(nvStg) == 0) {
					try {
						raf.seek(indiceNoeud*Noeud.getTailleNoeudOctet());
						nvStg.writeStagiaireToBin(raf);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					supprimerNoeud(ancienStg);
					ajouterStagiaire(nvStg);
				}
			}
		}
		
	}
	
	public void importerFile(File fichierOrigine) {
    	try {
    		//inititaliser BufferedReader
			FileReader fr = new FileReader(fichierOrigine);
			BufferedReader br = new BufferedReader(fr);
			
			//initialiser attributs Stagiaire
			ArrayList<String> attributs = new ArrayList<>();
			//lire 1ere ligne (String)
			String s = br.readLine();
			while( s != null) { //tant que la ligne lue n'est pas vide ou pas existant
				if(!s.strip().equals("*")) {
					attributs.add(s);
				} else {
					Stagiaire stg = new Stagiaire(attributs.get(0), attributs.get(1), attributs.get(2),attributs.get(3),attributs.get(4));
					this.ajouterStagiaire(stg);
					attributs.clear();
				}
				s = br.readLine(); //lire ligne suivate
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
}
