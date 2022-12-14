package fr.isika.cda22.projet1.composantsJFX;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
/**
 * Class representant HBox contenant le logo de l'application
 * @author shmilany
 *
 */
public class HbLogo extends HBox{
	
	/**
	 * constructeur permettant d'initialiser un HbLog
	 */
	public HbLogo() {
		super(); //constructeur HBox par default
		Label myIntern = new Label("My intern"); //creer un Label
		myIntern.setTextFill(Color.SADDLEBROWN); //changer le couleur de texte
		myIntern.setFont(Font.font("Brush Script MT", 25)); //change le style de texte
		myIntern.setAlignment(Pos.TOP_RIGHT); //changer l'alignement

		Circle cercle = new Circle(5); //creer une cercle
		cercle.setFill(Color.GOLD); //choisir le couleur de remplissage de cercle
		cercle.setStroke(Color.SADDLEBROWN); 
		
		this.getChildren().addAll(cercle,myIntern); //ajouter les composant
		this.setAlignment(Pos.BASELINE_RIGHT); //changer l'alignement
		this.setSpacing(3);
	}
	
}
