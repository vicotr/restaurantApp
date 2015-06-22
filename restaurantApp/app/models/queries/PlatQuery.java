package models.queries;

import java.util.List;

import models.Plat;

public class PlatQuery {
	
	public static List<Plat> getItem(){
		return Plat.find.all();
	}
	
}
