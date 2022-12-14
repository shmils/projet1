package fr.isika.cda22.projet1.vues;

import java.io.InputStream;
import java.util.ArrayList;

import fr.isika.cda22.projet1.composantsJFX.HbLogo;
import fr.isika.cda22.projet1.entites.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * classe representant une VueLogin 
 * 
 * @author FLICI Lilia
 *
 */
public class VueLogin extends Scene{

	private ArrayList<Formatteur> Utilisateur;
	private Button btnLogin;
	private TextField tfNom;
	private CheckBox cbAdmin;
	private PasswordField PfMotdePass;
	private TextField TfMotdePass;
	private Label msgErreur;
	private ImageView viewHidden;
	private Button btnChangerVisibilite;
	
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
	
	public Label getMsgErreur() {
		return msgErreur;
	}

	public void setMsgErreur(Label msgErreur) {
		this.msgErreur = msgErreur;
	}

	public TextField getTfMotdePass() {
		return TfMotdePass;
	}

	public void setTfMotdePass(TextField tfMotdePass) {
		TfMotdePass = tfMotdePass;
	}

	/**
	 * constructeur permettant d'initialiser une VueLogin
	 */
	public VueLogin() {
		super(new VBox(),400,600);
		VBox root = (VBox) this.getRoot();
		
		//On stylise notre panneau:

		root.setStyle("-fx-background-color:beige");
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
	
		//On instancie une HBox:
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
		
        // on instancie une GridPane pour tous:
        
		GridPane gridUserPass = new GridPane();
		
	  
		Label labelNom = new Label("Username");
		labelNom.setTextFill(Color.SADDLEBROWN);
		labelNom.setFont(Font.font("Calibri",20));
		
		tfNom= new TextField();
			
		
		Label labelMotdePass = new Label("Password");
		labelMotdePass.setTextFill(Color.SADDLEBROWN);
		labelMotdePass.setFont(Font.font("Calibri",20));
		
		PfMotdePass = new PasswordField();
		PfMotdePass.setVisible(true);
		
		TfMotdePass = new TextField();
		TfMotdePass.setVisible(false);
		StackPane spMotdePasse = new StackPane(PfMotdePass, TfMotdePass); //creer stackpane et lui ajouter pfMotdePass et tfMotDePass
		
		//on instacie une imageView pour btnChangerVisibilite
		InputStream input1 = getClass().getResourceAsStream("/fr/isika/cda22/projet1/images/hidden.png");
		Image imgHidden = new Image(input1);
        ImageView viewHidden = new ImageView(imgHidden);
        viewHidden.setFitHeight(15);
        viewHidden.setPreserveRatio(true);
        
		//on instacie une imageView pour btnChangerVisibilite
        InputStream input2 = getClass().getResourceAsStream("/fr/isika/cda22/projet1/images/eye.png");
        Image imgVisibile = new Image(input2);
        ImageView viewVisibile = new ImageView(imgVisibile);
        viewVisibile.setFitHeight(15);
        viewVisibile.setPreserveRatio(true);
        
        btnChangerVisibilite = new Button();
        btnChangerVisibilite.setGraphic(viewHidden);
        btnChangerVisibilite.setAlignment(Pos.CENTER_RIGHT);
        btnChangerVisibilite.setOnAction(new EventHandler<ActionEvent>() { //action handler pour changer la visibilité de mot De Passe (inverter visibilite de pf et tf motDePasse=
			@Override
			public void handle(ActionEvent event) {
				if(PfMotdePass.isVisible()) {
					btnChangerVisibilite.setGraphic(viewVisibile);
					PfMotdePass.setVisible(false);
					TfMotdePass.setVisible(true);
					TfMotdePass.setText(PfMotdePass.getText());
				} else {
					btnChangerVisibilite.setGraphic(viewHidden);
					TfMotdePass.setVisible(false);
					PfMotdePass.setVisible(true);
					PfMotdePass.setText(TfMotdePass.getText());
				}
			}
        });
        
        HBox hbMotdePass = new HBox(2, spMotdePasse, btnChangerVisibilite);
        
		gridUserPass.add(labelNom, 0, 0);
		gridUserPass.add(tfNom, 1, 0);
		gridUserPass.add(labelMotdePass, 0, 1);
		gridUserPass.add(hbMotdePass, 1,1);
		
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
		
		//on instacie msg Erreur
		msgErreur = new Label();
		msgErreur.setText("username ou mot de passe sont erronées!\n"
				+ "Veuillez reessayer");
		msgErreur.setTextFill(Color.RED);
		msgErreur.setFont(Font.font("Calibri",15));
		msgErreur.setAlignment(Pos.CENTER);
		msgErreur.setVisible(false);
		
		//On donne nos éléments graphiques à notre panneau:

		root.getChildren().add(hboxMyIntern);
		root.getChildren().add(welcomeImg);
		root.getChildren().add(gridUserPass);
		root.getChildren().add(btnLogin);
		root.getChildren().add(msgErreur);

		VBox.setMargin(gridUserPass, new Insets(50,0,20,0));
		
		
		Formatteur Hossein = new Formatteur("MILANI", "Hossein","hossein","123");
		Admin Lilia = new Admin ("FLICI","Lilia","lilia","321");
		Utilisateur = new ArrayList<>();
		Utilisateur.add(Hossein);
		Utilisateur.add(Lilia);
			
	}
	
	/**
	 * methode permettant de verifier le username et mot de passe 
	 * @param nomUtilisateur
	 * @param motDePasse
	 * @param isAdmin
	 * @return
	 */
	public boolean authentifier(String nomUtilisateur, String motDePasse, boolean isAdmin) {
		for(int i=0 ; i < Utilisateur.size();i++ ) { //parcourir toute la liste des utlisateurs
			Formatteur user = Utilisateur.get(i); //pour le formatteur à l'indice i
			if(isAdmin) { //si isAdmin 
				if(user.getNomUtilisateur().equals(nomUtilisateur) &&
						user.getMotDePasse().equals(motDePasse) && user.isAdmin()) { //verifier coordonnees et si l'utlisateur est admin
					return true;
				}
			} else {
				if(user.getNomUtilisateur().equals(nomUtilisateur) &&
						user.getMotDePasse().equals(motDePasse)) {//verifier coordonnees 
					return true;
				}
			}
			
		}
		return false;
	}
	
	/**
	 * methode permettant de recuperer le mot de passe du champ visible
	 * @return
	 */
	public String getMotdePasse() {
		if(PfMotdePass.isVisible()) {
			return PfMotdePass.getText();
		} else {
			return TfMotdePass.getText();
		}
	}

	/**
	 * methode permettant de reinitialiser la vueLogin
	 */
	public void reInit() {
		this.TfMotdePass.clear();
		this.PfMotdePass.clear();
		this.tfNom.clear();
		this.msgErreur.setVisible(false);
		this.TfMotdePass.setVisible(false);
		this.PfMotdePass.setVisible(true);
		InputStream input1 = getClass().getResourceAsStream("/fr/isika/cda22/projet1/images/hidden.png");
		Image imgHidden = new Image(input1);
        viewHidden = new ImageView(imgHidden);
        viewHidden.setFitHeight(15);
        viewHidden.setPreserveRatio(true);
		this.btnChangerVisibilite.setGraphic(this.viewHidden);
	}

	
}