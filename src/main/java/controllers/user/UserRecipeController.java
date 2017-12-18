package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.UserService;

import domain.Recipe;
import domain.User;

@Controller
@RequestMapping("/userRecipes")
public class UserRecipeController extends AbstractController{

	//Services-----------------------------
	
	@Autowired
	private UserService userService;
	
	//Constructor--------------------------
	
	//Listing------------------------------
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam int userId){
		
		ModelAndView result;
		User user;
		Collection<Recipe> recipes;
		
		user = userService.findOne(userId);
		recipes = user.getRecipes();
		result = new ModelAndView("recipe/list");
		result.addObject("recipe",recipes);
		result.addObject("requestURI", "recipe/list.do");
		
		return result;
	}
}
