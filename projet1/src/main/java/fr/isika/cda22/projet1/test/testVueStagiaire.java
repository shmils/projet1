package fr.isika.cda22.projet1.test;

import fr.isika.cda22.projet1.vues.VueStagiaire;
import javafx.application.Application;
import javafx.stage.Stage;

public class testVueStagiaire extends Application {
	//Attribut 
	
		public VueStagiaire vueStagiaire;
	
	@Override
	public void start(Stage stage) throws Exception {
		vueStagiaire = new VueStagiaire();
		
		stage.setResizable(false);
		stage.setTitle("Liste des stagiaires");
		stage.setScene(vueStagiaire);
		
		stage.show();
	
		
}
	
	public static void main(String[] args) {
		launch();
	}
}
