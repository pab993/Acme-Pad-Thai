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
@RequestMapping("/register/user")
public class RegisterUserController extends AbstractController{
	
	// Services-------------------------------------------------------------------------

		@Autowired
		private UserService userService;
		
		
	//Constructor-----------------------------------------------------------------------
		
	//Creation--------------------------------------------------------------------------
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {

			ModelAndView result;
			User user = new User();

			user = userService.create();
			result = createEditModelAndView(user);

			return result;

		}
		
		//Edition------------------------------------------------------------------
		
		
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
					userService.save(user);
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
			result = new ModelAndView("user/register");
			result.addObject("user", user);
			result.addObject("message", message);

			return result;
		}
		
	
}
