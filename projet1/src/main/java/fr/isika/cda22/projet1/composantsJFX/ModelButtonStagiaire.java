package fr.isika.cda22.projet1.composantsJFX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ModelButtonStagiaire extends Button{


	public ModelButtonStagiaire (String label) {
		super (label);

		this.setPrefSize(130, 50);
		this.setTextFill(Color.SADDLEBROWN);
		this.setFont(Font.font("Calibri Script MT", 14));
		this.setAlignment(Pos.BOTTOM_CENTER);
	}

}
