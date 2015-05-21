package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model.Finder;

@Entity
public class Categorie {
	
    public int catid;
    public String categoryName;
    
    @Id
    public int getCatid(){
    	return catid;
    }
    public void setCatid(int catid){
    	this.catid = catid;
    }
    
    @Column(name = "categoryName")
    public String getCategoryName(){
    	return categoryName;
    }
    public void setCategoryName(String categoryName){
    	this.categoryName = categoryName;
    }
    public static Finder<Long,Utilisateur> find= new Finder<Long,Utilisateur>(
    		Long.class, Utilisateur.class
    		);
}
