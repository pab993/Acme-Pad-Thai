package controllers.sponsor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;

import controllers.AbstractController;
import domain.Sponsor;

@Controller
@RequestMapping("/register/sponsor")
public class RegisterSponsorController extends AbstractController{

	// Services-------------------------------------------------------------------------

			@Autowired
			private SponsorService sponsorService;
			
	//Constructor-----------------------------------------------------------------------
			
	//Creation--------------------------------------------------------------------------
			
			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create() {

				ModelAndView result;
				Sponsor sponsor = new Sponsor();

				sponsor = sponsorService.create();

				result = new ModelAndView("sponsor/register");
				result.addObject("sponsor", sponsor);

				return result;

			}
			
			//Edition------------------------------------------------------------------
			
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid Sponsor sponsor, BindingResult binding) {
				ModelAndView result;
				
				
				if (binding.hasErrors()) {
					result = createEditModelAndView(sponsor);
				} else {
					try {
						sponsorService.save(sponsor);
						result = new ModelAndView("redirect:/security/login.do");
					} catch (Throwable oops) {
						result = createEditModelAndView(sponsor,
								"sponsor.commit.error");
					}
				}

				return result;
			}

			// Ancillary methods ----------------------------------------------------------------

			protected ModelAndView createEditModelAndView(Sponsor sponsor) {
				ModelAndView result;

				result = createEditModelAndView(sponsor, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(Sponsor sponsor,
					String message) {
				ModelAndView result;
				result = new ModelAndView("sponsor/register");
				result.addObject("sponsor", sponsor);
				result.addObject("message", message);

				return result;
			}
	
}
