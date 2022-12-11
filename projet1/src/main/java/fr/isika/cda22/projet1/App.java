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
	
	public VueStagiaire vueStagiaire;
	public VueEnregistrement vueEnregistrement;
	public VueModification vueModification;
	
    @Override
    public void start(Stage stage) {
    	vueStagiaire = new VueStagiaire();
    	vueEnregistrement = new VueEnregistrement();
    	vueModification = new VueModification("milany", "hossein", "45", "cda al 22", "2022");
    	
		stage.setResizable(false);
		stage.setTitle("Liste des stagiaires");
//		stage.setScene(vueStagiaire);
		stage.setScene(vueEnregistrement);
		
		stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}