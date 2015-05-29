package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model.Finder;

@Entity
public class StockFournisseur {
	
    public int sfid;
    public Produit produit;
    public int quantite;
    public Local lid;
    public String commentaires;
    public int prixUnite;
    
    @Id
    public int getSfid(){
        return sfid;
    }
    public void setSfid(int sfid){
    	this.sfid = sfid;
    }
    
    @ManyToOne
    public Produit getProduits(){
        return produit;
    }
    public void setProduits(Produit produit){
    	this.produit= produit;
    }

    @Column(name = "quantite")
    public int getQuantite(){
        return quantite;
    }
    public void setQuantite(int quantite){
    	this.quantite = quantite;
    }
    
    @ManyToOne
    public Local getLid(){
        return lid;
    }
    public void setL(Local lid){
    	this.lid = lid;
    }

    @Column(name="commentaires")
    public String getCommentaires(){
        return commentaires;
    }
    public void setSfid(String commentaires){
    	this.commentaires = commentaires;
    }

    @Column(name="prixUnite")
    public int getPrixUnite(){
        return prixUnite;
    }
    public void setPrixUnite(int prixUnite){
    	this.prixUnite = prixUnite;
    }
    public static Finder<Long,StockFournisseur> find= new Finder<Long,StockFournisseur>(
    		Long.class, StockFournisseur.class
    		);
}
