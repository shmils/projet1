package fr.isika.cda22.projet1.vues;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import fr.isika.cda22.projet1.composantsJFX.HbLogo;
import fr.isika.cda22.projet1.composantsJFX.ModelTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VueEnregistrement extends Scene{

	//attributs

	private Label titrePage;
	private TextField tfNom;
	private TextField tfPrenom;
	private TextField tfLocalisation;
	private TextField tfFormation;
	private TextField tfAnneePromo;
	private Button btnConfirmation;
	private Button buttonRetour;


	public VueEnregistrement() {

		super(new VBox(),500,700);

		//sous arbre 1 (bouton retour et my intern)


		buttonRetour = new Button("Retour"); 
		buttonRetour.setAlignment(Pos.BASELINE_LEFT);

//		Label myIntern = new Label("My intern");
//		myIntern.setTextFill(Color.SADDLEBROWN);
//		myIntern.setFont(Font.font("Brush Script MT", 25));
//		myIntern.setAlignment(Pos.TOP_RIGHT);
//
//		Circle cercle = new Circle(5);
//		cercle.setFill(Color.GOLD);
//		cercle.setStroke(Color.SADDLEBROWN);
//
//		HBox hboxCercleInterne = new HBox(cercle,myIntern);
//		hboxCercleInterne.setAlignment(Pos.BASELINE_RIGHT);
//		hboxCercleInterne.setSpacing(3);
		HbLogo hboxCercleInterne = new HbLogo();
		
		HBox hboxRetourListeStagiaire = new HBox (buttonRetour,hboxCercleInterne);
		hboxRetourListeStagiaire.setSpacing(300);
		hboxRetourListeStagiaire.setPadding(new Insets(10));
		hboxRetourListeStagiaire.setAlignment(Pos.CENTER);

		//sous arbre 2 (Ajouter stagiaire et image)


		//Label ajouterFicheStagiaire = new Label ("Ajouter la fiche stagiaire");
		titrePage = new Label("Ajouter la fiche stagiaire");
		titrePage.setTextFill(Color.SADDLEBROWN);
		titrePage.setFont(Font.font("Brush Script MT", 45));


		// on instancie l'image
		final URL imageURL = getClass().getResource("/fr/isika/cda22/projet1/images/imageAjouterStagiaire.png");
		final Image image = new Image(imageURL.toExternalForm());
		final ImageView imageView = new ImageView(image);
		imageView.setFitWidth(250);
		imageView.setPreserveRatio(true);


		VBox vboxCentrale = new VBox (titrePage);
		vboxCentrale.setAlignment(Pos.CENTER);
		vboxCentrale.getChildren().add(imageView);

		//sous arbre 3 (informations stagaire)

		tfNom = initTf("Nom");
		tfPrenom = initTf("Prénom");
		tfLocalisation = initTf("Localisation");
		tfFormation = initTf("Formation");
		tfAnneePromo = initTf("Annee de promotion");
		
		VBox vboxInformationsStagiaire = new VBox(tfNom,tfPrenom,tfLocalisation,tfFormation,tfAnneePromo);
		vboxInformationsStagiaire.setAlignment(Pos.CENTER);
		vboxInformationsStagiaire.setSpacing(30);


		//sous arbre 4 (bouton confirmer)

		btnConfirmation = new Button ("Cliquez pour valider");
		btnConfirmation.setPrefSize(200,20);
		btnConfirmation.setText("Ajouter");
		btnConfirmation.setTextFill(Color.SADDLEBROWN);

		btnConfirmation.setFont(Font.font("Calibri", 20));

		HBox hboxConfirmation = new HBox(btnConfirmation);
		hboxConfirmation.setAlignment(Pos.CENTER);

		//construction de la Vbox root

		VBox root = (VBox) this.getRoot();
		root.setStyle("-fx-background-color:beige");

		root.getChildren().addAll(hboxRetourListeStagiaire,vboxCentrale,vboxInformationsStagiaire,hboxConfirmation);
		root.setSpacing(10);
	}

	public Label getTitrePage() {
		return titrePage;
	}

	public void setTitrePage(Label titrePage) {
		this.titrePage = titrePage;
	}

	public TextField getTfNom() {
		return tfNom;
	}

	public void setTfNom(TextField tfNom) {
		this.tfNom = tfNom;
	}

	public TextField getTfPrenom() {
		return tfPrenom;
	}

	public void setTfPrenom(TextField tfPrenom) {
		this.tfPrenom = tfPrenom;
	}

	public TextField getTfLocalisation() {
		return tfLocalisation;
	}

	public void setTfLocalisation(TextField tfLocalisation) {
		this.tfLocalisation = tfLocalisation;
	}

	public TextField getTfFormation() {
		return tfFormation;
	}

	public void setTfFormation(TextField tfFormation) {
		this.tfFormation = tfFormation;
	}

	public TextField getTfAnneePromo() {
		return tfAnneePromo;
	}

	public void setTfAnneePromo(TextField tfAnneePromo) {
		this.tfAnneePromo = tfAnneePromo;
	}

	public Button getBtnConfirmation() {
		return btnConfirmation;
	}

	public void setBtnConfirmation(Button btnConfirmation) {
		this.btnConfirmation = btnConfirmation;
	}

	public Button getButtonRetour() {
		return buttonRetour;
	}

	public void setButtonRetour(Button buttonRetour) {
		this.buttonRetour = buttonRetour;
	}

	private ModelTextField initTf(String contenue) {
		ModelTextField tf = new ModelTextField(contenue);
		tf.setMaxSize(300,20);
		tf.setOnMouseClicked(event ->{
			tf.clear();
		});
		return tf;
	}
	
	public ArrayList<String> getTextFields(){
		ArrayList<String> attributs = new ArrayList<>();
		attributs.add(tfNom.getText());
		attributs.add(tfPrenom.getText());
		attributs.add(tfLocalisation.getText());
		attributs.add(tfFormation.getText());
		attributs.add(tfAnneePromo.getText());
		return attributs;
	}
	
}
