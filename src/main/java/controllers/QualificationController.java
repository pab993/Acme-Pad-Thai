package controllers;

import java.util.Collection;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Contest;
import domain.LikeDislike;
import domain.Qualification;
import domain.Recipe;
import domain.User;

import security.LoginService;
import services.ContestService;
import services.QualificationService;
import services.RecipeService;
import services.UserService;

@Controller
@RequestMapping("/qualification")
public class QualificationController extends AbstractController{
	
	//Services-----------------------------------------------------------------------
	
	@Autowired
	private QualificationService qualificationService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContestService contestService;
	
	//Edit---------------------------------------------------------------------------
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contestId){
		
		ModelAndView result;
		Qualification qualification;
		Contest contest;
		Date date = new Date(System.currentTimeMillis());
		contest = contestService.findOne(contestId);
		if(contest.getClosingTime().before(date)){
			JOptionPane.showMessageDialog(null,"The contest is closed");
			String s = "redirect:/contest/list.do";
			result = new ModelAndView(s);
		}
		qualification = qualificationService.create();
		qualification.setContest(contest);
		
		result = createEditModelAndView(qualification);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Qualification qualification, BindingResult binding) {
		ModelAndView result;
		Recipe recipe = recipeService.findOne(qualification.getRecipe().getId());
		int countLikes = 0;
		int countDislikes = 0;
		for(LikeDislike ld : recipe.getLikes()){
			if(ld.getLikeOrDislike() == true){
				countLikes = countLikes + 1;
			}else{
				countDislikes = countDislikes + 1;
			}
		}
		if(countLikes < 5 || countDislikes > 0){
			int value = qualification.getRecipe().getId();
			JOptionPane.showMessageDialog(null,"Your recipe has not enough likes or has a dislike");
			String s = "redirect:/user/recipes/list.do?recipeId=" + value;
			result = new ModelAndView(s);
			return result;
		}
		if (binding.hasErrors()) {
			result = createEditModelAndView(qualification);
		} else {
			try {
				int value = qualification.getRecipe().getId();
				String s = "redirect:/user/recipes/list.do?recipeId=" + value;
				qualificationService.save(qualification);
				result = new ModelAndView(s);
			} catch (Throwable oops) {
				result = createEditModelAndView(qualification,
						"user.commit.error");
			}
		}

		return result;
	}
	
	
	// Ancillary methods ----------------------------------------------------------------

				protected ModelAndView createEditModelAndView(Qualification qualification) {
					ModelAndView result;
					
					result = createEditModelAndView(qualification, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(Qualification qualification,
						String message) {
					ModelAndView result;
					
					User user;
					user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
					Collection<Recipe> recipes;
					recipes = recipeService.findByUser(user.getId());
					
					result = new ModelAndView("qualification/edit");
					result.addObject("qualification", qualification);
					result.addObject("recipes", recipes);
					result.addObject("message", message);

					return result;
				}
	

}
