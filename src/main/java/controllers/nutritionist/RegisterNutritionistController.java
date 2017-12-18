package controllers.nutritionist;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.NutritionistService;

import controllers.AbstractController;
import domain.Nutritionist;

@Controller
@RequestMapping("/register/nutritionist")
public class RegisterNutritionistController extends AbstractController{

	// Services-------------------------------------------------------------------------

		@Autowired
		private NutritionistService nutritionistService;
			
	//Constructor-----------------------------------------------------------------------
			
	//Creation--------------------------------------------------------------------------
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {

			ModelAndView result;
			Nutritionist nutritionist = new Nutritionist();

			nutritionist = nutritionistService.create();

			result = new ModelAndView("nutritionist/register");
			result.addObject("nutritionist", nutritionist);

			return result;

		}
		
		//Edition------------------------------------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Nutritionist nutritionist, BindingResult binding) {
			ModelAndView result;
			
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(nutritionist);
			} else {
				try {
					nutritionistService.save(nutritionist);
					result = new ModelAndView("redirect:/security/login.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(nutritionist,
							"nutritionist.commit.error");
				}
			}

			return result;
		}

		// Ancillary methods ----------------------------------------------------------------

		protected ModelAndView createEditModelAndView(Nutritionist nutritionist) {
			ModelAndView result;

			result = createEditModelAndView(nutritionist, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Nutritionist nutritionist,
				String message) {
			ModelAndView result;
			result = new ModelAndView("nutritionist/register");
			result.addObject("nutritionist", nutritionist);
			result.addObject("message", message);

			return result;
		}
		
		
}
