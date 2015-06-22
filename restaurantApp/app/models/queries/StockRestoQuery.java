package models.queries;

import java.util.List;

import models.Plat;
import models.StockResto;
import models.Utilisateur;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

public class StockRestoQuery {
	
	public static List<StockResto> getItem(){
		return StockResto.find.all();
	}

	public static StockResto getStockResto(int pid){	
		StockResto stockResto = Ebean.find(StockResto.class)
										.where().eq("produit_pid",pid)
										.findUnique();
		return stockResto;
	}
	
	public static StockResto getStockRestoSrid(int srid){
		StockResto stockResto = Ebean.find(StockResto.class, srid);
		return stockResto;
	}
	
}