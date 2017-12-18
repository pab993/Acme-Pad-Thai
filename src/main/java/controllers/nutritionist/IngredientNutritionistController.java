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
import services.IngredientService;
import services.PossesionService;
import services.PropertyService;

import controllers.AbstractController;
import domain.Category;
import domain.Ingredient;
import domain.Possesion;
import domain.Property;
import domain.Recipe;
import domain.UnitSystem;
import domain.User;

@Controller
@RequestMapping("/nutritionist/ingredient")
public class IngredientNutritionistController extends AbstractController{
	
	//Services----------------------------------------------------
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private PossesionService possesionService;
	
	//CREATE -----------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		
		Ingredient ingredient = ingredientService.create();
		result = createEditModelAndView(ingredient);

		return result;
	}
	
	@RequestMapping(value="/properties/add", method=RequestMethod.GET)
	public ModelAndView add(@RequestParam int ingredientId){
		
		ModelAndView result;
		Ingredient ingredient;
		
		
		Possesion possesion = possesionService.create();
		ingredient = ingredientService.findOne(ingredientId);
		possesion.setIngredient(ingredient);
		result = createEditModelAndView(possesion);
		
		return result;
	}
	
	
	//LISTING ----------------------
	
		@RequestMapping(value="/listAll", method=RequestMethod.GET)
		public ModelAndView list(){
			
			ModelAndView result;
			Collection<Ingredient> ingredients;
			
			ingredients = ingredientService.findAll();
			result = new ModelAndView("ingredient/listAll");
			result.addObject("ingredient",ingredients);
			result.addObject("requestURI", "nutritionist/ingredient/listAll.do");
			
			return result;
		}
		
		@RequestMapping(value="/properties/listOfProperties", method=RequestMethod.GET)
		public ModelAndView listOfProperties(@RequestParam int ingredientId){
			
			ModelAndView result;
			Ingredient ingredient;
			Collection<Property> properties = new ArrayList<Property>();
			
			ingredient = ingredientService.findOne(ingredientId);
			for(Possesion p :ingredient.getPossesions()){
				properties.add(p.getProperty());
			}
			result = new ModelAndView("property/listOfProperties");
			result.addObject("properties", properties);
			result.addObject("ingredientId", ingredientId);
			result.addObject("requestURI", "nutritionist/ingredient/properties/listOfProperties.do");
			
			return result;
		}
		
		
		//Edit-------------------------------------------------------------------------------
		
		@RequestMapping(value="/edit", method=RequestMethod.GET)
		public ModelAndView edit(@RequestParam int ingredientId){
			
			ModelAndView result;
			Ingredient ingredient;
			
			ingredient = ingredientService.findOne(ingredientId);
			Assert.notNull(ingredient);
			result = createEditModelAndView(ingredient);
			return result;
		}
		
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Ingredient ingredient, BindingResult binding) {
			ModelAndView result;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(ingredient);
			} else {
				try {
					ingredientService.save(ingredient);
					result = new ModelAndView("redirect:/nutritionist/ingredient/listAll.do");
					
				} catch (Throwable oops) {
					result = createEditModelAndView(ingredient,
							"user.commit.error");
				}
			}
			return result;
		}
		
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Ingredient ingredient, BindingResult binding) {
			ModelAndView result;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(ingredient);
			} else {
				try {
					if(ingredient.getUnitSystems().isEmpty()){
						ingredientService.delete(ingredient);
						result = new ModelAndView("redirect:/nutritionist/ingredient/listAll.do");
					}else{
						JOptionPane.showMessageDialog(null,"You can not delete this ingredient because it is being used");
						result = new ModelAndView("redirect:/nutritionist/ingredient/listAll.do");
						return result;
					}
					
				} catch (Throwable oops) {
					result = createEditModelAndView(ingredient,
							"user.commit.error");
				}
			}

			return result;
		}
		
		@RequestMapping(value="/properties/remove", method=RequestMethod.GET)
		public ModelAndView remove(@RequestParam int ingredientId, @RequestParam int propertyId){
			
			ModelAndView result;
			String s = "redirect:/nutritionist/ingredient/properties/listOfProperties.do?ingredientId=";
			s += ingredientId;
			
			Possesion possesion = possesionService.possesionByIngredientAndProperty(ingredientId, propertyId);
			possesionService.delete(possesion);
			//result = createEditModelAndView(unitSystem);
			result = new ModelAndView(s);
			
			return result;
		}
		
		
		@RequestMapping(value = "/properties/add", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Possesion possesion, BindingResult binding) {
			ModelAndView result;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(possesion);
			} else {
				try {
					String s = "redirect:/nutritionist/ingredient/properties/listOfProperties.do?ingredientId=";
					s += possesion.getIngredient().getId();
					possesionService.save(possesion);
					result = new ModelAndView(s);
				} catch (Throwable oops) {
					result = createEditModelAndView(possesion,
							"user.commit.error");
				}
			}

			return result;
		}
		
		
		// Ancillary methods ----------------------------------------------------------------

		protected ModelAndView createEditModelAndView(Ingredient ingredient) {
			ModelAndView result;
						
			result = createEditModelAndView(ingredient, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Ingredient ingredient,
				String message) {
			ModelAndView result;
						
			result = new ModelAndView("ingredient/edit");
			result.addObject("ingredient", ingredient);
			result.addObject("message", message);

			return result;
		}
		
		
		
		
		
		protected ModelAndView createEditModelAndView(Possesion possesion) {
			ModelAndView result;
						
			result = createEditModelAndView(possesion, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Possesion possesion,
				String message) {
			ModelAndView result;
						
			Collection<Property> properties;
			
			properties = propertyService.findAll();
			result = new ModelAndView("property/add");
			result.addObject("possesion", possesion);
			result.addObject("properties", properties);
			result.addObject("ingredientId", possesion.getIngredient().getId());
			result.addObject("message", message);

			return result;
		}

}
