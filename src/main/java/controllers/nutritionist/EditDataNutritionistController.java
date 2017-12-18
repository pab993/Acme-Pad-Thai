package controllers.nutritionist;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.NutritionistService;
import controllers.AbstractController;
import domain.Nutritionist;

@Controller
@RequestMapping("/nutritionist")
public class EditDataNutritionistController extends AbstractController {
	
	@Autowired
	private NutritionistService nutritionistService;
	
	
	public EditDataNutritionistController(){
		super();
	}
	
	// Create ----------------------------------------------------------------
	// Listing ---------------------------------------------------------------
	
	
	
	// Editing ---------------------------------------------------------------
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView result;
		Nutritionist nutritionist;
		
		nutritionist = nutritionistService.findOneByUserAccount(LoginService.getPrincipal());
		result = new ModelAndView("nutritionist/edit");
		result.addObject("nutritionist", nutritionist);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Nutritionist nutritionist, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(nutritionist);
		} else {
			try {
				nutritionistService.save(nutritionist);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(nutritionist,
						"user.commit.error");
			}
		}
		return result;
	}
	
	
	

	// Ancillary methods ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Nutritionist nutritionist, String message) {
			// TODO Auto-generated method stub
			ModelAndView result;
			
			result = new ModelAndView("nutritionist/edit");
			result.addObject("nutritionist", nutritionist );
			
			result.addObject("message", message);
			
			return result;
		}

		protected ModelAndView createEditModelAndView(Nutritionist nutritionist) {
			// TODO Auto-generated method stub
			return createEditModelAndView(nutritionist, null);
		}
		
		

		
		
		
		
		
	
}
