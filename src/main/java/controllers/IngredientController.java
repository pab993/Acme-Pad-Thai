package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Category;
import domain.Ingredient;
import domain.Recipe;
import domain.UnitSystem;
import domain.User;

import security.LoginService;
import services.IngredientService;
import services.RecipeService;
import services.UnitSystemService;
import services.UserService;

@Controller
@RequestMapping("/user/ingredients")
public class IngredientController extends AbstractController{
	
	//Services-----------------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private UnitSystemService unitSystemService;
	
	
	//Create... more like just add but whatever--------------------------
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add(@RequestParam int recipeId){
		
		ModelAndView result;
		Recipe recipe;
		
		
		UnitSystem unitSystem = unitSystemService.create();
		recipe = recipeService.findOne(recipeId);
		unitSystem.setRecipe(recipe);
		result = createEditModelAndView(unitSystem);
		
		return result;
	}
	
	//List---------------------------------------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam int recipeId){
		
		ModelAndView result;
		User user;
		Recipe recipe;
		Collection<Ingredient> ingredients = new ArrayList<Ingredient>();
		Collection<UnitSystem> unitSystems;
		
		user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
		recipe = recipeService.findOne(recipeId);
		unitSystems = recipe.getUnitSystems();
		for(UnitSystem us : unitSystems){
			ingredients.add(us.getIngredient());
		}
		result = new ModelAndView("ingredient/list");
		result.addObject("ingredient",ingredients);
		result.addObject("userVar",user);
		result.addObject("recipeFull", recipe);
		result.addObject("recipeId", recipeId);
		result.addObject("requestURI", "ingredient/list.do");
		
		return result;
	}
	
	@RequestMapping(value="/watch/list", method=RequestMethod.GET)
	public ModelAndView listNoAuth(@RequestParam int recipeId){
		
		ModelAndView result;
		Recipe recipe;
		Collection<Ingredient> ingredients = new ArrayList<Ingredient>();
		Collection<UnitSystem> unitSystems;
		
		recipe = recipeService.findOne(recipeId);
		unitSystems = recipe.getUnitSystems();
		for(UnitSystem us : unitSystems){
			ingredients.add(us.getIngredient());
		}
		result = new ModelAndView("ingredient/list");
		result.addObject("ingredient",ingredients);
		result.addObject("recipeId", recipeId);
		result.addObject("requestURI", "ingredient/list.do");
		
		return result;
	}
	
	//Edit-------------------------------------------------------------------------------
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UnitSystem unitSystem, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(unitSystem);
		} else {
			try {
				User user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
				if(unitSystem.getRecipe().getUser().getId() == user.getId()){
				String s = "redirect:/user/ingredients/list.do?recipeId=";
				s += unitSystem.getRecipe().getId();
				unitSystemService.save(unitSystem);
				result = new ModelAndView(s);
				}else{
					JOptionPane.showMessageDialog(null,"You can not edit that is not yours!");
					result = new ModelAndView("redirect:/ingredient/list.do");
					return result;
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(unitSystem,
						"user.commit.error");
			}
		}

		return result;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public ModelAndView remove(@RequestParam int ingredientId, @RequestParam int recipeId){
		
		ModelAndView result;
		String s = "redirect:/user/ingredients/list.do?recipeId=";
		s += recipeId;
		
		UnitSystem unitSystem = unitSystemService.unitSystemByRecipeAndIngredient(recipeId, ingredientId);
		unitSystemService.delete(unitSystem);
		result = createEditModelAndView(unitSystem); //Es necesario?
		result = new ModelAndView(s);
		
		return result;
	}
	
	// Ancillary methods ----------------------------------------------------------------

				protected ModelAndView createEditModelAndView(UnitSystem unitSystem) {
					ModelAndView result;
					
					result = createEditModelAndView(unitSystem, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(UnitSystem unitSystem,
						String message) {
					ModelAndView result;
					Collection<Ingredient> ingredients;
					
					ingredients = ingredientService.findAll();
					result = new ModelAndView("ingredient/add");
					result.addObject("unitSystem", unitSystem);
					result.addObject("ingredients", ingredients);
					result.addObject("recipeId", unitSystem.getRecipe().getId());
					result.addObject("message", message);

					return result;
				}
	

}