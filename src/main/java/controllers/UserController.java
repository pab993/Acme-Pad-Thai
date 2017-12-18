package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;
	
	public UserController(){
		super();
	}
	
	//LISTING ----------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView result;
		Collection<User> users;
		
		users = userService.findAll();
		result = new ModelAndView("user/list");
		result.addObject("users",users);
		result.addObject("requestURI", "user/list.do");
		
		return result;
	}
	
	
	
	
	
	
	//EDIT -------------------
	
	
	
	//CREATE --------------------
	//DISPLAY--------------------
	
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView displayAuthor(@RequestParam int recipeId){
		
		ModelAndView result;
		User user;
		Collection<User> users = new ArrayList<User>();
		
		user = userService.searchAuthor(recipeId);
		users.add(user);
		result = new ModelAndView("user/list");
		
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");
		
		
		
		return result;
	}
	
	//SEARCH--------------------------------------------------
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@RequestParam(value = "keyword") String word) {
		ModelAndView result;
		Collection<User> users;

		users = userService.UserSearch(word);
		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");

		return result;
	}
}
