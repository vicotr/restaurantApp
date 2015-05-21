package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model.Finder;

@Entity
public class Plat {
	
	   public int plid;
	   public String platName;
	   public List<Produit> produits;
	    
	   @Id
	   public int getPlid(){
		   return plid;
	   }
	   public void setPlid(int plid){
		   this.plid = plid;
	   }
	   
	   @Column(name = "Plat")
	   public String getPlatName(){
		   return platName;
	   }
	   public void setPlatName(String platName){
		   this.platName = platName;
	   }
	   
	   @ManyToMany
	   @JoinTable(name="PLAT_PRODUIT")
	   public List<Produit> getProduits(){
		   return produits;
	   }
	   public void setProduits(List<Produit> produits){
		   this.produits = produits;
	   }
	   public static Finder<Long,Utilisateur> find= new Finder<Long,Utilisateur>(
	    		Long.class, Utilisateur.class
	    		);
}
