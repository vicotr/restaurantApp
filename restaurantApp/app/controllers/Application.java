package controllers;

import static play.data.Form.form;

import java.util.List;

import models.Produit;
import models.queries.StockGerant;
import models.queries.userLog;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.stocks_gerant;

public class Application extends Controller {
	
	public static class Login {

	    public String email;
	    public String password;

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
		List<Produit> list_produit = StockGerant.getItem();
		
		return ok(stocks_gerant.render(list_produit));
	}

}