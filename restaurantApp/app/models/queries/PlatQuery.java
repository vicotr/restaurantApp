package models.queries;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Plat;

public class PlatQuery {
	
	public static List<Plat> getItem(){
		return Plat.find.all();
	}
	
	public static Plat getPlat(int plid){	
		Plat plat = Ebean.find(Plat.class)
					.where().eq("plid",plid)
					.findUnique();
		return plat;
	}
	
}
