package controllers;

import static play.data.Form.form;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import models.Demande;
import models.Fonction;
import models.Produit;
import models.StockResto;
import models.Utilisateur;
import models.queries.FonctionQuery;
import models.queries.StockGerant;
import models.queries.StockRestoQuery;
import models.queries.UtilisateurQuery;
import models.queries.userLog;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.gerant_administration;
import views.html.gerant_alertes;
import views.html.gerant_nouveau_membre;
import views.html.gerant_suivi_des_commandes;
import views.html.login;
import views.html.gerant_stocks;
import views.html.gerant_demande_reapprovisionnement;
import views.html.gerant_accueil;
import views.html.restaurant_mon_compte;
import views.html.creationIngredient;

public class Application extends Controller {
	
	public static class Login {

	    public String email;
	    public String password;

	}
	
	public static class DemandeBis {
		public String commentaires;
	}
	
	
	// Méthodes get
	public static class NouvelUtilisateur{
		public String identifiant;
		public String password;
		public String role;
	}
	
	public static class NouveauProduit{
		public String nom;
		public int quantite;
		public int minimum;
		public int maximum;
		public int seuilAlerte;
		public String unite;
	}
	
	public static Result demande(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				gerant_demande_reapprovisionnement.render()
				);
        }
	}
	
	public static Result compteResto(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(restaurant_mon_compte.render());
        }
	}
	
	public static Result administration() {
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<Fonction> list_fonctions = FonctionQuery.getItem();
		List<Utilisateur> list_utilisateurs = UtilisateurQuery.getItem();
		return ok(gerant_administration.render(list_utilisateurs, list_fonctions));
        }
	}

	public static Result nouveauMembre() {
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				gerant_nouveau_membre.render()
				);
        }
	}
	
	public static Result logOut() {
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		session().clear();
		return ok(
	            login.render(form(Login.class))
	        );
        }
	}
	
	public static Result index(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<Produit> list_produit = StockGerant.getItem();
		List<StockResto> stockRestos = StockRestoQuery.getItem();
		return ok(gerant_stocks.render(list_produit, stockRestos));
        }
	}
	
	public static Result gerantAccueil(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				gerant_accueil.render()
				);
        }
	}
	
	public static Result gerantAlertes(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				gerant_alertes.render()
				);
        }
	}
	
	public static Result gerantSuivi(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				gerant_suivi_des_commandes.render()
				);
        }
	}
	
	public static Result gerantCompte(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				restaurant_mon_compte.render()
				);
        }
	}
	
	public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }

	
	public static Result gerantStock(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<Produit> list_produit = StockGerant.getItem();
		List<StockResto> stockRestos = StockRestoQuery.getItem();
		return ok(gerant_stocks.render(list_produit, stockRestos));
        }
	}
	
	public static Result creationIngredient(){
		return ok(creationIngredient.render());
	}
	
	
	
	// ==========================================================================
	// ==========================================================================
	// ==========================================================================
	
	//Méthodes POST
	
	public static Result creationIngredientTraitement(){
		Form<NouveauProduit> nouveauProduit = form(NouveauProduit.class).bindFromRequest();
	    
	    String nom = nouveauProduit.get().nom;
		String unite = nouveauProduit.get().unite;
	    
	    Produit produit = new Produit();
	    produit.setProductName(nom);
	    produit.setUnite(unite);
	    
	    Ebean.save(produit);
	    
	    return redirect("/gerant_stocks");
	}
	
	public static Result nouveauMembreTraitement() {
		Form<NouvelUtilisateur> nouvelUtilisateurForm = form(NouvelUtilisateur.class).bindFromRequest();
		String pseudo = nouvelUtilisateurForm.get().identifiant;
		String password = nouvelUtilisateurForm.get().password;
		String role = nouvelUtilisateurForm.get().role;
		int compteur = 0;
		
		// I On récupère toutes les fonctions 
		List<Fonction> listeFonctions = FonctionQuery.getItem();
		
		for(int i = 0; i < listeFonctions.size(); i++){
			Fonction f = listeFonctions.get(i);
			if(role.equals(f.activity)){
				String s = "INSERT INTO utilisateur (pseudo,password,fonction_fid) VALUES (:pseudo,:password,:uid)";
			 	SqlUpdate update = Ebean.createSqlUpdate(s);
			 	update.setParameter("pseudo",pseudo);
			 	update.setParameter("password",password);
			 	update.setParameter("uid", f.fid);
			 
			 	Ebean.execute(update);
			 	compteur ++;
			}
		}
		
		if(compteur == 0){
			Fonction fonction = new Fonction();
			fonction.setActivity(role);
			
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setPseudo(pseudo);
			utilisateur.setPassword(password);
			utilisateur.setFonction(fonction);
			
			Ebean.save(fonction);
			Ebean.save(utilisateur);
		}
		
		return redirect("/gerant_administration");
	}
	
	// demande
	public static Result traitement(){
		  Form<DemandeBis> loginForm = form(DemandeBis.class).bindFromRequest();
		    String commentaires = loginForm.get().commentaires;
		    
		    Demande demande = new Demande();
		    demande.setCommentaires(commentaires);
		    
		    Ebean.save(demande);

		return redirect("/gerant_accueil");
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

	public static Result supprimerMembre(int id){
		
		Utilisateur utilisateur = Ebean.find(Utilisateur.class, id);
		Ebean.delete(utilisateur);
		
		List<Fonction> newFonctions = FonctionQuery.getItem();
		List<Utilisateur> utilisateurs = UtilisateurQuery.getItem();
		return ok(gerant_administration.render(utilisateurs,newFonctions));
	}
	
	public static Result modificationStock(){
		DynamicForm stockForm = Form.form().bindFromRequest();
		int linesUpdated = Integer.parseInt(stockForm.get("linesUpdated"));
		int linesCreated = Integer.parseInt(stockForm.get("linesCreated"));
		int quantite = 0;
		int minimum = 0;
		int maximum = 0;
		int seuilAlerte = 0;
		int srid = 0;
		int pidProduit = 0;
		
		for(int i = 1; i < linesUpdated+1; i++){
			
			quantite = Integer.parseInt(stockForm.get("quantitelineUpdated"+i));
			minimum = Integer.parseInt(stockForm.get("minimumlineUpdated"+i));
			maximum = Integer.parseInt(stockForm.get("maximumlineUpdated"+i));
			seuilAlerte = Integer.parseInt(stockForm.get("alertelineUpdated"+i));
			srid = Integer.parseInt(stockForm.get("hiddenlineUpdated"+i));
		    
			
			String s = "UPDATE stock_resto SET quantite = :quantite, stockMin = :stockMin, stockMax = :stockMax, stockAlerte = :stockAlerte WHERE srid = :srid";
		    SqlUpdate update = Ebean.createSqlUpdate(s);
		 	update.setParameter("quantite",quantite);
		 	update.setParameter("stockMin",minimum);
		 	update.setParameter("stockMax", maximum);
		 	update.setParameter("stockAlerte", seuilAlerte);
		 	update.setParameter("srid", srid);
		 	
		 	Ebean.execute(update);
		}
			
			for(int i = 1; i < linesCreated+1; i++){
				
				if(stockForm.get("quantitelineCreated"+i) != "" && stockForm.get("minimumlineCreated"+i) != "" &&  stockForm.get("maximumlineCreated"+i) != "" && stockForm.get("alertelineCreated"+i) != "" && stockForm.get("ingredientlineCreated"+i) != ""){
				
					quantite = Integer.parseInt(stockForm.get("quantitelineCreated"+i));
					minimum = Integer.parseInt(stockForm.get("minimumlineCreated"+i));
					maximum = Integer.parseInt(stockForm.get("maximumlineCreated"+i));
					seuilAlerte = Integer.parseInt(stockForm.get("alertelineCreated"+i));
					pidProduit = Integer.parseInt(stockForm.get("ingredientlineCreated"+i));
					
					String s = "INSERT INTO stock_resto (quantite,stockMax,stockMin,stockAlerte,produit_pid) VALUES (:quantite,:stockMax,:stockMin,:stockAlerte,:produit_pid)";
				 	SqlUpdate update = Ebean.createSqlUpdate(s);
				 	update.setParameter("quantite",quantite);
				 	update.setParameter("stockMin",minimum);
				 	update.setParameter("stockMax", maximum);
				 	update.setParameter("stockAlerte", seuilAlerte);
				 	update.setParameter("produit_pid", pidProduit);
				 	
				 	Ebean.execute(update);
				}
			}
		
		
		List<Produit> list_produit = StockGerant.getItem();
		List<StockResto> stockRestos = StockRestoQuery.getItem();
		return ok(gerant_stocks.render(list_produit, stockRestos));
	
	}
	
	public static boolean checkSession(){       
	        if (session("email")!= null){
	            return true;
	        }else{
	            return false;
	        }
	    }
	
}