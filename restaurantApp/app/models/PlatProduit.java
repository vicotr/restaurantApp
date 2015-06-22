package models;
	
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model.Finder;

@Entity
public class PlatProduit {
	
	public int plpid;
	public Produit produit;
	public Plat plat;
	public int quantite;
	public String unite;
	
	@Id
	public int getPlpid(){
    	return plpid;
    }
    public void setPlpid(int plpid){
    	this.plpid = plpid;
    }
    
    @ManyToOne
    public Produit getProduit(){
    	return produit;
    }
    public void setProduit(Produit produit){
    	this.produit = produit;
    }
    
    @ManyToOne
    public Plat getPlat(){
    	return plat;
    }
    public void setPlat(Plat plat){
    	this.plat = plat;
    }
    
    public int getQauntite(){
    	return quantite;
    }
    public void setQuantite(int quantite){
    	this.quantite = quantite;
    }
    
    public String getUnite(){
    	return unite;
    }
    public void setUnite(String unite){
    	this.unite = unite;
    }
    
    public static Finder<Long,PlatProduit> find= new Finder<Long,PlatProduit>(Long.class, PlatProduit.class);
}
