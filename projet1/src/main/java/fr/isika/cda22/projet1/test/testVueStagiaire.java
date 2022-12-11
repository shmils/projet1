package fr.isika.cda22.projet1.test;

import fr.isika.cda22.projet1.vues.vueStagiaire;
import javafx.application.Application;
import javafx.stage.Stage;

public class testVueStagiaire extends Application {
	//Attribut 
	
		public vueStagiaire vueStagiaire;
	
	@Override
	public void start(Stage stage) throws Exception {
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
