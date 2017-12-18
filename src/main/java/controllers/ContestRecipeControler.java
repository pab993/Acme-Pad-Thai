package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestRecipeService;
import domain.ContestRecipe;

@Controller
@RequestMapping("/contestRecipe")
public class ContestRecipeControler extends AbstractController {

	@Autowired
	private ContestRecipeService contestRecipeService;
	
	public ContestRecipeControler(){
		super();
	}
	
	
	
	// List -------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam int contestId){
		
		ModelAndView resul = new ModelAndView("contestRecipe/list");
		Collection<ContestRecipe> contestRecipes = 
				contestRecipeService.findAllByContest(contestId);
		
		resul.addObject("contestRecipes", contestRecipes);
		resul.addObject("requestURI", "contestRecipe/list.do");
		
		return resul;
	}
	
	// Picture List -----------------------------
	
	@RequestMapping(value="picturesList", method=RequestMethod.GET)
	public ModelAndView picturesList(@RequestParam int contestRecipeId){
		
		ModelAndView result;
			
		ContestRecipe contestRecipe = contestRecipeService.findOne(contestRecipeId);
		
		Collection<String> images = new ArrayList<String>();
		images = contestRecipe.getPictures();
		
		result = new ModelAndView("contestRecipe/listPictures");
		result.addObject("images", images);
		result.addObject("requestURI", "contestRecipe/picturesList.do");
		
		return result;
	}
	
}
