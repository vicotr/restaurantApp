package models.queries;

import java.util.List;

import models.StockResto;

import com.avaje.ebean.Query;

public class StockRestoQuery {
	
	public static List<StockResto> getItem(){
		return StockResto.find.all();
	}

}