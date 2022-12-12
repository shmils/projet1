package fr.isika.cda22.projet1.composantsJFX;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class HbLogo extends HBox{

	public HbLogo() {
		super();
		Label myIntern = new Label("My intern");
		myIntern.setTextFill(Color.SADDLEBROWN);
		myIntern.setFont(Font.font("Brush Script MT", 25));
		myIntern.setAlignment(Pos.TOP_RIGHT);

		Circle cercle = new Circle(5);
		cercle.setFill(Color.GOLD);
		cercle.setStroke(Color.SADDLEBROWN);
		
		this.getChildren().addAll(cercle,myIntern);
		this.setAlignment(Pos.BASELINE_RIGHT);
		this.setSpacing(3);
	}
	
}
