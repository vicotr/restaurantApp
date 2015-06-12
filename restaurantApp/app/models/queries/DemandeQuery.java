package models.queries;

import java.util.List;

import models.Demande;
import models.Fonction;

public class DemandeQuery {
	public static List<Demande> getItem(){
		return Demande.find.all();
	}
}
