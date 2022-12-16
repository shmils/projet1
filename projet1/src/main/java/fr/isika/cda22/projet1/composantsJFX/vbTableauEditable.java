package fr.isika.cda22.projet1.composantsJFX;

import fr.isika.cda22.projet1.entites.Arbre;
import fr.isika.cda22.projet1.entites.Stagiaire;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;

public class vbTableauEditable extends vbTableau {
	
	private Arbre monArbre;
	public vbTableauEditable(Arbre monArbre) {
		super();
		this.monArbre = monArbre;
		handleChange(nomCol);
		handleChange(prenomCol);
		handleChange(localisationCol);
		handleChange(nomPromoCol);
		handleChange(anneePromoCol);
	}
	
	public void handleChange(TableColumn<Stagiaire, String> col) {
		col.setCellFactory(TextFieldTableCell.<Stagiaire>forTableColumn());
		col.setOnEditCommit(new EventHandler<CellEditEvent<Stagiaire,String>>() {
			@Override
			public void handle(CellEditEvent<Stagiaire, String> event) {
                Stagiaire s = (Stagiaire) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
                if(col.equals(nomCol)) {
                	s.setNom(event.getNewValue());
                }
                if(col.equals(prenomCol)) {
                	s.setPrenom(event.getNewValue());
                }
                if(col.equals(localisationCol)) {
                	s.setLocalisation(event.getNewValue());
                }
                if(col.equals(nomPromoCol)) {
                	s.setNomFormation(event.getNewValue());
                }
                if(col.equals(anneePromoCol)) {
                	s.setAnneePromo(event.getNewValue());
                }
                	
            }
		});
	}
	
}
