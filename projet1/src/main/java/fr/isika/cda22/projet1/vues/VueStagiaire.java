package fr.isika.cda22.projet1.vues;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import fr.isika.cda22.projet1.composantsJFX.*;
import fr.isika.cda22.projet1.entites.Arbre;
import fr.isika.cda22.projet1.entites.Stagiaire;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class VueStagiaire extends Scene {

	private static final String[] LIST_CRITERES = {"Nom", "Prenom", "Localisation", 
			"Nom de la Formation", "Annee Promo" };
	
	private vbTableau vbTableau;
	private ArrayList<String> listCriteres;
	private Arbre monArbre;
	private ModelButton btnAjouterStagiaire;
	private ModelButton btnModifierStagiaire;
	private ModelButton btnSupprimerStagiaire;
	private ModelButton seDeconnecter;
	private ModelButton btnImporter;
	private VBox Criteres;
	
	public Arbre getMonArbre() {
		return monArbre;
	}

	public void setMonArbre(Arbre monArbre) {
		this.monArbre = monArbre;
	}

	public vbTableau getVbTableau() {
		return vbTableau;
	}

	public void setVbTableau(vbTableau vbTableau) {
		this.vbTableau = vbTableau;
	}

	public ArrayList<String> getListCriteres() {
		return listCriteres;
	}

	public void setListCriteres(ArrayList<String> listCriteres) {
		this.listCriteres = listCriteres;
	}

	public ModelButton getBtnAjouterStagiaire() {
		return btnAjouterStagiaire;
	}

	public void setBtnAjouterStagiaire(ModelButton btnAjouterStagiaire) {
		this.btnAjouterStagiaire = btnAjouterStagiaire;
	}

	public ModelButton getBtnModifierStagiaire() {
		return btnModifierStagiaire;
	}

	public void setBtnModifierStagiaire(ModelButton btnModifierStagiaire) {
		this.btnModifierStagiaire = btnModifierStagiaire;
	}

	public ModelButton getBtnSupprimerStagiaire() {
		return btnSupprimerStagiaire;
	}

	public ModelButton getSeDeconnecter() {
		return seDeconnecter;
	}
	
	public ModelButton geBtntImporter() {
		return btnImporter;
	}

	public void setSeDeconnecter(ModelButton seDeconnecter) {
		this.seDeconnecter = seDeconnecter;
	}

	public void setBtnSupprimerStagiaire(ModelButton btnSupprimerStagiaire) {
		this.btnSupprimerStagiaire = btnSupprimerStagiaire;
	}

	public VueStagiaire() {
	
		super(new VBox(), 700, 800);

		VBox fenetre = (VBox) this.getRoot();
		
		// Fond en couleur de l'arrière plan
		fenetre.setStyle("-fx-background-color:beige");

		// -----------------Début de la Hbox Haut de la page -------------------

		// Création du logo
		HbLogo cercleMyIntern = new HbLogo();
		
		// Création bouton seDeconnecter
		seDeconnecter = new ModelButton("Se Déconnecter");
		seDeconnecter.setAlignment(Pos.TOP_CENTER);

		// Création de HBox pour tous les éléments en Top de la page Liste stagiaire et pouvoir les aligner comme on le souhaite
		HBox hautPage = new HBox();
		hautPage.setPadding(new Insets(10));
		hautPage.getChildren().addAll(cercleMyIntern, seDeconnecter);
		hautPage.setAlignment(Pos.TOP_CENTER);
		hautPage.setSpacing(450);
		hautPage.setPrefHeight(50);

		// -----------------Fin de la Hbox Haut de la page -------------------

		// -----------------Début de la VBox Liste stagiaire,recherche et tableau middle top de la page -------------------

		// Vbox avec Texte liste stagiaire, une hbox dans laquel on a une choiceBox un Textfield et un bouton Recherche, et la tableview

		// Création label Liste stagiare qui doit être situer au milieu
		Label listeStagiaires = new Label("Liste des stagiaires");
		listeStagiaires.setTextFill(Color.SADDLEBROWN);
		listeStagiaires.setFont(Font.font("Brush Script MT", 25));
		listeStagiaires.setAlignment(Pos.TOP_CENTER);

		// Vbox recherche et filtre 
		Criteres = new VBox(2);

		Criteres.getChildren().add(creerHbCritere(0));

		// Hbox avec deux boutons
		ModelButton Ajouter = new ModelButton("Ajouter");
		Ajouter.setAlignment(Pos.BOTTOM_CENTER);
		//Ajouter.setTextFill(Color.SADDLEBROWN);
		ModelButton Supprimer = new ModelButton("Supprimer");
		Supprimer.setAlignment(Pos.BOTTOM_CENTER);
		//Supprimer.setTextFill(Color.SADDLEBROWN);
		ModelButton RechercheFiltre = new ModelButton("Rechercher");
		RechercheFiltre.setAlignment(Pos.BOTTOM_CENTER);
		//RechercheFiltre.setTextFill(Color.SADDLEBROWN);
		ModelButton btnReset = new ModelButton("Retablir Tableau");
		btnReset.setAlignment(Pos.BOTTOM_CENTER);
		
		VBox Boutons = new VBox(2,RechercheFiltre ,Ajouter, Supprimer, btnReset );

		// méthode des bouton ajouter et supprimer
		Ajouter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int Integer = Criteres.getChildren().size();

				Criteres.getChildren().add(creerHbCritere(Integer));

				Integer = Criteres.getChildren().size();

				if (Integer == 5) {
					Ajouter.setDisable(true);
				}

				if (Integer > 1) {
					Supprimer.setDisable(false);
				}
			}
		});
		
		Supprimer.setDisable(true);
		Supprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int Integer = Criteres.getChildren().size();
				Criteres.getChildren().remove(Integer - 1);
				Integer = Criteres.getChildren().size();
				if (Integer == 1) {
					Supprimer.setDisable(true);
					;
				}
				if (Integer < 5) {
					Ajouter.setDisable(false);
				}
			}
		});
		
		RechercheFiltre.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {	
				Map<String,String> monMap = new HashMap<>();
				for(Node hb : Criteres.getChildren()){
				ChoiceBox cb = (ChoiceBox) ((HBox)hb).getChildren().get(0);
				TextField tf = (TextField) ((HBox)hb).getChildren().get(1);
				monMap.put(cb.getValue().toString(), tf.getText());
				}				
//				Stagiaire stgRecherche = new Stagiaire(monMap);
				ArrayList<Stagiaire> stgs = monArbre.rechercheCritere(monMap);
//				System.out.println(monArbre);
//				System.out.println(stgs.size());
//				for(Stagiaire s: stgs) {
//					System.out.println(s);
//				}
//				refreshTable();
				vbTableau.setListeStagiaire(stgs);
			}	
		});
		
		btnReset.setOnAction(event ->{
			refreshTable();
		});
		
		// VBox
		HBox CritereRechercheMulti = new HBox(5, Criteres, Boutons);

		// Création hbox pour gérer les espacements entre la Hbox de recherche et la
		// choice box

//		HBox rechercheCritere = new HBox();
//		rechercheCritere.setPadding(new Insets(10));
//		rechercheCritere.getChildren().addAll(CritereRechercheMulti);
//		rechercheCritere.setAlignment(Pos.TOP_CENTER);
//		rechercheCritere.setSpacing(150);

		HBox DispositionRecherche = new HBox();
		DispositionRecherche.setPadding(new Insets(10));
//		DispositionRecherche.getChildren().addAll(CritereRechercheMulti, rechercheCritere);
		DispositionRecherche.getChildren().addAll(CritereRechercheMulti);
		DispositionRecherche.setAlignment(Pos.TOP_LEFT);
		DispositionRecherche.setSpacing(150);
		DispositionRecherche.setPrefHeight(100);
		// Création vbox pour gérer les espacements entre la Hbox critère/recherche et
		// label liste stagiaires et le tableau

		// Insérer le tableau vueTableau 
		monArbre = new Arbre("src/main/java/fr/isika/cda22/projet1/fichiers/arbre2.bin");
		
		vbTableau  = new vbTableau();
		vbTableau.setMaxHeight(550);
		refreshTable();

		VBox vbRechercheTableau = new VBox();
		vbRechercheTableau.getChildren().addAll(listeStagiaires,DispositionRecherche);
		vbRechercheTableau.setAlignment(Pos.TOP_CENTER);
		vbRechercheTableau.setSpacing(10);

		// -----------------Fin de la VBox Liste stagiaire,recherche et tableau middle
		// top de la page -------------------

		// -----------------Début de la Hbox Importer et telecharger-------------------
		// Bouton importer
		btnImporter = new ModelButton("Importer");
		btnImporter.setAlignment(Pos.BOTTOM_CENTER);	

		// bouton telecharger
		ModelButton btnTelecharger = new ModelButton("Télécharger");
		btnTelecharger.setAlignment(Pos.BOTTOM_CENTER);
	
		// Hbox pour gerer les hbox telecharger et importer
		HBox hbImprimerImporter = new HBox(btnImporter, btnTelecharger);
		hbImprimerImporter.setAlignment(Pos.CENTER);
		hbImprimerImporter.setSpacing(160);
		hbImprimerImporter.setMinHeight(50);
		
		// -----------------Fin de la Hbox Importer et telecharger-------------------

		// -----------------Début de la Hbox Ajouter, Modifier,Supprimer-------------------

		// Création bouton ajouter
		btnAjouterStagiaire = new ModelButton("Ajouter un\n  Stagiaire");
		btnAjouterStagiaire.setPrefHeight(50);
//		btnAjouterStagiaire.setAlignment(Pos.BASELINE_CENTER);

		// Création bouton modifier
		btnModifierStagiaire = new ModelButton("Modifier un\n   Stagiaire");
		btnModifierStagiaire.setPrefHeight(50);
//		btnModifierStagiaire.setAlignment(Pos.BASELINE_CENTER);

		// Création boutton supprimer
		btnSupprimerStagiaire = new ModelButton("Supprimer un\n    Stagiaire");
		btnSupprimerStagiaire.setPrefHeight(50);
//		btnSupprimerStagiaire.setAlignment(Pos.BASELINE_CENTER);
		
		btnSupprimerStagiaire.setOnAction(event ->{
			Stagiaire ancienStagiaire = vbTableau.getTable().getSelectionModel().getSelectedItem();
			this.supprimerStagiaire(ancienStagiaire);
		});

		// Hbox pour gerer les trois boutons
		HBox hbAjouterModifierSupprimer = new HBox(btnAjouterStagiaire, btnModifierStagiaire, btnSupprimerStagiaire);
		hbAjouterModifierSupprimer.setAlignment(Pos.BASELINE_CENTER);
		hbAjouterModifierSupprimer.setSpacing(150);
		hbAjouterModifierSupprimer.setMinHeight(50);

		// -----------------Fin de la Hbox Ajouter, Modifier, Supprimer-------------------

		// -----------------Début de la Vbox qui affiche toute la page -------------------

		fenetre.getChildren().addAll(hautPage, vbRechercheTableau, vbTableau, hbImprimerImporter, hbAjouterModifierSupprimer);
		fenetre.setSpacing(5);


		// -----------------Fin de la Vbox qui affiche toute la page -------------------

	}

	public HBox creerHbCritere(int Integer) {
		HBox HB = new HBox(5);
		ChoiceBox critere = new ChoiceBox();
		critere.setPrefWidth(150);
		ObservableList<String> listCriteres = FXCollections.observableArrayList(LIST_CRITERES);
		critere.setItems(listCriteres);		
		critere.getSelectionModel().select(Integer);
		TextField TF = new TextField();
		HB.getChildren().addAll(critere, TF);
		return HB;
	}
	
	public void refreshTable() {
		vbTableau.setListeStagiaire(monArbre.toArray());
	}
	
	public void ajouterStagiaire(Stagiaire cleAjouter) {
		monArbre.ajouterStagiaire(cleAjouter);
		refreshTable();
	}
	
	public void modifierStagiaire(Stagiaire ancienStagiaire, Stagiaire cleModifie) {
		monArbre.modifierNoeud(ancienStagiaire, cleModifie);
		refreshTable();
	}

	public void supprimerStagiaire(Stagiaire cleSupprimer) {
		monArbre.supprimerNoeud(cleSupprimer);
		refreshTable();
	}
	
}