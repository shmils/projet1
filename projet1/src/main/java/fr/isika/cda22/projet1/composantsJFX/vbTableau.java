package fr.isika.cda22.projet1.composantsJFX;

import java.util.ArrayList;

import fr.isika.cda22.projet1.entites.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * classe representant un VBox contenant un Tableau
 * @author shmilany
 *
 */
public class vbTableau extends VBox {
	
	private ArrayList<Stagiaire> listeStagiaire;
	private TableView<Stagiaire> table;
	
	/**
	 * methode permettant d'acceder au tableau
	 * @return un TableView
	 */
	public TableView<Stagiaire> getTable() {
		return table;
	}

	/**
	 * methode permettant de modifier tableau
	 * @param table
	 */
	public void setTable(TableView<Stagiaire> table) {
		this.table = table;
	}

	/**
	 * methode permettant d'acceder à la liste des stagiaire
	 * @return arrayList
	 */
	public ArrayList<Stagiaire> getListeStagiaire() {
		return listeStagiaire;
	}

	/**
	 * methode permettant de modifier liste tableau et actualiser le continue de tableau
	 * @param listeStagiaire
	 */
	public void setListeStagiaire(ArrayList<Stagiaire> listeStagiaire) {
		this.listeStagiaire = listeStagiaire;
		if(this.listeStagiaire != null){ //verifier si arrayList est null
			table.setItems(FXCollections.observableArrayList(this.listeStagiaire)); //actualiser le continue de tableau
		}
	}

	/**
	 * constructeur permettant d'initialiser le vbTalbeau
	 */
	public  vbTableau() {
		super(5);
		// Création de la table
		table = new TableView<Stagiaire>();
		table.setEditable(true);
		
		listeStagiaire = new ArrayList();
		// Création des cinq colonnes

		// Nom
		TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
		nomCol.setMinWidth(120);
		// Spécifier comment remplir la donnée pour chaque cellule de cette colonne
		nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		
		// Prénom
		TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
		prenomCol.setMinWidth(120);

		prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		// Localisation
		TableColumn<Stagiaire, String> localisationCol = new TableColumn<Stagiaire, String>("Localisation");
		localisationCol.setMinWidth(120);

		localisationCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("localisation"));

		// Nom de la Formation
		TableColumn<Stagiaire, String> nomPromoCol = new TableColumn<Stagiaire, String>("Nom de la \nPromotion");
		nomPromoCol.setMinWidth(120);
		nomPromoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nomFormation"));
		
		// Année de la promotion
		TableColumn<Stagiaire, String> anneePromoCol = new TableColumn<Stagiaire, String>("Année de la \nPromotion");
		anneePromoCol.setMinWidth(120);
		anneePromoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("anneePromo"));

		// On ajoute les cinq colonnes à la table
		table.getColumns().addAll(nomCol, prenomCol, localisationCol, nomPromoCol, anneePromoCol);

//		table.setItems(getStagiaireList());
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.getChildren().add(table);
	}

	/**
	 * methode retournant remplir l'initaliser un liste de teste
	 * @return ObservableList<Stagiaire>
	 */
	private ObservableList<Stagiaire> getStagiaireList() {
		Stagiaire Stagiaire1 = new Stagiaire("Bleriot", "Louis", "94", "CDA22", "2022");
		Stagiaire Stagiaire2 = new Stagiaire("Jeandes", "Bernard","85", "CDA41", "2012");
		Stagiaire Stagiaire3 = new Stagiaire("Goodesa", "Marie", "41", "CDA98", "2006");
		listeStagiaire.add(Stagiaire1);
		listeStagiaire.add(Stagiaire2);
		listeStagiaire.add(Stagiaire3);
		ObservableList<Stagiaire> list = FXCollections.observableArrayList(listeStagiaire);
		return list;
	}
	
	/**
	 * methode permettant d'ajouter un stagiaire au tableau et actualise le contenu du tableau
	 * @param cleAjouter
	 */
	public void ajouterStagiaire(Stagiaire cleAjouter){
		listeStagiaire.add(cleAjouter);
		ObservableList<Stagiaire> list = FXCollections.observableArrayList(listeStagiaire);
		table.setItems(list);
	}

}