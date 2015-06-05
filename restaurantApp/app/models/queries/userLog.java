package models.queries;
import models.Utilisateur;
import play.db.ebean.*;


public class userLog {
	
	public static Utilisateur authen(String email, String password){
		
		return Utilisateur.find.where()
				.eq("email", email)
				.eq("password", password)
				.findUnique();
	}
}
