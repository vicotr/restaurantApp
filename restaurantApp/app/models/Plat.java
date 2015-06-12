package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model.Finder;

@Entity
public class Plat {
	
	   public int plid;
	   public String platName;
	   
	   @Id
	   public int getPlid(){
		   return plid;
	   }
	   public void setPlid(int plid){
		   this.plid = plid;
	   }
	   
	   @Column(name = "Plat")
	   public String getPlatName(){
		   return platName;
	   }
	   public void setPlatName(String platName){
		   this.platName = platName;
	   }
	  
	   public static Finder<Long,Plat> find= new Finder<Long,Plat>(
	    		Long.class, Plat.class
	    		);
}
