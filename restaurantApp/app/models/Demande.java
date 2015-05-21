package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class Demande {

    public int did;
    public Local lid;
    public Etat eid;
    public String commentaires;
    public Local local;
    public List<Commande> commandes;
    
    @Id
    public int getDid(){
      return did;
    }
    public void setDid(int did){
      this.did = did;
    }

    @ManyToOne
    @JoinColumn(name="LOCAL_ID", nullable=false)
    public Local getLid(){
      return lid;
    }
    public void setLid(Local lid){
      this.lid = lid;
    }

    @ManyToOne
    @JoinColumn(name="ETAT_ID", nullable=false)
     public Etat getEid(){
      return eid;
    }
    public void setEid(Etat eid){
      this.eid = eid;
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
    public void setDemande(List<Commande> commandes){
    	this.commandes = commandes;
    }
    public static Finder<Long,Utilisateur> find= new Finder<Long,Utilisateur>(
    		Long.class, Utilisateur.class
    		);
}
