package fr.isika.cda22.projet1.vues;

import fr.isika.cda22.projet1.composantsJFX.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.stage.Stage;

public class vueStagiaire extends Scene {

	private vbTableau vbTableau;
	private ObservableList<String> listCriteres; 
	
	public vueStagiaire() {
	
		super(new VBox(), 700, 800);

		VBox fenetre = (VBox) this.getRoot();
		
		// Fond en couleur de l'arrière plan
		fenetre.setStyle("-fx-background-color:beige");

		// -----------------Début de la Hbox Haut de la page -------------------

		// Création du petit cercle à côté de MyIntern
		Circle cercle = new Circle(5);
		cercle.setFill(Color.GOLD);
		cercle.setStroke(Color.SADDLEBROWN);

		// Création du label MyIntern
		Label myIntern = new Label("My Intern");
		myIntern.setTextFill(Color.SADDLEBROWN);
		myIntern.setFont(Font.font("Brush Script MT", 25));
		myIntern.setAlignment(Pos.TOP_LEFT);

		// Création d'une Hbox pour pouvoir gérer le cercle et myIntern
		HBox cercleMyIntern = new HBox();
		// cercleMyIntern.setPadding(new Insets(10));
		cercleMyIntern.getChildren().addAll(cercle, myIntern);
		cercleMyIntern.setAlignment(Pos.TOP_LEFT);

		// Création bouton seDeconnecter
		ModelButton seDeconnecter = new ModelButton("Se Déconnecter");
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
		VBox Criteres = new VBox();

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
		VBox Boutons = new VBox(5,RechercheFiltre ,Ajouter, Supprimer );

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
				Criteres.getChildren();		
				for 
				(Node hb : Criteres.getChildren()){
				ChoiceBox cb = (ChoiceBox) ((HBox)hb).getChildren().get(0);
				System.out.println(cb.getValue());
				TextField tf = (TextField) ((HBox)hb).getChildren().get(1);
				System.out.println(tf.getText());
				}				
			}
			
		});
		
		
		// VBox
		HBox CritereRechercheMulti = new HBox(5, Criteres, Boutons);

		// Création hbox pour gérer les espacements entre la Hbox de recherche et la
		// choice box

		HBox rechercheCritere = new HBox();
		rechercheCritere.setPadding(new Insets(10));
		rechercheCritere.getChildren().addAll(CritereRechercheMulti);
		rechercheCritere.setAlignment(Pos.TOP_CENTER);
		rechercheCritere.setSpacing(150);

		HBox DispositionRecherche = new HBox();
		DispositionRecherche.setPadding(new Insets(10));
		DispositionRecherche.getChildren().addAll(CritereRechercheMulti, rechercheCritere);
		DispositionRecherche.setAlignment(Pos.TOP_LEFT);
		DispositionRecherche.setSpacing(150);
		DispositionRecherche.setPrefHeight(100);
		// Création vbox pour gérer les espacements entre la Hbox critère/recherche et
		// label liste stagiaires et le tableau

		// Insérer le tableau vueTableau 

		vbTableau  = new vbTableau();
		vbTableau.setMaxHeight(550);
		
		VBox vbRechercheTableau = new VBox();
		//rechercheTableau.setPadding(new Insets(10));
		vbRechercheTableau.getChildren().addAll(listeStagiaires,DispositionRecherche);
		vbRechercheTableau.setAlignment(Pos.TOP_CENTER);
		vbRechercheTableau.setSpacing(10);

		// -----------------Fin de la VBox Liste stagiaire,recherche et tableau middle
		// top de la page -------------------

		// -----------------Début de la Hbox Importer et telecharger-------------------
		// Bouton importer
		ModelButton btnImporter = new ModelButton("Importer");
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
		ModelButtonStagiaire ajouter = new ModelButtonStagiaire("Ajouter un\n  Stagiaire");
		ajouter.setAlignment(Pos.BASELINE_CENTER);

		// Création bouton modifier
		ModelButtonStagiaire modifier = new ModelButtonStagiaire("Modifier un\n   Stagiaire");
		modifier.setAlignment(Pos.BASELINE_CENTER);

		// Création boutton supprimer
		ModelButtonStagiaire supprimer = new ModelButtonStagiaire("Supprimer un\n    Stagiaire");
		supprimer.setAlignment(Pos.BASELINE_CENTER);

		// Hbox pour gerer les trois boutons
		HBox hbAjouterModifierSupprimer = new HBox(ajouter, modifier, supprimer);
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
		critere.setItems(this.listCriteres);
		critere.getSelectionModel().select(Integer);
		TextField TF = new TextField();
		HB.getChildren().addAll(critere, TF);
		return HB;
	}
	
	public void afficherRecherche(String Filtre) {
		

	}
	}