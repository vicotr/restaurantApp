package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.ebean.Model.Finder;

@Entity
public class Commande {
	
    public int cid;
    public List<Local> locals;
    public List<Demande> demandes;
    public Etat etat;
    public String commentaires;
    
    @Id
    public int getCid(){
        return cid;
    }
    public void setCid(int cid){
    	this.cid = cid;
    }
    
    @ManyToMany(mappedBy="commandes")
    public List<Local> getLocal(){
        return locals;
    }
    public void setLocal(List<Local> locals){
    	this.locals = locals;
    }
    
    @ManyToMany
    @JoinTable(name="COMMANDE_DEMANDE")
    public List<Demande> getDemandes(){
        return demandes;
    }
    public void setDemande(List<Demande> demandes){
    	this.demandes = demandes;
    }
    
    @Column(name = "eid")
    @ManyToOne
    public Etat getEtat(){
        return etat;
    }
    public void setEtat(Etat etat){
    	this.etat = etat;
    }
    
    @Column(name="commentaires")
    public String getCommentaires(){
        return commentaires;
    }
    public void setCommentaires(String commentaires){
    	this.commentaires = commentaires;
    }
    public static Finder<Long,Commande> find= new Finder<Long,Commande>(
    		Long.class, Commande.class
    		);
}
