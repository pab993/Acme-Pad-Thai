package controllers;

import java.util.Collection;

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

import domain.Recipe;
import domain.Step;
import domain.User;

import security.LoginService;
import services.RecipeService;
import services.StepService;
import services.UserService;


@Controller
@RequestMapping("/recipe/steps")
public class StepController extends AbstractController{

	
	// Services-------------------------------------------------------------------------

		@Autowired
		private StepService stepService;
		
		@Autowired
		private RecipeService recipeService;
		
		@Autowired
		private UserService userService;
			
			
	//Constructor-----------------------------------------------------------------------
			
	//Creation--------------------------------------------------------------------------
		

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int recipeId) {
			ModelAndView result;
			Step step;
			Recipe recipe;
			
			recipe = recipeService.findOne(recipeId);
			User user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
			if(user.getRecipes().contains(recipe)){
				step = stepService.create();
				step.setRecipe(recipe);
				result = createEditModelAndView(step);
				return result;
			}else{
				JOptionPane.showMessageDialog(null,"You can not create that is not yours!");
				result = new ModelAndView("redirect:/user/recipes/list.do");
				return result;
			}
			
		}
		
	//listing--------------------------------------------------------------------------
		
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public ModelAndView stepsList(@RequestParam int recipeId){
			
			ModelAndView result;
			Recipe recipe;
			Collection<Step> steps;
				
			recipe = recipeService.findOne(recipeId);
			Assert.notNull(recipe);
			steps = recipe.getSteps();

			result = new ModelAndView("step/list");
			result.addObject("steps",steps);
			result.addObject("requestURI", "recipe/steps/list.do");
			
			return result;
		}


//Edition------------------------------------------------------------------

	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int stepId){
		
		ModelAndView result;
		Step step;
		
		
		step = stepService.findOne(stepId);
		Assert.notNull(step);
		User user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
		if(user.getRecipes().contains(step.getRecipe())){
			result = createEditModelAndView(step);
			return result;
		}else{
			JOptionPane.showMessageDialog(null,"You can not edit that is not yours!");
			result = new ModelAndView("redirect:/user/recipes/list.do");
			return result;
		}
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Step step, BindingResult binding) {
		ModelAndView result;
		Collection<Step> steps;
		steps = stepService.findAll();
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(step);
		} else {
			try {
				if(steps.contains(step)){
					stepService.save(step);
					result = new ModelAndView("redirect:/user/recipes/list.do");
				}else{
					Recipe recipe;
					recipe = step.getRecipe();
					recipe.getSteps().add(step);
					recipeService.save(recipe);
					result = new ModelAndView("redirect:/user/recipes/list.do");
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(step,
						"user.commit.error");
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Step step, BindingResult binding) {
		ModelAndView result;

		try {
			Recipe r = step.getRecipe();
			if(r.getSteps().size() == 1){
				JOptionPane.showMessageDialog(null,"A recipe can not have no steps!");
				result = new ModelAndView("redirect:/user/recipes/list.do");
			}else{
				r.getSteps().remove(step);
				recipeService.save(r);
				stepService.delete(step);
				result = new ModelAndView("redirect:/user/recipes/list.do");
			}
		} catch (Throwable oops) {
			result = createEditModelAndView(step, "user.commit.error");

		}
		return result;
	}
	
	// Ancillary methods ----------------------------------------------------------------

				protected ModelAndView createEditModelAndView(Step step) {
					ModelAndView result;
					
					result = createEditModelAndView(step, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(Step step,
						String message) {
					ModelAndView result;
					result = new ModelAndView("step/edit");
					result.addObject("step", step);
					result.addObject("message", message);

					return result;
				}
		
}
