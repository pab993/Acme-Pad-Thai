package controllers.nutritionist;

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
import services.BartolService;
import services.NutritionistService;
import services.RecipeService;
import domain.Bartol;
import domain.Curriculum;
import domain.Nutritionist;
import domain.Recipe;
import domain.Step;
import domain.User;

@Controller
@RequestMapping("/bartols/recipe")
public class BartolController {

	// Services ---------------------------------------
	
			@Autowired
			private RecipeService recipeService;
			
			@Autowired
			private NutritionistService nutritionistService;
			
			@Autowired
			private BartolService bartolService;
			
	// Listing ------------------------------------------------------------

			@RequestMapping(value = "/list", method = RequestMethod.GET)
			public ModelAndView list(@RequestParam int recipeId) {
				ModelAndView result;
				
				//Nutritionist nutri = nutritionistService.findByUserAccountId(LoginService.getPrincipal().getId());
				Recipe recipe = recipeService.findOne(recipeId);
				Collection<Bartol> bartols = recipe.getBartols();
				Collection<Bartol> newBartols = new ArrayList<Bartol>();
				long l = 10;
				Date d = new Date(System.currentTimeMillis()- l);
				for(Bartol b : bartols){
					if(b.getMomentDisplay().before(d)){
						newBartols.add(b);
					}
				}
				result = new ModelAndView("bartol/list");
				result.addObject("requestURI", "bartol/recipe/list.do");
				//result.addObject("nutriVar", nutri);
				result.addObject("bartols", newBartols);
				return result;
			}
			
			@RequestMapping(value = "/listNutri", method = RequestMethod.GET)
			public ModelAndView listNutri(@RequestParam int recipeId) {
				ModelAndView result;
				
				Nutritionist nutri = nutritionistService.findByUserAccountId(LoginService.getPrincipal().getId());
				Recipe recipe = recipeService.findOne(recipeId);
				Collection<Bartol> bartols = recipe.getBartols();
				Collection<Bartol> newBartols = new ArrayList<Bartol>();
				long l = 10;
				Date d = new Date(System.currentTimeMillis()- l);
				for(Bartol b : bartols){
					if(b.getMomentDisplay().before(d)){
						newBartols.add(b);
					}
				}
				result = new ModelAndView("bartol/list");
				result.addObject("requestURI", "bartol/recipe/list.do");
				result.addObject("nutriVar", nutri);
				result.addObject("bartols", newBartols);
				result.addObject("recipeId", recipeId);
				return result;
			}
			
			
	//Creating--------------------------------------------
			
			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create(@RequestParam int recipeId) {
				ModelAndView result;
				Recipe recipe;
				Bartol bartol;
				
				recipe = recipeService.findOne(recipeId);
				
				bartol = bartolService.create();
				bartol.setRecipe(recipe);
				result = createEditModelAndView(bartol);
				
				return result;
				
				
			}
			
			//Edition------------------------------------------------------------------

			@RequestMapping(value="/edit", method=RequestMethod.GET)
			public ModelAndView edit(@RequestParam int bartolId){
				
				ModelAndView result;
				Bartol bartol;
				
				
				bartol = bartolService.findOne(bartolId);
				Assert.notNull(bartol);
				Nutritionist nutri = nutritionistService.findByUserAccountId(LoginService.getPrincipal().getId());
				if(nutri.getBartols().contains(bartol)){
					result = createEditModelAndView(bartol);
					return result;
				}else{
					JOptionPane.showMessageDialog(null,"You can not edit that is not yours!");
					result = new ModelAndView("redirect:/bartols/recipe/listNutri.do?recipeId=" + bartol.getRecipe().getId());
					return result;
				}
			}
			
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid Bartol bartol, BindingResult binding) {
				ModelAndView result;
				Collection<Bartol> bartols;
				bartols = bartolService.findAll();
				
				if (binding.hasErrors()) {
					result = createEditModelAndView(bartol);
				} else {
					try {
						bartolService.save(bartol);
						//result = new ModelAndView("redirect:/bartols/recipe/listNutri.do?recipeId="+ bartol.getRecipe().getId());
						result = new ModelAndView("redirect:/bartols/recipe/listNutri.do?recipeId="+ bartol.getRecipe().getId());
						
					} catch (Throwable oops) {
						result = createEditModelAndView(bartol,
								"user.commit.error");
					}
				}

				return result;
			}
			
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
			public ModelAndView delete(Bartol bartol, BindingResult binding) {
				ModelAndView result;

				try {
						bartolService.delete(bartol);
						result = new ModelAndView("redirect:/bartols/recipe/listNutri.do");

				} catch (Throwable oops) {
					result = createEditModelAndView(bartol, "user.commit.error");

				}
				return result;
			}
			
			
			// Ancillary methods ----------------------------------------------------------------

			protected ModelAndView createEditModelAndView(Bartol bartol) {
				ModelAndView result;
				
				result = createEditModelAndView(bartol, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(Bartol bartol,
					String message) {
				ModelAndView result;
				result = new ModelAndView("bartol/edit");
				result.addObject("bartol", bartol);
				result.addObject("message", message);

				return result;
			}
}
