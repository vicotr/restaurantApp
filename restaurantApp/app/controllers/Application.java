package controllers;

import static play.data.Form.form;

import com.avaje.ebean.Ebean;

import models.Demande;
import models.Fonction;
import models.Utilisateur;
import models.queries.userLog;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.gerant_administration;
import views.html.gerant_nouveau_membre;
import views.html.login;
import views.html.stocks_gerant;
import views.html.gerant_demande_reapprovisionnement;
import views.html.gerant_accueil;

public class Application extends Controller {
	
	public static class Login {

	    public String email;
	    public String password;

	}
	
	public static class DemandeBis {
		public String commentaires;
	}
	
	public static class NouvelUtilisateur{
		public String identifiant;
		public String password;
		public String role;
	}
	
	public static Result demande(){
		return ok(
				gerant_demande_reapprovisionnement.render()
				);
	}
	
	public static Result administration() {
		return ok(
				gerant_administration.render()
				);
	}

	public static Result nouveauMembre() {
		return ok(
				gerant_nouveau_membre.render()
				);
	}
	
	public static Result nouveauMembreTraitement() {
		Form<NouvelUtilisateur> nouvelUtilisateurForm = form(NouvelUtilisateur.class).bindFromRequest();
		String pseudo = nouvelUtilisateurForm.get().identifiant;
		String password = nouvelUtilisateurForm.get().password;
		String role = nouvelUtilisateurForm.get().role;
		
		Fonction fonction = new Fonction();
		fonction.setActivity(role);
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(pseudo);
		utilisateur.setPassword(password);
		utilisateur.setFonction(fonction);
		
		Ebean.save(fonction);
		Ebean.save(utilisateur);
		
		return ok(
				gerant_administration.render()
				);
	}
	
	public static Result traitement(){
		  Form<DemandeBis> loginForm = form(DemandeBis.class).bindFromRequest();
		    String commentaires = loginForm.get().commentaires;
		    
		    Demande demande = new Demande();
		    demande.setCommentaires(commentaires);
		    
		    Ebean.save(demande);

		return ok(
				gerant_accueil.render()	
				);
	}
	
	public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
	
	public static Result authenticate() {
	    Form<Login> loginForm = form(Login.class).bindFromRequest();
	    String email = loginForm.get().email;
	    String password = loginForm.get().password;
	    if (userLog.authen(email, password)==null) {
	        return badRequest(login.render(loginForm));
	    } else {
	        session().clear();
	        session("email", email);
	        session("password",password);
	        return redirect(
	            routes.Application.index()
	        );
	    }
	}
	
	public static Result logOut() {
		session().clear();
		return ok(
	            login.render(form(Login.class))
	        );
	}
	
	public static Result index(){
		return ok(
				stocks_gerant.render()
				);
	}

}