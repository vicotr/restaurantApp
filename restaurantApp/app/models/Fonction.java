package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class Fonction {

    public int fid;
    public String activity;
    
    @Id
    public int getFid(){
    	return fid;
    }
    public void setFid(int fid){
    	this.fid = fid;
    }

    @Column(name = "activity")
    public String getActivity(){
    	return activity;
    }
    public void setActivity(String activity){
    	this.activity = activity;
    }
  
}