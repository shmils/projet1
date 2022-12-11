package fr.isika.cda22.projet1.composantsJFX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ModelButton extends Button {

	public ModelButton (String label) {
		super (label);
		
		this.setPrefSize(120, 10);
		this.setTextFill(Color.SADDLEBROWN);
		this.setFont(Font.font("Calibri Script MT", 14));


	
}}
