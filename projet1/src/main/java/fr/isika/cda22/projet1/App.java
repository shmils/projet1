package fr.isika.cda22.projet1;

import java.io.File;
import java.util.ArrayList;

import fr.isika.cda22.projet1.composantsJFX.vbTableau;
import fr.isika.cda22.projet1.entites.Stagiaire;
import fr.isika.cda22.projet1.vues.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * JavaFX App
 */
public class App extends Application {
	
	public VueStagiaire vueListeStagiaire;
	public VueEnregistrement vueEnregistrement;
	public VueModification vueModification;
	public VueLogin vueLogin;

    @Override
    public void start(Stage stage) {
    	vueListeStagiaire = new VueStagiaire();
    	vueEnregistrement = new VueEnregistrement();
//    	vueModification = new VueModification("milany", "hossein", "45", "cda al 22", "2022");
    	vueModification = new VueModification();
    	vueLogin = new VueLogin();
    	
    	vueLogin.getBtnLogin().setOnAction(event ->{
    		boolean isAdmin = vueLogin.getCbAdmin().isSelected();
    		if(vueLogin.authentifier(vueLogin.getTfNom().getText(), 
    				vueLogin.getMotdePasse(), isAdmin)) {
    			vueListeStagiaire.getBtnModifierStagiaire().setDisable(!isAdmin);
    			vueListeStagiaire.getBtnSupprimerStagiaire().setDisable(!isAdmin);
    			stage.setScene(vueListeStagiaire);
        		stage.setTitle("My Intern - Liste");
    		} else {
    			vueLogin.getMsgErreur().setVisible(true);
    		}
		});
    	
    	vueListeStagiaire.getBtnModifierStagiaire().setOnAction(event ->{
    		//on devra creer le stagiare à modifier
//    		Stagiaire ancienStagiaire = vueListeStagiaire.getVbTableau().getTable().getSelectionModel().getSelectedItem();
    		vueModification.setAncienStagiaire(vueListeStagiaire.getVbTableau().getTable().getSelectionModel().getSelectedItem());
    		vueModification.remplirFields();
    		stage.setScene(vueModification);
    		stage.setTitle("My Intern - Modifier");
    	});
    	
    	vueListeStagiaire.getSeDeconnecter().setOnAction(event ->{
    		vueLogin.getTfNom().clear();
    		vueLogin.getPfMotdePass().clear();
    		vueLogin.getTfMotdePass().clear();
    		vueLogin.getMsgErreur().setVisible(false);
    		stage.setScene(vueLogin);
    		stage.setTitle("My Intern - Se Connecter");
    	});
    	
    	vueListeStagiaire.getBtnAjouterStagiaire().setOnAction(event ->{
    		stage.setScene(vueEnregistrement);
    		stage.setTitle("My Intern - Ajouter");
    	});
    	
    	vueListeStagiaire.geBtntImporter().setOnAction(event->{
    			FileChooser fileChooser = new FileChooser();
    	    	fileChooser.setTitle("Open Resource File");
    	    	fileChooser.getExtensionFilters().add(new ExtensionFilter(".don files", "*.don"));;  	
    	    	File f = fileChooser.showOpenDialog(stage);
//    	    	String nomFichier = fileChooser.g
//    	    	System.out.println(f.getName());
//    			File f = new File("src/main/java/fr/isika/cda22/projet1/fichiers/STAGIAIRES_complet.DON");
    			vueListeStagiaire.getMonArbre().importerFile(f);
    			vueListeStagiaire.refreshTable();
    	});
    	
    	vueEnregistrement.getBtnConfirmation().setOnAction(event ->{
    		//on devra recuperer les textFields
    		ArrayList<String> attributs = vueEnregistrement.getTextFields();
    		//creer un Stagiaire
    		Stagiaire cleAjouter = new Stagiaire(attributs.get(0),attributs.get(1),attributs.get(2),attributs.get(3),attributs.get(4));
    		//ajouter le Stagiaire sur le tableau
    		vueListeStagiaire.ajouterStagiaire(cleAjouter);
    		retourEnListe(stage);
    	});
    	
    	vueEnregistrement.getButtonRetour().setOnAction(event ->{
    		retourEnListe(stage);
    	});
    	vueModification.getBtnConfirmation().setOnAction(event ->{
    		//on devra recuperer les textFields
    		ArrayList<String> attributs = vueModification.getTextFields();
    		//creer un Stagiaire
    		Stagiaire cleModifier = new Stagiaire(attributs.get(0),attributs.get(1),attributs.get(2),attributs.get(3),attributs.get(4));
    		//ajouter le Stagiaire sur le tableau
//    		vueListeStagiaire.getVbTableau().ajouterStagiaire(cleModifier);
//    		vueListeStagiaire.getVbTableau().getListeStagiaire().remove(vueModification.getAncienStagiaire());
//    		vueListeStagiaire.getVbTableau().getListeStagiaire().add(cleModifier);
//    		vueListeStagiaire.getVbTableau().setListeStagiaire(vueListeStagiaire.getVbTableau().getListeStagiaire());
//    		monAbre.modifierStagiaire(vueModification.getAncienStagiaire(),cleModifier);
    		vueListeStagiaire.modifierStagiaire(vueModification.getAncienStagiaire(), cleModifier);
    		retourEnListe(stage);
    	});
    	vueModification.getButtonRetour().setOnAction(event ->{
    		retourEnListe(stage);
    	});
	
    	
		stage.setResizable(false);
		stage.setTitle("My Inter - Se Connecter");
		stage.setScene(vueListeStagiaire);
		stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public void retourEnListe(Stage stage) {
    	stage.setScene(vueListeStagiaire);
		stage.setTitle("My Intern - Liste");
    }

}