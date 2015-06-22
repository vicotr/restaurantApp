package models.queries;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

import models.Produit;
import models.Utilisateur;

public class UtilisateurQuery {
	
public static List<Utilisateur> getItem(){
		return Utilisateur.find.all();
	}

public static int getfonction(String mail){
	
	Utilisateur utilisateur = Ebean.find(Utilisateur.class)
									.where().eq("email",mail)
									.findUnique();
	
	return utilisateur.fonction.fid;
}

}
