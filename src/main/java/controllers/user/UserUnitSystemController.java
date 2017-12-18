package controllers.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.RecipeService;
import services.UnitSystemService;
import services.UserService;

import controllers.AbstractController;
import domain.Category;
import domain.Recipe;
import domain.UnitSystem;
import domain.User;

@Controller
@RequestMapping("/user/unitSystem")
public class UserUnitSystemController extends AbstractController{
	
	//Services-----------------------------------------------------------
	
	@Autowired
	private UnitSystemService unitSystemService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
	
	//List----------------------------------------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam int ingredientId, @RequestParam int recipeId){
		
		ModelAndView result;
		UnitSystem unitSystem;
		
		unitSystem = unitSystemService.unitSystemByRecipeAndIngredient(recipeId, ingredientId);
		result = new ModelAndView("unitSystem/list");
		result.addObject("unitSystem",unitSystem);
		result.addObject("requestURI", "unitSystem/list.do");
		
		return result;
	}
	
	//Edit----------------------------------------------------------------
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int ingredientId, int recipeId){
		
		ModelAndView result;
		UnitSystem unitSystem;
		
		unitSystem = unitSystemService.unitSystemByRecipeAndIngredient(recipeId, ingredientId);
		Assert.notNull(unitSystem);
		User user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
		result = createEditModelAndView(unitSystem);
		return result;
		}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UnitSystem unitSystem, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(unitSystem);
		} else {
			try {
				String s = "redirect:/user/ingredients/list.do?recipeId=";
				int valor = unitSystem.getRecipe().getId();
				s += valor;
				unitSystemService.save(unitSystem);
				result = new ModelAndView(s);
			} catch (Throwable oops) {
				result = createEditModelAndView(unitSystem,
						"user.commit.error");
			}
		}

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
		Collection<String> units =new ArrayList<String>();
		units.add("grams");
		units.add("kilograms");
		units.add("ounces");
		units.add("pounds");
		units.add("millilitres");
		units.add("litres");
		units.add("spoons");
		units.add("cups");
		units.add("pieces");
		int recipeId = unitSystem.getRecipe().getId();
					
		result = new ModelAndView("unitSystem/edit");
		result.addObject("unitSystem", unitSystem);
		result.addObject("units", units);
		result.addObject("recipeId", recipeId);
		result.addObject("message", message);

		return result;
	}

}
