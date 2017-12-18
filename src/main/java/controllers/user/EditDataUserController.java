package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.UserService;

import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/user")
public class EditDataUserController extends AbstractController{
	
	// Services-------------------------------------------------------------------------

			@Autowired
			private UserService userService;
			
			
		//Constructor-----------------------------------------------------------------------
			
			@RequestMapping(value="/edit", method=RequestMethod.GET)
			public ModelAndView edit(){
				
				ModelAndView result;
				User user;
				
				
				user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
				Assert.notNull(user);
				result = createEditModelAndView(user);
				
				return result;
			}
			
			
			
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid User user, BindingResult binding) {
				ModelAndView result;
				
				
				if (binding.hasErrors()) {
					result = createEditModelAndView(user);
				} else {
					try {
						userService.saveAgain(user);
						result = new ModelAndView("redirect:/welcome/index.jsp");
					} catch (Throwable oops) {
						result = createEditModelAndView(user,
								"user.commit.error");
					}
				}

				return result;
			}

			// Ancillary methods ----------------------------------------------------------------

			protected ModelAndView createEditModelAndView(User user) {
				ModelAndView result;
				
				result = createEditModelAndView(user, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(User user,
					String message) {
				ModelAndView result;
				result = new ModelAndView("user/edit");
				result.addObject("user", user);
				result.addObject("message", message);

				return result;
			}

}
