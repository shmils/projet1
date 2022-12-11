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
	public VueLogin vueLogin;

    @Override
    public void start(Stage stage) {
    	vueStagiaire = new VueStagiaire();
    	vueEnregistrement = new VueEnregistrement();
    	vueModification = new VueModification("milany", "hossein", "45", "cda al 22", "2022");
    	vueLogin = new VueLogin();
    	
    	vueLogin.btnLogin.setOnAction(event ->{
    		vueLogin.authentifier(vueLogin.txtlabelNom.getText(), 
    				vueLogin.txtMotdePass.getText(),vueLogin.cbAdmin.isSelected());			
		});
    	
		stage.setResizable(false);
		stage.setTitle("Liste des stagiaires");
		stage.setScene(vueLogin);
		stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}