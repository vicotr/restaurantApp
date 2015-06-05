package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model.Finder;

@Entity
public class Etat {
	
    public int eid;
    public String name;
    
    @Id
    public int getEid(){
        return eid;
    }
    public void setEid(int eid){
    	this.eid = eid;
    }
    
    @Column(name = "name")
    public String getName(){
        return name;
    }
    public void setName(String name){
    	this.name = name;
    }
    public static Finder<Long,Etat> find= new Finder<Long,Etat>(
    		Long.class, Etat.class
    		);
}
