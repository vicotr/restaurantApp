package controllers;

import static play.data.Form.form;
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
	    if (loginForm.hasErrors()) {
	        return badRequest(login.render(loginForm));
	    } else {
	        session().clear();
	        session("email", loginForm.get().email);
	        return redirect(
	            routes.Application.index()
	        );
	    }
	}
	
	
	
	public static Result index(){
		return ok(
				stocks_gerant.render()
				);
	}

}