package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;


import security.LoginService;
import services.ActorService;
import services.CustomerService;
import services.RecipeService;
import services.UserService;

import domain.Actor;
import domain.Customer;
import domain.Recipe;
import domain.User;



@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController{
	
	//Services------------------------------
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActorService actorService;
	
	
	//Constructor---------------------------
	
	public RecipeController(){
		super();
	}
	
	//listing---------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView result;
		Collection<Recipe> recipes;

		recipes = recipeService.ShowRecipesByCategory();
//		User user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
		Actor actor = actorService.findByActorAccountId(LoginService.getPrincipal().getId());
		result = new ModelAndView("recipe/list");
		result.addObject("recipe",recipes);
		result.addObject("userVar", actor);
		result.addObject("requestURI", "recipe/list.do");
		
		return result;
	}
	
	@RequestMapping(value="/adminList", method=RequestMethod.GET)
	public ModelAndView adminList(){
		
		ModelAndView result;
		Collection<Recipe> recipes;

		recipes = recipeService.ShowRecipesByCategory();
		result = new ModelAndView("recipe/adminList");
		result.addObject("recipe",recipes);
		result.addObject("requestURI", "recipe/adminList.do");
		
		return result;
	}
	
	@RequestMapping(value="/watch/list", method=RequestMethod.GET)
	public ModelAndView listSinAuth(){
		
		ModelAndView result;
		Collection<Recipe> recipes;

		recipes = recipeService.ShowRecipesByCategory();
		result = new ModelAndView("recipe/list");
		result.addObject("recipe",recipes);
		result.addObject("requestURI", "recipe/watch/list.do");
		
		return result;
	}
	
	@RequestMapping(value="/picturesList", method=RequestMethod.GET)
	public ModelAndView picturesList(@RequestParam int recipeId){
		
		ModelAndView result;
		Recipe recipe;
			
		recipe = recipeService.findOne(recipeId);
		result = new ModelAndView("recipe/picturesList");
		result.addObject("recipe",recipe);
		result.addObject("requestURI", "recipe/picturesList.do");
		
		return result;
	}
	
	@RequestMapping(value="/lastUpdatesList", method=RequestMethod.GET)
	public ModelAndView lasUpdatesList(){
		
		ModelAndView result;
		Collection<Recipe> recipes = new ArrayList<Recipe>();
		Customer customer;
		Collection<Customer> customers;
		
		customer = customerService.findOneByuserAccountId(LoginService.getPrincipal().getId());
		
		customers = customerService.findFollowedsByCustomerId(customer.getId());
//		for(Customer c : customers){
//			Collection<Recipe> r = recipeService.findRecentRecipes(c.getId());
//			for(Recipe r1 : r){
//				recipes.add(r1);
//			}
//		}
		ArrayList<Recipe> recipesTotalAbsoluto = new ArrayList<Recipe>();
		for(Customer c: customers){
			ArrayList<Recipe> recipesTop5 = new ArrayList<Recipe>();
			Collection<Recipe> recipesTotal = recipeService.findRecentRecipes(c.getId());
			for(Recipe r1 : recipesTotal){
				if(recipesTop5.size() < 5){
					recipesTop5.add(r1);
				}
			}
			for(Recipe r2 : recipesTop5){
				recipesTotalAbsoluto.add(r2);
			}
		}
		
		result = new ModelAndView("recipe/lastUpdatesList");
		result.addObject("recipe",recipesTotalAbsoluto);
		result.addObject("requestURI", "recipe/lastUpdatesList.do");
		
		return result;
	}
	
	
	
	//Search-----------------------------------------------------------------
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@RequestParam(value = "keyword") String word) {
		ModelAndView result;
		Collection<Recipe> recipes;

		recipes = recipeService.RecipeSearch(word);
		result = new ModelAndView("recipe/list");
		result.addObject("recipe", recipes);
		result.addObject("requestURI", "recipe/list.do");

		return result;
	}
	
	// Display recipes in a contest
	
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView displayAuthor(@RequestParam int contestId){
		
		ModelAndView result;
		Collection<Recipe> recipes;
		
		recipes = recipeService.findByContest(contestId);
		result = new ModelAndView("recipe/display");
		result.addObject("recipes", recipes);
		result.addObject("requestURI", "recipe/display.do");
		
		return result;
	}
	
	//Edition------------------------------------------------------------------

	

}
