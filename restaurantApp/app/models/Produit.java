package models;
	
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Produit {
    
    public int pid;
    public String productName;
    public boolean accessibleFournisseur;
    public String unite;
    public Categorie categorie;
    public List<Plat> plats;
    public StockResto stockResto;
    public StockFournisseur stockFournisseur;
    
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

    @Column(name="unite")
    public String getUnite(){
    	return unite;
    }
    public void setUnite(String unite){
    	this.unite = unite;
    }
    
    @ManyToOne
    @Column(name="categorie")
    public Categorie getCategorie(){
    	return categorie;
    }
    public void setCategorie(Categorie categorie){
    	this.categorie = categorie;
    }
    
   @ManyToMany(mappedBy="produits")
    public List<Plat> getPlat(){
    	return plats;
    }
    public void setPlat(List<Plat> plats){
    	this.plats = plats;
    }
    
    @ManyToOne
    @JoinColumn(name="stockResto",nullable=false)
    public StockResto getStockResto(){
    	return stockResto;
    }
    public void setStockResto(StockResto stockResto){
    	this.stockResto = stockResto;
    }
    
    @ManyToOne
    @JoinColumn(name="stockFournisseur",nullable=false)
    public StockFournisseur getStockFournisseur(){
    	return stockFournisseur;
    }
    public void setStockFournisseur(StockFournisseur stockFournisseur){
    	this.stockFournisseur = stockFournisseur;
    }
}