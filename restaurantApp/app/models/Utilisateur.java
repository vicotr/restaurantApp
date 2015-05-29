package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Utilisateur extends Model {

	public int uid;
    public String lastName;
    public String firstName;
    public String email;
    public String password;
    public String pseudo;
    public Fonction fonction;
    public Local local;
    
    @Id
    public int getUid(){
      return uid;
    }
    public void setUid(int uid){
      this.uid = uid;
    }

    @Column(name = "lastName")
    public String getLastName(){
      return lastName;
    }
    public void setLastName(String lastName){
      this.lastName = lastName;
    }

    @Column(name = "firstName")
    public String getFirstName(){
      return firstName;
    }
    public void setFirstName(String firstName){
      this.firstName = firstName;
    }

    @Column(unique = true, name = "email")
    public String getEmail(){
      return email;
    }
    public void setEmail(String email){
      this.email = email;
    }

    @Column(unique = true,name = "password")
    public String getPassword(){
      return password;
    }
    public void setPassword(String password){
      this.password = password;
    }

    @Column(unique = true,name = "pseudo")
    public String getPseudo(){
      return pseudo;
    }
    public void setPseudo(String pseudo){
      this.pseudo = pseudo;
    }

    @ManyToOne
    public Fonction getFonction(){
      return fonction;
    }
    public void setFonction(Fonction fonction){
      this.fonction = fonction;
    }
    
   @ManyToOne
    public Local getLocal(){
      return local;
    }
    public void setLocal(Local local){
      this.local = local;
    }
    
    public static Finder<Long,Utilisateur> find= new Finder<Long,Utilisateur>(
    		Long.class, Utilisateur.class
    		);

}