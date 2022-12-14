package fr.isika.cda22.projet1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.isika.cda22.projet1.composantsJFX.vbTableau;
import fr.isika.cda22.projet1.entites.Stagiaire;
import fr.isika.cda22.projet1.vues.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 *class representant le lanceur de l'application
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
    	vueModification = new VueModification();
    	vueLogin = new VueLogin();
    	
    	//Handle Action Button vueLogin
    	vueLogin.getBtnLogin().setOnAction(event ->{
    		boolean isAdmin = vueLogin.getCbAdmin().isSelected();
    		if(vueLogin.authentifier(vueLogin.getTfNom().getText(), 
    				vueLogin.getMotdePasse(), isAdmin)) {
    			vueListeStagiaire.reInit();
    			vueListeStagiaire.setIsAdmin(isAdmin);
    			stage.setScene(vueListeStagiaire);
        		stage.setTitle("My Intern - Liste");
    		} else {
    			vueLogin.getMsgErreur().setVisible(true);
    		}
		});
    	
    	//Handle Action Button vueListeStagiaire
    	vueListeStagiaire.getBtnModifierStagiaire().setOnAction(event ->{
    		//on devra creer le stagiare à modifier
    		vueModification.setAncienStagiaire(vueListeStagiaire.getVbTableau().getTable().getSelectionModel().getSelectedItem());
    		//on remplir les different fields
    		vueModification.remplirFields();
    		stage.setScene(vueModification);
    		stage.setTitle("My Intern - Modifier");
    	});
    	
    	vueListeStagiaire.getSeDeconnecter().setOnAction(event ->{
    		vueLogin.reInit();
    		stage.setScene(vueLogin);
    		stage.setTitle("My Intern - Se Connecter");
    	});
    	
    	vueListeStagiaire.getBtnAjouterStagiaire().setOnAction(event ->{
    		vueEnregistrement.initTextFields();
    		stage.setScene(vueEnregistrement);
    		stage.setTitle("My Intern - Ajouter");
    	});
    	
    	vueListeStagiaire.getBtnImporter().setOnAction(event->{
			FileChooser fileChooser = new FileChooser();
	    	fileChooser.setTitle("Open Resource File");
	    	fileChooser.getExtensionFilters().add(new ExtensionFilter(".don files", "*.don"));;  	
	    	File f = fileChooser.showOpenDialog(stage);
	    	if(f!=null) {
	    		vueListeStagiaire.importer(f);
	    	}
    	});
    	
    	vueListeStagiaire.getBtnTelecharger().setOnAction(event ->{
    		DirectoryChooser dirChooser = new DirectoryChooser();
    		dirChooser.setTitle("Open Resource File");
    		File f = dirChooser.showDialog(stage);
    		if(f != null) {
    			vueListeStagiaire.telecharger(f.getAbsolutePath());
    		}
    	});
    	
    	//Handle Action Button vueEnregistrement
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
    	
    	//Handle Action Button vueModification
    	vueModification.getBtnConfirmation().setOnAction(event ->{
    		//on devra recuperer les textFields
    		ArrayList<String> attributs = vueModification.getTextFields();
    		//creer un Stagiaire
    		Stagiaire cleModifier = new Stagiaire(attributs.get(0),attributs.get(1),attributs.get(2),attributs.get(3),attributs.get(4));
    		//ajouter le Stagiaire sur le tableau
    		vueListeStagiaire.modifierStagiaire(vueModification.getAncienStagiaire(), cleModifier);
    		retourEnListe(stage);
    	});
    	
    	vueModification.getButtonRetour().setOnAction(event ->{
    		retourEnListe(stage);
    	});
	
    	
		stage.setResizable(false);
		stage.setTitle("My Inter - Se Connecter");
		stage.setScene(vueLogin);
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