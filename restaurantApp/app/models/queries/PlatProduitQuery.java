package models.queries;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Plat;
import models.PlatProduit;

public class PlatProduitQuery {
	
	public static List<PlatProduit> getPlatProduitsBis(int plid){
		
		List<PlatProduit> platProduits = Ebean.find(PlatProduit.class)
											  .where().eq("plat_plid",plid)
											  .findList();
		return platProduits;
	}
	
	public static List<PlatProduit> deletePlatProduits(int pid){
		
		List<PlatProduit> platProduits = Ebean.find(PlatProduit.class)
											  .where().eq("produit_pid",pid)
											  .findList();
		return platProduits;
	}
}
