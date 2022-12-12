package fr.isika.cda22.projet1.vues;

import java.util.ArrayList;

import fr.isika.cda22.projet1.composantsJFX.HbLogo;
import fr.isika.cda22.projet1.entites.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class VueLogin extends Scene{

	private ArrayList<Formatteur> Utilisateur;
	private Button btnLogin;
	private TextField tfNom;
	private CheckBox cbAdmin;
	private PasswordField PfMotdePass;
	
	public ArrayList<Formatteur> getUtilisateur() {
		return Utilisateur;
	}

	public void setUtilisateur(ArrayList<Formatteur> utilisateur) {
		Utilisateur = utilisateur;
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}

	public TextField getTfNom() {
		return tfNom;
	}

	public void setTfNom(TextField tfNom) {
		this.tfNom = tfNom;
	}

	public CheckBox getCbAdmin() {
		return cbAdmin;
	}

	public void setCbAdmin(CheckBox cbAdmin) {
		this.cbAdmin = cbAdmin;
	}

	public PasswordField getPfMotdePass() {
		return PfMotdePass;
	}

	public void setPfMotdePass(PasswordField txtMotdePass) {
		this.PfMotdePass = txtMotdePass;
	}

	public VueLogin() {
		super(new VBox(),300,600);
		VBox root = (VBox) this.getRoot();
		
		//On stylise notre panneau:

		root.setStyle("-fx-background-color:beige");
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
	
		//On instancie une HBox:
		
		
//		HBox hboxMyIntern = new HBox();
//		
//		// on commence par instancie une label :
//		
//		Label myIntern = new Label ("My intern ");
//		myIntern.setTextFill(Color.SADDLEBROWN);
//		myIntern.setFont(Font.font("Brush Script MT", 25));
//		//myIntern.relocate(20, 100);
//		myIntern.setAlignment(Pos.TOP_CENTER);
//		
//		//On instancie des objets à afficher sur le panneau, on les stylise et on les positionne:
//		Circle cercle = new Circle(5);
//		cercle.setFill(Color.GOLD);
//		cercle.setStroke(Color.SADDLEBROWN);
//		//cercle.relocate(288,30);
//		
//		hboxMyIntern.getChildren().addAll(cercle,myIntern);
//		hboxMyIntern.setAlignment(Pos.CENTER);
//		hboxMyIntern.setSpacing(5);
		HbLogo hboxMyIntern = new HbLogo();
		hboxMyIntern.setAlignment(Pos.CENTER);
		
		//On instancie une VBox:
		
		VBox welcomeImg = new VBox();
		
		Label welcome = new Label ("Welcome Back!");
		welcome.setTextFill(Color.SADDLEBROWN);
		welcome.setFont(Font.font("Brush Script MT", 25));
		welcome.setAlignment(Pos.TOP_CENTER);
		
		
		// on instancie l'image 
        
		final java.net.URL imageURL = getClass().getResource("/fr/isika/cda22/projet1/images/imageSeConnecter.png");
        final Image image = new Image(imageURL.toExternalForm()); 
        final ImageView imageView = new ImageView(image); 
        imageView.setFitWidth(300); 
        imageView.setPreserveRatio(true);
		
        welcomeImg.getChildren().addAll(welcome,imageView);
        welcomeImg.setAlignment(Pos.TOP_CENTER);
        welcomeImg.setSpacing(20);
		
     // on instancie une GridPAne pour tous:
        
		GridPane gridUserPass = new GridPane();
		
	  
		Label labelNom = new Label("Username");
		labelNom.setTextFill(Color.SADDLEBROWN);
		labelNom.setFont(Font.font("Calibri",20));
		
		tfNom= new TextField();
			
		
		Label labelMotdePass = new Label("Password");
		labelMotdePass.setTextFill(Color.SADDLEBROWN);
		labelMotdePass.setFont(Font.font("Calibri",20));
		
		PfMotdePass = new PasswordField();
		gridUserPass.add(labelNom, 0, 0);
		gridUserPass.add(tfNom, 1, 0);
		gridUserPass.add(labelMotdePass, 0, 1);
		gridUserPass.add(PfMotdePass, 1,1);
		
		gridUserPass.setHgap(20);
		gridUserPass.setVgap(10);
		
		gridUserPass.setAlignment(Pos.CENTER);
		
		//On instancie une CheckBox:
		
		cbAdmin = new CheckBox();
		Label checkAdmin = new Label("login as admin");
		HBox hbCheckAdmin = new HBox(5, cbAdmin, checkAdmin);
		hbCheckAdmin.setAlignment(Pos.BASELINE_LEFT);
 	    checkAdmin.setTextFill(Color.SADDLEBROWN);
		checkAdmin.setFont(Font.font("Calibri",15));
	
		gridUserPass.add(hbCheckAdmin, 0, 2, 2, 1);
		
		
		//On instancie le boutton:
		btnLogin = new Button("LogIn");
		btnLogin.setTextFill(Color.SADDLEBROWN);
		btnLogin.setFont(Font.font("Calibri",15));
		btnLogin.setAlignment(Pos.CENTER);
		
		//On donne nos éléments graphiques à notre panneau:

		root.getChildren().add(hboxMyIntern);
		root.getChildren().add(welcomeImg);
		root.getChildren().add(gridUserPass);
		root.getChildren().add(btnLogin);

		VBox.setMargin(gridUserPass, new Insets(50,0,20,0));
		
		
		Formatteur Hossein = new Formatteur("MILANI", "Hossein","hossein","123");
		Admin Lilia = new Admin ("FLICI","Lilia","lilia","321");
		Utilisateur = new ArrayList<>();
		Utilisateur.add(Hossein);
		Utilisateur.add(Lilia);
			
	}
	
	public boolean authentifier(String nomUtilisateur, String motDePasse, boolean isAdmin) {
		for(int i=0 ; i < Utilisateur.size();i++ ) {
			Formatteur user = Utilisateur.get(i);
			if(isAdmin) {
				if(user.getNomUtilisateur().equals(nomUtilisateur) &&
						user.getMotDePasse().equals(motDePasse) && user.isAdmin()) {
					System.out.println("bienvenue admin");
					return true;
				}
			} else {
				if(user.getNomUtilisateur().equals(nomUtilisateur) &&
						user.getMotDePasse().equals(motDePasse)) {
					System.out.println("bienvenue Formateur");
					return true;
				}
			}
			
		}
		System.out.println("erreur");
		return false;
	}

}