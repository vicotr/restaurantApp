package models.queries;

import java.util.List;

import models.Etat;

public class EtatQuery {
	
	public static List<Etat> getItem(){
		return Etat.find.all();
	}

}