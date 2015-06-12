package controllers;

import static play.data.Form.form;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import models.Demande;
import models.Etat;
import models.Fonction;
import models.Produit;
import models.StockResto;
import models.Utilisateur;
import models.queries.DemandeQuery;
import models.queries.EtatQuery;
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
import views.html.cuisinier_stocks;

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
        	List<Produit> produits = StockGerant.getItem();
    		List<StockResto> stockRestos = StockRestoQuery.getItem();
			return ok(gerant_demande_reapprovisionnement.render(produits,stockRestos));
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
        	List<StockResto> stockRestos = StockRestoQuery.getItem();
    		int alertes = 0;
    		for(int i = 0; i < stockRestos.size(); i++){
    			StockResto stockResto = stockRestos.get(i);
    			if(stockResto.quantite <= stockResto.stockAlerte){
    				alertes ++;
    			}
    		}
    		return ok(
				gerant_accueil.render(alertes)
				);
        }
	}
	
	public static Result gerantAlertes(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
        	List<StockResto> stockRestos = StockRestoQuery.getItem();
        	return ok(
				gerant_alertes.render(stockRestos)
				);
        }
	}
	
	public static Result gerantSuivi(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
        	List<Demande> demandes = DemandeQuery.getItem();
        	return ok(
				gerant_suivi_des_commandes.render(demandes)
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
	
	public static Result cuisinierStock(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<Produit> list_produit = StockGerant.getItem();
		List<StockResto> stockRestos = StockRestoQuery.getItem();
		return ok(cuisinier_stocks.render(list_produit, stockRestos));
        }
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
	
	public static Result traitementDemande(){
		DynamicForm demandeForm = Form.form().bindFromRequest();
		int nombreLignes = Integer.parseInt(demandeForm.get("nombreLignes"));
		String commentaires = demandeForm.get("commentaires");
		int compteur = 0;
		int compteur1 = 0;
		int did = 0;
		
		for(int i = 1; i < nombreLignes + 1; i++){
			String commande = demandeForm.get("hiddenDemande" + i);
			if(commande.equals("true")){
				if(compteur == 0){
					if(compteur1 == 0){
						
						Calendar calendrier = Calendar.getInstance();
						int day = calendrier.get(Calendar.DAY_OF_MONTH);
						int month = calendrier.get(Calendar.MONTH) + 1;
						int year = calendrier.get(Calendar.YEAR);
						
						String dateString = day + "/0" + month + "/" + year;
						
						
						
						List<Etat> etats = EtatQuery.getItem();
						
						for(int j = 0; j < etats.size(); j++){
							Etat etat = etats.get(j);
							if("envoyée".equals(etat.name)){
								String s = "INSERT INTO demande (commentaires,ETAT_ID,date) VALUES (:commentaires,:ETAT_ID,:date)";
							 	SqlUpdate update = Ebean.createSqlUpdate(s);
							 	update.setParameter("commentaires",commentaires);
							 	update.setParameter("ETAT_ID",etat.eid);
							 	update.setParameter("date", dateString);
							 
							 	Ebean.execute(update);
							 	compteur ++;
							}
						}
						
						if(compteur == 0){
							Etat etatBis = new Etat();
							etatBis.setName("envoyée");
							
							Demande demande = new Demande();
							demande.setCommentaires(commentaires);
							demande.setDate(dateString);
							demande.setEtat(etatBis);
							
							Ebean.save(etatBis);
							Ebean.save(demande);
						}

						List<Demande> demandes = DemandeQuery.getItem();
						did = demandes.get(demandes.size()-1).did;
						
						compteur1 ++;
					}
				}
				compteur ++;
				
				int pid = Integer.parseInt(demandeForm.get("pid" + i));
				int quantite = Integer.parseInt(demandeForm.get("quantite" + i));
				
				String s = "INSERT INTO produit_demande (quantite,produit_pid,demande_did) VALUES (:quantite,:produit_pid,:demande_did)";
			    SqlUpdate update = Ebean.createSqlUpdate(s);
			 	update.setParameter("quantite",quantite);
			 	update.setParameter("produit_pid", pid);
			 	update.setParameter("demande_did", did);
			 	
			 	Ebean.execute(update);
			}
		}

		return redirect("/gerant_demande_reapprovisionnement");
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
	
	public static Result supprimerStock(int srid){
		
		StockResto stock = Ebean.find(StockResto.class, srid);
		Ebean.delete(stock);
		
		return redirect("/gerant_stocks");
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