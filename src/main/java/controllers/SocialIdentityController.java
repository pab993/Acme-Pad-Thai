package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.SocialIdentityService;
import domain.SocialIdentity;

@Controller
@RequestMapping("/socialIdentity")
public class SocialIdentityController extends AbstractController {

	@Autowired
	private SocialIdentityService socialIdentityService;
	
	// Listing --------------------------------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView listSocialIdentity(){
		
		ModelAndView result;
		Collection<SocialIdentity> socialIdentities;
		
		socialIdentities = socialIdentityService.findAllByPrincipal(
				LoginService.getPrincipal());
		result = new ModelAndView("socialIdentity/list");
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("requestURI", "socialIdentity/list.do");
		
		return result;
	}
	
	// Create ---------------------------------------------------------
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createSocialIdentity(){
		
		ModelAndView result;
		SocialIdentity socialIdentity = socialIdentityService.create();
		
		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		
		return result;
	}
	
	// Edit -----------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SocialIdentity socialIdentity, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentity);
		} else {
			try {
				socialIdentityService.save(socialIdentity);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(socialIdentity,
						"socialIdentity.commit.error");
			}
		}

		return result;
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(SocialIdentity socialIdentity, BindingResult binding) {
		
		ModelAndView resul;
		try {
			
			socialIdentityService.delete(socialIdentity);
			resul = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			resul = createEditModelAndView(
					socialIdentity, "socialIdentity.commit.error");
		}
		
		return resul;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int socialIdentityId){
		
		ModelAndView result;
		SocialIdentity socialIdentity;
		
		socialIdentity = socialIdentityService.findOne(socialIdentityId);
		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		
		return result;
	}
	
	// Ancillary methods
	
	private ModelAndView createEditModelAndView(SocialIdentity socialIdentity) {
		// TODO Auto-generated method stub
		return createEditModelAndView(socialIdentity, null);
	}

	private ModelAndView createEditModelAndView(SocialIdentity socialIdentity,
			String message) {
		// TODO Auto-generated method stub
		ModelAndView result;
		
		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		
		result.addObject("message", message);
		
		return result;
	}
	
}
