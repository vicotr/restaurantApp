package controllers;

import java.util.List;

import models.Produit;
import models.queries.StockGerant;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.stocks_gerant;

public class stockgerant extends Controller {
	public static Result getColumn() {
		List<Produit> list_produit = StockGerant.getItem();
		//return ok();
		return ok(stocks_gerant.render(list_produit));
	
	}

}
