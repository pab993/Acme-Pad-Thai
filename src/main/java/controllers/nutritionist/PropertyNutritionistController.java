package controllers.nutritionist;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PropertyService;

import controllers.AbstractController;
import domain.Nutritionist;
import domain.Property;
@Controller
@RequestMapping("/property/nutritionist")
public class PropertyNutritionistController extends AbstractController {

	@Autowired
	private PropertyService propertyService;
	
	public PropertyNutritionistController(){
		super();
	}
	
	// List ------------------------------------------------------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView resul;
		
		Collection<Property> properties = propertyService.findAll();
		resul = new ModelAndView("property/list");
		resul.addObject("properties", properties);
		resul.addObject("requestURI", "property/nutritionist/list.do");
		
		return resul;
	}
	// Create ------------------------------------------------------------------------------
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView resul;
		
		resul = new ModelAndView("property/edit");
		Property property = propertyService.create();
		resul.addObject("property", property);
		
		return resul;
	}
	
	// Edit --------------------------------------------------------------------------------
	
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId){
		
		ModelAndView resul;
		
		Property property = propertyService.findOne(propertyId);
		resul = new ModelAndView("property/edit");
		resul.addObject("property", property);
		
		return resul;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Property property, BindingResult binding) {
		ModelAndView result;
		
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(property);
		} else {
			try {
				propertyService.save(property);
				result = new ModelAndView("redirect:/property/nutritionist/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(property,
						"property.commit.error");
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Property property, BindingResult binding) {
		ModelAndView result;
		
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(property);
		} else {
			try {
				propertyService.delete(property);
				result = new ModelAndView("redirect:/property/nutritionist/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(property,
						"property.commit.error");
			}
		}

		return result;
	}

	private ModelAndView createEditModelAndView(Property property, String message) {
		// TODO Auto-generated method stub
		ModelAndView resul;
		
		resul = new ModelAndView("property/edit");
		resul.addObject("property", property);
		
		resul.addObject("message", message);
		return resul;
	}

	private ModelAndView createEditModelAndView(Property property) {
		// TODO Auto-generated method stub
		return createEditModelAndView(property, null);
	}
	
	// Delete ------------------------------------------------------------------------------
}
