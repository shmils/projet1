package fr.isika.cda22.projet1.composantsJFX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * classe representant un Button typique
 * 
 * @author shmilany
 *
 */
public class ModelButton extends Button {

	/**
	 * constructeur permettant d'initialiser un ModelButton
	 * @param label le text par default
	 */
	public ModelButton (String label) {
		super (label); //constructeur surchargé du Button
		
		this.setPrefSize(120, 10); //changer taille
		this.setTextFill(Color.SADDLEBROWN); //change couleur texte
		this.setFont(Font.font("Calibri Script MT", 14)); //change police texte
		this.setAlignment(Pos.CENTER); //chagner alignement

}}
