package controllers;

import java.util.Collection;
import java.util.Date;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ContestService;
import services.RecipeService;
import services.UserService;
import domain.Contest;
import domain.Recipe;
import domain.User;

@Controller
@RequestMapping("/contest")
public class ContestController extends AbstractController {

	//Services--------------------------------------------------
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RecipeService recipeService;
	
	public ContestController(){
		super();
	}
	
	//listing---------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView result;
		Collection<Contest> contests;
		Date date = new Date(System.currentTimeMillis());
		
		contests = contestService.findAll();
		result = new ModelAndView("contest/list");
		result.addObject("contests", contests);
		result.addObject("date", date);
		result.addObject("requestURI", "contest/list.do");
		
		return result;
	}
		
	//editing------------------------------------
		
//		@RequestMapping(value="/register", method=RequestMethod.GET)
//		public ModelAndView register(@RequestParam int contestId){
//			
//			ModelAndView result;
//			Contest contest;
//			long l = 10;
//			Date d = new Date(System.currentTimeMillis()- l);
//			
//			contest = contestService.findOne(contestId);
//			if(contest.getClosingTime().before(d)){
//				Assert.notNull(contest);
//				result = createEditModelAndView(contest);
//				return result;
//			}else{
//				JOptionPane.showMessageDialog(null,"The contest is over");
//				result = new ModelAndView("redirect:/contest/list.do");
//				return result;
//			}
//			
//		}
//		
//		// Ancillary methods ----------------------------------------------------------------
//
//					protected ModelAndView createEditModelAndView(Contest contest) {
//						ModelAndView result;
//						
//						result = createEditModelAndView(contest, null);
//
//						return result;
//					}
//
//					protected ModelAndView createEditModelAndView(Contest contest,
//							String message) {
//						ModelAndView result;
//						Collection<Recipe> recipes;
//						User user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
//						
//						recipes = recipeService.findByUser(user.getId());
//						RecipeContestForm recipeContestForm = new RecipeContestForm();
//						recipeContestForm.setRecipes(recipes);
//						recipeContestForm.setContest(contest);
//						result = new ModelAndView("contest/qualify");
//						result.addObject("recipeContestForm", recipeContestForm);
//						result.addObject("recipes", recipes);
//						result.addObject("contest", contest);
//						result.addObject("message", message);
//
//						return result;
//					}

}
