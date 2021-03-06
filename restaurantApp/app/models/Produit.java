package models;
	
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model.Finder;

@Entity
public class Produit {
    
    public int pid;
    public String productName;
    public boolean accessibleFournisseur;
    public Categorie categorie;
    public List<StockResto> stockRestos;
    public List<StockFournisseur> stockFournisseurs;
    
    @Id
    public int getPid(){
    	return pid;
    }
    public void setPid(int pid){
    	this.pid = pid;
    }
    
    @Column(name = "productName")
    public String getProductName(){
    	return productName;
    }
    public void setProductName(String productName){
    	this.productName = productName;
    }
    
    @Column(name="accessibleFournisseur")
    public boolean getAccessibleFournisseur(){
    	return accessibleFournisseur;
    }
    public void setAccessibleFournisseur(boolean accessibleFournisseur){
    	this.accessibleFournisseur = accessibleFournisseur;
    }
    
    @ManyToOne
    public Categorie getCategorie(){
    	return categorie;
    }
    public void setCategorie(Categorie categorie){
    	this.categorie = categorie;
    }
    
   @OneToMany(cascade=CascadeType.ALL, mappedBy="produit")
    public List<StockResto> getStockRestos(){
    	return stockRestos;
    }
    public void setStockRestos(List<StockResto> stockRestos){
    	this.stockRestos = stockRestos;
    } 
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="produit")
    public List<StockFournisseur> getStockFournisseurs(){
    	return stockFournisseurs;
    }
    public void setStockFournisseurs(List<StockFournisseur> stockFournisseurs){
    	this.stockFournisseurs = stockFournisseurs;
    } 
    
    public static Finder<Long,Produit> find= new Finder<Long,Produit>(Long.class, Produit.class);
    
}
