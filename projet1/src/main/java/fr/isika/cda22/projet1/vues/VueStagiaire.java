package fr.isika.cda22.projet1.vues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
	private ModelButton btnTelecharger;
	private VBox vbCriteres;
	private boolean isAdmin;
	
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
	
	public ModelButton getBtnImporter() {
		return btnImporter;
	}
	
	public ModelButton getBtnTelecharger() {
		return btnTelecharger;
	}
	
	public void setSeDeconnecter(ModelButton seDeconnecter) {
		this.seDeconnecter = seDeconnecter;
	}

	public void setBtnSupprimerStagiaire(ModelButton btnSupprimerStagiaire) {
		this.btnSupprimerStagiaire = btnSupprimerStagiaire;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public VueStagiaire() {
	
		super(new VBox(), 700, 800);

		VBox fenetre = (VBox) this.getRoot();
		
		// Fond en couleur de l'arrière plan
		fenetre.setStyle("-fx-background-color:beige");

		// -----------------Début de la Hbox Haut de la page -------------------
		isAdmin = true;
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
		Label lbListeStagiaires = new Label("Liste des stagiaires");
		lbListeStagiaires.setTextFill(Color.SADDLEBROWN);
		lbListeStagiaires.setFont(Font.font("Brush Script MT", 25));
		lbListeStagiaires.setAlignment(Pos.TOP_CENTER);

		// Vbox recherche et filtre 
		vbCriteres = new VBox(2);

		vbCriteres.getChildren().add(creerHbCritere(0));

		// Hbox avec les boutons
		ModelButton btnAjouterCritere = new ModelButton("Ajouter");
		
		ModelButton btnSupprimerCritere = new ModelButton("Supprimer");
		btnSupprimerCritere.setDisable(true);
		
		ModelButton btnRechercher = new ModelButton("Rechercher");
		ModelButton btnResetTableau = new ModelButton("Retablir Tableau");
		
		VBox vbBtnRecherche = new VBox(2,btnRechercher ,btnAjouterCritere, btnSupprimerCritere, btnResetTableau );

		// méthode des bouton ajouter, supprimer rechercher, et reset
		btnAjouterCritere.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int Integer = vbCriteres.getChildren().size();

				vbCriteres.getChildren().add(creerHbCritere(Integer));

				Integer = vbCriteres.getChildren().size();

				if (Integer == 5) {
					btnAjouterCritere.setDisable(true);
				}

				if (Integer > 1) {
					btnSupprimerCritere.setDisable(false);
				}
			}
		});
		
		btnSupprimerCritere.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int Integer = vbCriteres.getChildren().size();
				vbCriteres.getChildren().remove(Integer - 1);
				Integer = vbCriteres.getChildren().size();
				if (Integer == 1) {
					btnSupprimerCritere.setDisable(true);
					;
				}
				if (Integer < 5) {
					btnAjouterCritere.setDisable(false);
				}
			}
		});
		
		btnRechercher.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {	
				Map<String,String> monMap = new HashMap<>();
				for(Node hb : vbCriteres.getChildren()){
				ChoiceBox cb = (ChoiceBox) ((HBox)hb).getChildren().get(0);
				TextField tf = (TextField) ((HBox)hb).getChildren().get(1);
				monMap.put(cb.getValue().toString(), tf.getText());
				}				
				ArrayList<Stagiaire> stgs = monArbre.rechercheCritere(monMap);
				vbTableau.setListeStagiaire(stgs);
			}	
		});
		
		btnResetTableau.setOnAction(event ->{
			refreshTable();
		});
		
		VBox vbImporterTelecharger = new VBox(5);
		vbImporterTelecharger.setPadding(new Insets(10));
		vbImporterTelecharger.setAlignment(Pos.CENTER);
		vbImporterTelecharger.setPrefWidth(240);
		
		// HBox contenant les btns et vbCriteres
		HBox hbDispositionRecherche = new HBox(5, vbCriteres, vbBtnRecherche, vbImporterTelecharger);
		hbDispositionRecherche.setPadding(new Insets(10));

		// Insérer le tableau vueTableau 
		monArbre = new Arbre("src/main/java/fr/isika/cda22/projet1/fichiers/arbre2.bin");
		
		vbTableau  = new vbTableau();
		vbTableau.setMaxHeight(550);
		vbTableau.setPadding(new Insets(10));
		refreshTable();
		
		vbTableau.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	btnSupprimerStagiaire.setDisable(!isAdmin);
		    	btnModifierStagiaire.setDisable(!isAdmin);
		    }
		});
		
		VBox vbRechercheTableau = new VBox();
		vbRechercheTableau.getChildren().addAll(lbListeStagiaires, hbDispositionRecherche);
		vbRechercheTableau.setAlignment(Pos.TOP_CENTER);
		vbRechercheTableau.setSpacing(10);

		// -----------------Fin de la VBox Liste stagiaire,recherche et tableau middle
		// top de la page -------------------

		// -----------------Début de la Hbox Importer et telecharger-------------------
		// Bouton importer
		btnImporter = new ModelButton("Importer");
		btnImporter.setAlignment(Pos.BOTTOM_CENTER);	

		// bouton telecharger
		btnTelecharger = new ModelButton("Télécharger");
		btnTelecharger.setAlignment(Pos.BOTTOM_CENTER);
		
		// Hbox pour gerer les hbox telecharger et importer
//		HBox hbImprimerImporter = new HBox(btnImporter, btnTelecharger);
//		hbImprimerImporter.setAlignment(Pos.CENTER);
//		hbImprimerImporter.setSpacing(160);
//		hbImprimerImporter.setMinHeight(50);
		vbImporterTelecharger.getChildren().addAll(btnImporter, btnTelecharger);
		// -----------------Fin de la Hbox Importer et telecharger-------------------

		// -----------------Début de la Hbox Ajouter, Modifier,Supprimer-------------------

		// Création bouton ajouter
		btnAjouterStagiaire = new ModelButton("Ajouter un\n  Stagiaire");
		btnAjouterStagiaire.setPrefHeight(50);
//		btnAjouterStagiaire.setAlignment(Pos.BASELINE_CENTER);

		// Création bouton modifier
		btnModifierStagiaire = new ModelButton("Modifier un\n   Stagiaire");
		btnModifierStagiaire.setPrefHeight(50);
		btnModifierStagiaire.setDisable(true);
//		btnModifierStagiaire.setAlignment(Pos.BASELINE_CENTER);

		// Création boutton supprimer
		btnSupprimerStagiaire = new ModelButton("Supprimer un\n    Stagiaire");
		btnSupprimerStagiaire.setPrefHeight(50);
		btnSupprimerStagiaire.setDisable(true);
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

//		fenetre.getChildren().addAll(hautPage, vbRechercheTableau, vbTableau, hbImprimerImporter, hbAjouterModifierSupprimer);
		fenetre.getChildren().addAll(hautPage, vbRechercheTableau, vbTableau, hbAjouterModifierSupprimer);
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
	
	public void importer(File f) {
		monArbre.importerFile(f);
		refreshTable();
	}

	public void telecharger(String string) {
		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
					string + "/imprimer.pdf"));
			document.open();
			PdfPTable table = new PdfPTable(5);
			table.addCell("Nom");
	    	table.addCell("Prenom");
	    	table.addCell("Localisation");
	    	table.addCell("Nom de la Formation");
	    	table.addCell("Annee Promo");
	    	for(Stagiaire s: vbTableau.getTable().getItems()) {
	    		table.addCell(s.getNom());
	    		table.addCell(s.getPrenom());
	    		table.addCell(s.getLocalisation());
	    		table.addCell(s.getNomFormation());
	    		table.addCell(s.getAnneePromo());
	    	}
	    	document.add(table);
	    	document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}