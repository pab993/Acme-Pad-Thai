	package controllers.nutritionist;

	import java.util.Collection;

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
import security.UserAccount;
import services.EndorserService;
	import services.NutritionistService;
	import services.CurriculumService;
import domain.Endorser;
	import domain.Nutritionist;
import domain.Curriculum;

	@Controller
	@RequestMapping("/curriculum/nutritionist")
	public class CurriculumNutritionistController {

		// Services ---------------------------------------

		@Autowired
		private CurriculumService curriculumService;
		@Autowired
		private NutritionistService nutritionistService;
		@Autowired
		private EndorserService endorserService;
		// Constructors -----------------------------------------------------

		public CurriculumNutritionistController() {
			super();
		}

		// Listing ------------------------------------------------------------

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;

			Curriculum curricula;
			
			Nutritionist nutritionist = nutritionistService.findOneByUserAccountId(LoginService.getPrincipal().getId());
			curricula = nutritionist.getCurriculum();

			result = new ModelAndView("curriculum/list");
			result.addObject("requestURI", "curriculum/nutritionist/list.do");
			result.addObject("curricula", curricula);
			return result;
		}

		// Create methods---------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Curriculum curriculum;
			
			curriculum = curriculumService.create();
			result = createEditModelAndView(curriculum);

			return result;
		}

		// Edition------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit() {
			ModelAndView result;
			Curriculum curriculum;
			Nutritionist n=nutritionistService.findOneByUserAccountId(LoginService.getPrincipal().getId());
			curriculum = n.getCurriculum();
			Assert.notNull(curriculum);
			result = createEditModelAndView(curriculum);

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Curriculum curriculum, BindingResult binding) {
			ModelAndView result;
			if (binding.hasErrors()) {
			result = createEditModelAndView(curriculum);
			} else {
				try {
					curriculumService.save(curriculum);
					
					result = new ModelAndView("redirect:list.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(curriculum,
							"curriculum.commit.error");
				}
			}
			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(Curriculum curriculum, BindingResult binding) {
			ModelAndView result;
			if (binding.hasErrors()) {
				result = createEditModelAndView(curriculum);
			}
			try {
				curriculumService.delete(curriculum);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(curriculum, "curriculum.commit.error");
				//int x = 0;

			}
			return result;
		}

		// Ancillary methods------------------------------

		protected ModelAndView createEditModelAndView(Curriculum curriculum) {
			ModelAndView result;

			result = createEditModelAndView(curriculum, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(Curriculum curriculum,
				String message) {
			ModelAndView result;

			result = new ModelAndView("curriculum/edit");
			result.addObject("curriculum", curriculum);
			result.addObject("message", message);

			return result;
		}

	}



