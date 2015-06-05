package models.queries;

import java.util.List;

import com.avaje.ebean.Query;

import models.Produit;
import models.Utilisateur;

public class UtilisateurQuery {
	
public static List<Utilisateur> getItem(){
		return Utilisateur.find.all();
	}

}
