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
public class ProduitDemande {
	
	public int pdid;
	public Produit produit;
	public Demande demande;
	public int quantite;
	
	@Id
	public int getPdid(){
    	return pdid;
    }
    public void setPdid(int pdid){
    	this.pdid = pdid;
    }
    
    @ManyToOne
    public Produit getProduit(){
    	return produit;
    }
    public void setProduit(Produit produit){
    	this.produit = produit;
    }
    
    @ManyToOne
    public Demande getDemande(){
    	return demande;
    }
    public void setDemande(Demande demande){
    	this.demande = demande;
    }
    
    public int getQauntite(){
    	return quantite;
    }
    public void setQuantite(int quantite){
    	this.quantite = quantite;
    }
    
    public static Finder<Long,ProduitDemande> find= new Finder<Long,ProduitDemande>(Long.class, ProduitDemande.class);
}