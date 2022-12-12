package fr.isika.cda22.projet1;

import fr.isika.cda22.projet1.vues.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


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
    	vueModification = new VueModification("milany", "hossein", "45", "cda al 22", "2022");
    	vueLogin = new VueLogin();
    	
    	vueLogin.getBtnLogin().setOnAction(event ->{
    		if(vueLogin.authentifier(vueLogin.getTfNom().getText(), 
    				vueLogin.getPfMotdePass().getText(),vueLogin.getCbAdmin().isSelected())) {
    			stage.setScene(vueListeStagiaire);
        		stage.setTitle("My Intern - Liste");
    		}
		});
    	
    	vueListeStagiaire.getBtnModifierStagiaire().setOnAction(event ->{
    		stage.setScene(vueModification);
    		stage.setTitle("My Intern - Modifier");
    	});
    	
    	vueListeStagiaire.getSeDeconnecter().setOnAction(event ->{
    		vueLogin.getTfNom().clear();
    		vueLogin.getPfMotdePass().clear();
    		stage.setScene(vueLogin);
    		stage.setTitle("My Intern - Se Connecter");
    	});
    	
    	vueListeStagiaire.getBtnAjouterStagiaire().setOnAction(event ->{
    		stage.setScene(vueEnregistrement);
    		stage.setTitle("My Intern - Ajouter");
    	});
    	
    	vueEnregistrement.getBtnConfirmation().setOnAction(event ->{
    		retourEnListe(stage);
    	});
    	vueEnregistrement.getButtonRetour().setOnAction(event ->{
    		retourEnListe(stage);
    	});
    	vueModification.getBtnConfirmation().setOnAction(event ->{
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