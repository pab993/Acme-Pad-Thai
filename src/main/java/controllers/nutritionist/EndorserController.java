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

import controllers.AbstractController;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.AdministratorService;
import services.ConfigurationSystemService;
import services.CurriculumService;
import services.EndorserService;
import services.NutritionistService;
import services.UserService;
import domain.Curriculum;
import domain.Endorser;
import domain.Nutritionist;

@Controller
@RequestMapping("/endorser/nutritionist")
public class EndorserController extends AbstractController {

	// Services ---------------------------------------

	@Autowired
	private EndorserService endorserService;

	@Autowired
	private CurriculumService curriculumService;
	
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private NutritionistService nutritionistService;
	@Autowired
	private UserService userService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private ConfigurationSystemService configurationSystemService;
	
	// Constructors -----------------------------------------------------

	public EndorserController() {
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Endorser e;
		Nutritionist a=nutritionistService.findByUserAccountId(LoginService.getPrincipal().getId());
		e = endorserService.create();
		e.setCurriculum(a.getCurriculum());
		result = createEditModelAndView(e);

		return result;
	}
	// Listing ------------------------------------------------------------

	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam int curriculumId){
		
		ModelAndView result;
		
		Collection<Endorser> endorsers = endorserService.findAllByCurriculum(curriculumId);
		
		result = new ModelAndView("endorser/display");
		result.addObject("endorsers",endorsers);
		result.addObject("requestURI", "endorser/nutritionist/list.do");
		
		return result;
	}

	// Create methods---------------------------

	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int endorserId) {
		ModelAndView result;
		Endorser endorser;

		endorser = endorserService.findOne(endorserId);
		Assert.notNull(endorser);
		result = createEditModelAndView(endorser);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Endorser endorser, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(endorser);
		}else{
			try {
				endorserService.save(endorser);
				String s="redirect:display.do?curriculumId=";
				int valor=endorser.getCurriculum().getId();
				s+=valor;
				result = new ModelAndView(s);
			} catch (Throwable oops) {
				result = createEditModelAndView(endorser,
						"endorser.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Endorser endorser, BindingResult binding) {
		ModelAndView result;

		try {
			endorserService.delete(endorser);
			String s="redirect:display.do?curriculumId=";
			int valor=endorser.getCurriculum().getId();
			s+=valor;
			result = new ModelAndView(s);
		} catch (Throwable oops) {
			result = createEditModelAndView(endorser, "endorser.commit.error");

		}
		return result;
	}

	// Ancillary methods------------------------------

		protected ModelAndView createEditModelAndView(Endorser endorser) {
			ModelAndView result;

			result = createEditModelAndView(endorser, null);
			return result;
		}
			
		protected ModelAndView createEditModelAndView(Endorser endorser,
					String message) {
				ModelAndView result;
				Curriculum res;
				UserAccount uA=LoginService.getPrincipal();
				Nutritionist a=nutritionistService.findOneByUserAccountId(uA.getId());
				res=a.getCurriculum();
				result = new ModelAndView("endorser/edit");
				result.addObject("endorser", endorser);
				result.addObject("message", message);
				result.addObject("curricula", res);

				return result;
			}

		}


