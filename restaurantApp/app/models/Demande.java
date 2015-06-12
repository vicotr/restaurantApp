package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class Demande {

    public int did;
    public Etat etat;
    public String commentaires;
    public String date;
    public Local local;
    public List<Commande> commandes;
    
    @Id
    public int getDid(){
      return did;
    }
    public void setDid(int did){
      this.did = did;
    }

    public String getDate(){
    	return date;
    }
    public void setDate(String date){
    	this.date = date;
    }
    
    @ManyToOne
    @JoinColumn(name="LOCAL_ID", nullable=false)
    public Local getLocal(){
      return local;
    }
    public void setLocal(Local local){
      this.local = local;
    }

    @ManyToOne
    @JoinColumn(name="ETAT_ID", nullable=false)
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
    
    @ManyToMany(mappedBy="demandes")
    public List<Commande> getCommandes(){
        return commandes;
    }
    public void setCommande(List<Commande> commandes){
    	this.commandes = commandes;
    }
    
 
    
    public static Finder<Long,Demande> find= new Finder<Long,Demande>(
    		Long.class, Demande.class
    		);
}
