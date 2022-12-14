package fr.isika.cda22.projet1.composantsJFX;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * classe representant un TextField typique
 * @author shmilany
 *
 */
public class ModelTextField extends TextField{

	/**
	 * constructeur permettant d'initialiser un ModelTextField
	 * @param contenue le text par default
	 */
	public ModelTextField(String contenue) {
		super(contenue); //constructeur d'un TextField
		this.setFont(Font.font("Calibri", 20)); //changer police
		this.setText(contenue);
	}

}
