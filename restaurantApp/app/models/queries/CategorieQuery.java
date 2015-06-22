package models.queries;

import java.util.List;

import models.Categorie;

public class CategorieQuery {
	
	public static List<Categorie> getItem(){
		return Categorie.find.all();
	}

}