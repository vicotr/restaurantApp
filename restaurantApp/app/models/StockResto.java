package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class StockResto {
	
    public int srid;
    public List<Produit> produits;
    public int quantite;
    public int stockMax;
    public int stockMin;
    public int stockAlerte;
    public Local local;
    
    @Id
    public int getSrid(){
      return srid;
    }
    public void setSrid(int srid){
      this.srid = srid;
    }
    
   // @Column(name = "produits")
    @OneToMany(cascade=CascadeType.ALL, mappedBy="stockResto")
    public List<Produit> getProduits(){
    	return produits;
    }
    public void setProduits(List<Produit> produits){
       this.produits = produits;
    }

    @Column(name = "quantite")
    public int getQuantite(){
    	return quantite;
    }
    public void setQuantite(int quantite){
       this.quantite = quantite;
    }

    @Column(name = "stockMax")
    public int getStockMax(){
    	return stockMax;
    }
    public void setStockMax(int stockMax){
       this.stockMax = stockMax;
    }
    
    @Column(name = "stockMin")    
    public int getStockMin(){
    	return stockMin;
    }
    public void setStockMin(int stockMin){
       this.stockMin = stockMin;
    }

    @Column(name = "stockAlerte")
    public int getStockAlerte(){
    	return stockAlerte;
    }
    public void setStockAlerte(int stockAlerte){
       this.stockAlerte = stockAlerte;
    }
    
    @OneToOne
    public Local getLocal(){
    	return local;
    }
    public void setLocal(Local local){
       this.local = local;
    }
}