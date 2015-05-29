package models.queries;

import java.util.List;

import com.avaje.ebean.Query;

import models.Produit;
import models.Utilisateur;

public class StockGerant {
	
public static List<Produit> getItem(){
		return Produit.
				find.all();
	}

}
