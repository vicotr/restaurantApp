package models.queries;

import java.util.List;

import models.Fonction;

public class FonctionQuery {
	
	public static List<Fonction> getItem(){
		return Fonction.find.all();
	}

}

