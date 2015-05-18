package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
}
