package fr.isika.cda22.projet1.composantsJFX;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class ModelTextField extends TextField{

	public ModelTextField(String contenue) {
		super(contenue);
		this.setFont(Font.font("Calibri", 20));
		this.setText(contenue);
	}

}
