package controllers;

import static play.data.Form.form;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import models.Categorie;
import models.Demande;
import models.Etat;
import models.Fonction;
import models.Plat;
import models.PlatProduit;
import models.Produit;
import models.StockResto;
import models.Utilisateur;
import models.queries.CategorieQuery;
import models.queries.DemandeQuery;
import models.queries.EtatQuery;
import models.queries.FonctionQuery;
import models.queries.PlatProduitQuery;
import models.queries.PlatQuery;
import models.queries.ProductQuery;
import models.queries.StockRestoQuery;
import models.queries.UtilisateurQuery;
import models.queries.userLog;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cuisinier_accueil;
import views.html.cuisinier_alertes;
import views.html.cuisinier_plats;
import views.html.cuisinier_creation_plat;
import views.html.cuisinier_creation_platBis;
import views.html.cuisinier_plats_cuisines;
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
import views.html.serveur_accueil;
import views.html.*;
//import views.html.cuisinier_mon_compte;
//import views.html.serveur_alertes;


public class Application extends Controller {
	
	public static class Login {

	    public String email;
	    public String password;
	    public int fonction_fid;

	}
	
	public static class DemandeBis {
		public String commentaires;
	}
	
	
	// Méthodes get
	public static class NouvelUtilisateur{
		public String identifiant;
		public String password;
		public String role;
		public String mail;
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
        	List<Produit> produits = ProductQuery.getItem();
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
		List<Produit> list_produit = ProductQuery.getItem();
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
        	List<Produit> produits = ProductQuery.getItem();
        	return ok(
				gerant_alertes.render(stockRestos,produits)
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
        	List<Produit> list_produit = ProductQuery.getItem();
	        List<StockResto> stockRestos = StockRestoQuery.getItem();
	    	return ok(gerant_stocks.render(list_produit, stockRestos));
        }
	}
	// Maison-mère
	// ===================================================
	// ==================================================
	public static Result creationIngredient(){
		List<Categorie> categories = CategorieQuery.getItem();
		return ok(creationIngredient.render(categories));
	}
	
	// POST
	// =========================================
	
	public static Result creationIngredientTraitement(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		DynamicForm nouveauProduit = Form.form().bindFromRequest();
	    
	    String nom = nouveauProduit.get("nom");
	    //categorie
		String catidString = nouveauProduit.get("categorie"); // récupérer l'identifiant de la catégorie sélectionnée par l'utilisateur;
		String categoryName = "";
		// Nombre de catégories ajoutées;
		int lines = Integer.parseInt(nouveauProduit.get("categorieLignes"));
		int catid = 0;
		
		if(lines != 0){
			for(int i = 1; i < lines + 1; i++){
				categoryName = nouveauProduit.get("categorie"+i);
				
				Categorie categorie = new Categorie();
			    categorie.setCategoryName(categoryName);
			    
			    Ebean.save(categorie);
			}
		}
		
		if(!nom.equals("")){
			if(!catidString.equals("")){
				
				catid = Integer.parseInt(catidString);
				
				String s = "INSERT INTO produit (productName,categorie_catid) VALUES (:productName,:categorie_catid)";
			 	SqlUpdate update = Ebean.createSqlUpdate(s);
			 	update.setParameter("productName",nom);
			 	update.setParameter("categorie_catid",catid);
			 	
			 	Ebean.execute(update);
			 	
			}
		}
	    
	    return redirect("/creationIngredient");
        }
	}
	
	// Cuisinier
    //=====================================================
    //=====================================================
	
	// GET
	//====================================================
	
	public static Result cuisinierStock(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<Produit> list_produit = ProductQuery.getItem();
		List<StockResto> stockRestos = StockRestoQuery.getItem();
		return ok(cuisinier_stocks.render(list_produit, stockRestos));
        }
	}
	
	public static Result cuisinierAcceuil(){
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
				cuisinier_accueil.render(alertes)
				);
        }
	}
	
	public static Result listePlats(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<Plat> plats = PlatQuery.getItem();
		return ok(cuisinier_plats.render(plats));	
        }
	}
	
	public static Result formulairePlat(int plid){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<PlatProduit> platProduits = null;
		Plat plat = null;
		if(plid != 0){
			platProduits = PlatProduitQuery.getPlatProduitsBis(plid);
			plat = PlatQuery.getPlat(plid);
		}
		
		List<Categorie> categories = CategorieQuery.getItem();
		List<Produit> produits = ProductQuery.getItem();
		return ok(cuisinier_creation_plat.render(produits,categories,platProduits,plat));
        }
	}
	
	public static Result formulairePlatBis(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		
		List<Categorie> categories = CategorieQuery.getItem();
		List<Produit> produits = ProductQuery.getItem();
		return ok(cuisinier_creation_platBis.render(produits,categories));
        }
	}
	
	public static Result cuisinierAlertes(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
        	List<StockResto> stockRestos = StockRestoQuery.getItem();
        	List<Produit> produits = ProductQuery.getItem();
        	return ok(
				cuisinier_alertes.render(stockRestos,produits)
				);
        }
		
	}
	
	public static Result cuisinierPlatsCuisines(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		
			List<Plat> plats = PlatQuery.getItem();
			List<StockResto> stockRestos = StockRestoQuery.getItem();
			List<PlatProduit> platProduits = PlatProduitQuery.getItem();
			List<Produit> produits = ProductQuery.getItem();
			
			return ok(cuisinier_plats_cuisines.render(plats,platProduits,stockRestos,produits));
			
        }
	}
	
	public static Result cuisinierCompte(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				cuisinier_mon_compte.render()
				);
        }
	}
	
	// POST
	// =========================================================

		public static Result creationPlat(){
			
			boolean check = checkSession();
	        if (check== false){
	            return ok(
	                    login.render(form(Login.class)));
	        }else{
			
			DynamicForm nouveauPlat = Form.form().bindFromRequest();
			String platName = nouveauPlat.get("plat");
			int nombreLignes = Integer.parseInt(nouveauPlat.get("nombreLignes"));
			int plid = Integer.parseInt(nouveauPlat.get("plidPlat"));
			int pid = 0;
			int quantite = 0;
			String quantiteString = "";
			String unite = "";
			int compteur = 0;
			
			Plat plat = PlatQuery.getPlat(plid);
			
			plat.setPlatName(platName);

			Ebean.save(plat);
			
			for(int i = 1; i < nombreLignes + 1; i++){
				quantiteString = nouveauPlat.get("quantite"+i);
				if(quantiteString != null && !quantiteString.equals("")){
					unite = nouveauPlat.get("quantite"+i);
					if(!unite.equals("")){ 
						
						pid = Integer.parseInt(nouveauPlat.get("produit"+i)); // on récupère l'id du produit au format int;
						unite = nouveauPlat.get("unite"+i);
						quantite = Integer.parseInt(quantiteString);// on convertit quantiteString en nombre;
						
						String s = "INSERT INTO plat_produit (produit_pid,plat_plid,quantite,unite) VALUES (:produit_pid,:plat_plid,:quantite,:unite)";
					 	SqlUpdate update = Ebean.createSqlUpdate(s);
					 	update.setParameter("produit_pid",pid);
					 	update.setParameter("plat_plid",plid);
					 	update.setParameter("quantite",quantite);
					 	update.setParameter("unite",unite);
					 	
					 	Ebean.execute(update);
					}
				}
			}
			
			return redirect("../cuisinier_plat");
	        }
		}
		
		public static Result creationPlatBis(){
			boolean check = checkSession();
	        if (check== false){
	            return ok(
	                    login.render(form(Login.class)));
	        }else{
			
			DynamicForm nouveauPlat = Form.form().bindFromRequest();
			String platName = nouveauPlat.get("plat"); // On récupère le nom du plat;
			int nombreLignes = Integer.parseInt(nouveauPlat.get("nombreLignes"));// on récupère le nombre de lignes de la commande;
			int pid = 0;
			int quantite = 0;
			String quantiteString = "";
			String unite = "";
			int compteur = 0;
			Plat platCourant = null;
			
			for(int i = 1; i < nombreLignes + 1; i++){
				quantiteString = nouveauPlat.get("quantite"+i);// On récupère la quantité de l'élément de la ligne i au format chaîne de caractères;
				if(quantiteString != null && !quantiteString.equals("")){// Si la chaîne de caractères n'est pas vide, alors on récupère ;
					unite = nouveauPlat.get("quantite"+i);//  On récupère l'unité au format de chaîne de caractères;
					if(!unite.equals("")){ // Si l'unité n'est pas définie;
						if(compteur == 0){
							Plat plat = new Plat();// On crée un objet plat;
							plat.setPlatName(platName);// On stocke le nom du plat;
							
							Ebean.save(plat);
							
							List<Plat> plats = PlatQuery.getItem();
							platCourant = plats.get(plats.size() - 1); 
							
							compteur ++;
						}
						
						pid = Integer.parseInt(nouveauPlat.get("produit"+i)); // on récupère l'id du produit au format int;
						unite = nouveauPlat.get("unite"+i);
						quantite = Integer.parseInt(quantiteString);// on convertit quantiteString en nombre;
						
						String s = "INSERT INTO plat_produit (produit_pid,plat_plid,quantite,unite) VALUES (:produit_pid,:plat_plid,:quantite,:unite)";
					 	SqlUpdate update = Ebean.createSqlUpdate(s);
					 	update.setParameter("produit_pid",pid);
					 	update.setParameter("plat_plid",platCourant.plid);
					 	update.setParameter("quantite",quantite);
					 	update.setParameter("unite",unite);
					 	
					 	Ebean.execute(update);
					}
				}
			}
			
			return redirect("../cuisinier_plat");
	        }
		}
	
		public static Result supprimerIngredient(int plid, int pid){
			
			boolean check = checkSession();
	        if (check== false){
	            return ok(
	                    login.render(form(Login.class)));
	        }else{
	        	
		        List<PlatProduit> platProduitsBis = PlatProduitQuery.deletePlatProduits(pid);
										
				Ebean.delete(platProduitsBis);
				
				return formulairePlat(plid);
				
	        }
		}
		
		public static Result supprimerPlat(int plid){
			
			boolean check = checkSession();
	        if (check== false){
	            return ok(
	                    login.render(form(Login.class)));
	        }else{
			
	        List<PlatProduit> platProduits = PlatProduitQuery.getPlatProduitsBis(plid);

			Ebean.delete(platProduits);
		
			Plat plat = PlatQuery.getPlat(plid);
			Ebean.delete(plat);
			
			return redirect("../cuisinier_plat");
	        }
		}
	// ==========================================================================
	// ==========================================================================
	// ==========================================================================
	
	//Méthodes POST
	
	public static Result nouveauMembreTraitement() throws NoSuchAlgorithmException {
		
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		Form<NouvelUtilisateur> nouvelUtilisateurForm = form(NouvelUtilisateur.class).bindFromRequest();
		String pseudo = nouvelUtilisateurForm.get().identifiant;
		String mail = nouvelUtilisateurForm.get().mail;
		String password = nouvelUtilisateurForm.get().password;
		 
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        password = sb.toString();
        
		String role = nouvelUtilisateurForm.get().role;
		int fid = 0;
		
		if(role.equals("Gerant")){
			fid = 1;
		}else if(role.equals("Cuisinier")){
			fid = 2;
		}else if(role.equals("Serveur")){
			fid = 3;
		}
		
		if(!mail.equals("")){
			String s = "INSERT INTO utilisateur (pseudo,password,email,fonction_fid) VALUES (:pseudo,:password,:mail,:fid)";
			SqlUpdate update = Ebean.createSqlUpdate(s);
			update.setParameter("pseudo",pseudo);
			update.setParameter("password",password);
			update.setParameter("mail",mail );
			update.setParameter("fid",fid );
			 
			Ebean.execute(update);
		}
		
		return redirect("/gerant_administration");
        }
	}
	
	public static Result traitementDemande(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
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
	}
	
	public static Result authenticate() throws NoSuchAlgorithmException {
	    Form<Login> loginForm = form(Login.class).bindFromRequest();
	    String email = loginForm.get().email;
	    String password = loginForm.get().password;
	    int fid = 0;
	    
	   // Cryptege
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        password = sb.toString();
        //Fin cryptage
        
	    if (userLog.authen(email, password)==null) {
	        return badRequest(login.render(loginForm));
	    } else {
	    	
	        session().clear();
	        session("email", email);
	        session("password",password);
	        
	        fid = UtilisateurQuery.getfonction(email);
	        
		    if(fid==1){
		    	return redirect(routes.Application.gerantAccueil());
		    } else if (fid==2){
		      	return redirect(routes.Application.cuisinierAcceuil());
		    } else if (fid==3) {
		    	return redirect(routes.Application.serveurAccueil());
		    }else{
		    	 return badRequest(login.render(loginForm));
		    }
	    } 
	}

	public static Result supprimerMembre(int id){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		
		Utilisateur utilisateur = UtilisateurQuery.getUtilisateur(id);
		Ebean.delete(utilisateur);
		
		List<Fonction> newFonctions = FonctionQuery.getItem();
		List<Utilisateur> utilisateurs = UtilisateurQuery.getItem();
		return ok(gerant_administration.render(utilisateurs,newFonctions));
        }
	}
	
	public static Result supprimerStock(int srid){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
        StockResto stock = StockRestoQuery.getStockRestoSrid(srid);

		Ebean.delete(stock);
		
		return redirect("/gerant_stocks");
        }
	}
	
	public static Result serveurAccueil(){
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
				serveur_accueil.render(alertes)
				);
        }
	}
	
	public static Result serveurStock(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		List<Produit> list_produit = ProductQuery.getItem();
		List<StockResto> stockRestos = StockRestoQuery.getItem();
		return ok(serveur_stocks.render(list_produit, stockRestos));
        }
	}
	
	public static Result serveurAlertes(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
        	List<StockResto> stockRestos = StockRestoQuery.getItem();
        	List<Produit> produits = ProductQuery.getItem();
        	return ok(
				serveur_alertes.render(stockRestos,produits)
				);
        }
		
	}
	
	
	public static Result serveurCompte(){
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		return ok(
				serveur_mon_compte.render()
				);
        }
	}
	
	public static Result modificationStock(){
		
		boolean check = checkSession();
        if (check== false){
            return ok(
                    login.render(form(Login.class)));
        }else{
		
		DynamicForm stockForm = Form.form().bindFromRequest();
		int linesUpdated = Integer.parseInt(stockForm.get("linesUpdated"));
		int linesCreated = Integer.parseInt(stockForm.get("linesCreated"));
		int quantite = 0;
		int minimum = 0;
		int maximum = 0;
		int seuilAlerte = 0;
		int srid = 0;
		int pidProduit = 0;
		String unite = "";
		
		for(int i = 1; i < linesUpdated+1; i++){
			
			quantite = Integer.parseInt(stockForm.get("quantitelineUpdated"+i));
			minimum = Integer.parseInt(stockForm.get("minimumlineUpdated"+i));
			maximum = Integer.parseInt(stockForm.get("maximumlineUpdated"+i));
			seuilAlerte = Integer.parseInt(stockForm.get("alertelineUpdated"+i));
			unite = stockForm.get("unitelineCreated"+i);
			srid = Integer.parseInt(stockForm.get("hiddenlineUpdated"+i));
		    
			
			String s = "UPDATE stock_resto SET quantite = :quantite, stockMin = :stockMin, stockMax = :stockMax, stockAlerte = :stockAlerte, unite = :unite WHERE srid = :srid";
		    SqlUpdate update = Ebean.createSqlUpdate(s);
		 	update.setParameter("quantite",quantite);
		 	update.setParameter("stockMin",minimum);
		 	update.setParameter("stockMax", maximum);
		 	update.setParameter("stockAlerte", seuilAlerte);
		 	update.setParameter("unite", unite);
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
					unite = stockForm.get("unitelineCreated"+i);
					
					String s = "INSERT INTO stock_resto (quantite,stockMax,stockMin,stockAlerte,unite,produit_pid) VALUES (:quantite,:stockMax,:stockMin,:stockAlerte,:unite,:produit_pid)";
				 	SqlUpdate update = Ebean.createSqlUpdate(s);
				 	update.setParameter("quantite",quantite);
				 	update.setParameter("stockMin",minimum);
				 	update.setParameter("stockMax", maximum);
				 	update.setParameter("stockAlerte", seuilAlerte);
				 	update.setParameter("produit_pid", pidProduit);
				 	update.setParameter("unite", unite);
				 	
				 	Ebean.execute(update);
				}
			}
		
		
			return redirect("/gerant_stocks");
        }
	
	}
	
	
	public static boolean checkSession(){       
	        if (session("email")!= null){
	            return true;
	        }else{
	            return false;
	        }
	    }
	
}