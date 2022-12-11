package fr.isika.cda22.projet1;

import fr.isika.cda22.projet1.vues.vueStagiaire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
	public vueStagiaire vueStagiaire;
	
    @Override
    public void start(Stage stage) {
    	vueStagiaire = new vueStagiaire();
		
		stage.setResizable(false);
		stage.setTitle("Liste des stagiaires");
		stage.setScene(vueStagiaire);
		
		stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}