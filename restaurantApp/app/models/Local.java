package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class Local {

    public int lid;
    public String localName;
    public String address;
    public String codePostal;
    public String type;
    public List<Commande> commandes;
    
    @Id
    public int getLid(){
      return lid;
    }
    public void setLid(int lid){
      this.lid=lid;
    }

    @Column(name = "localName")
    public String getL3ocalName(){
      return localName;
    }
    public void setLocalName(String localName){
      this.localName=localName;
    }

    @Column(name = "address")
    public String getAddress(){
     return address;
    }
    public void setAddress(String address){
      this.address=address;
    }
    
    @Column(unique = true, name = "codePostal")
    public String getCodePostal(){
    	return codePostal;
    }
    public void setCodePostal(String codePostal){
    	this.codePostal=codePostal;
    }

    @Column(unique = true,name = "type")
    public String getType(){
    	return type;
    }
    public void setType(String type){
    	this.type=type;
    }   
    
    @ManyToMany
    @JoinTable(name="LOCAL_COMMANDE")
    public List<Commande> getCommandes(){
    	return commandes;
    }
    public void setCommandes(List<Commande> commandes){
    	this.commandes = commandes;
    }
    public static Finder<Long,Local> find= new Finder<Long,Local>(
    		Long.class, Local.class
    		);
}