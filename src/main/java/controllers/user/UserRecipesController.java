package controllers.user;

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
import services.CategoryService;
import services.RecipeService;
import services.UserService;

import controllers.AbstractController;

import domain.Category;
import domain.Recipe;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserRecipesController extends AbstractController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	//Creation--------------------------------------------------------------------------
	

			@RequestMapping(value = "/recipes/create", method = RequestMethod.GET)
			public ModelAndView create() {
				ModelAndView result;
				Recipe recipe;
				User user;
				
				long l = 10;
				Date d = new Date(System.currentTimeMillis()- l);
				
				recipe = recipeService.create();
		
				user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
				
				recipe.setUser(user);
				recipe.setMomentAuthored(d);
				recipe.setMomentLastUpdate(d);
				result = createEditModelAndView(recipe);

				return result;
			}
	
	//LISTING ----------------------
	
			@RequestMapping(value="/recipes/list", method=RequestMethod.GET)
			public ModelAndView list(){
				
				ModelAndView result;
				User user;
				Collection<Recipe> recipes;
				
				user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
				recipes = recipeService.findByUser(user.getId());
				result = new ModelAndView("recipe/list");
				result.addObject("recipe",recipes);
				result.addObject("userVar",user);
				result.addObject("requestURI", "recipe/list.do");
				
				return result;
			}
			
	//Edit------------------------------------------------------------
			
			@RequestMapping(value="/recipes/edit", method=RequestMethod.GET)
			public ModelAndView edit(@RequestParam int recipeId){
				
				ModelAndView result;
				Recipe recipe;
				User user;
				
				recipe = recipeService.findOne(recipeId);
				Assert.notNull(recipe);
				user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
				if(user.getRecipes().contains(recipe)){
					result = createEditModelAndView(recipe);
					return result;
				}else{
					JOptionPane.showMessageDialog(null,"You can not edit that is not yours!");
					result = new ModelAndView("redirect:/recipe/list.do");
					return result;
				}
			}
			
			@RequestMapping(value = "/recipes/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid Recipe recipe, BindingResult binding) {
				ModelAndView result;
				
				if (binding.hasErrors()) {
					result = createEditModelAndView(recipe);
				} else {
					try {
						long l = 10;
						Date d = new Date(System.currentTimeMillis()- l);
						recipe.setMomentLastUpdate(d);
						recipeService.save(recipe);
						result = new ModelAndView("redirect:/user/recipes/list.do");
					} catch (Throwable oops) {
						result = createEditModelAndView(recipe,
								"user.commit.error");
					}
				}

				return result;
			}
	
			@RequestMapping(value = "recipes/edit", method = RequestMethod.POST, params = "delete")
			public ModelAndView delete(Recipe recipe, BindingResult binding) {
				ModelAndView result;

				try {
					recipeService.deleteRelations(recipe);
					result = new ModelAndView("redirect:/user/recipes/list.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(recipe, "user.commit.error");

				}
				return result;
			}
					
			
			// Ancillary methods ----------------------------------------------------------------

			protected ModelAndView createEditModelAndView(Recipe recipe) {
				ModelAndView result;
				
				result = createEditModelAndView(recipe, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(Recipe recipe,
					String message) {
				ModelAndView result;
				Collection<Category> categories;
				
				categories = categoryService.findAll();
				result = new ModelAndView("recipe/edit");
				result.addObject("categories", categories);
				result.addObject("recipe", recipe);
				result.addObject("message", message);

				return result;
			}
			
}
