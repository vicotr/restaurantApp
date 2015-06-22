package models.queries;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

import models.Produit;
import models.StockResto;
import models.Utilisateur;

public class ProductQuery {
	
	public static List<Produit> getItem(){
			return Produit.
					find.all();
	}
	
	public static Produit getProduit(int pid){	
		Produit produit = Ebean.find(Produit.class)
										.where().eq("pid",pid)
										.findUnique();
		return produit;
	}

}
