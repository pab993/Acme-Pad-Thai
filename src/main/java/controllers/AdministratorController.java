/* AdministratorController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.BillService;
import services.CampaignService;
import services.ContestRecipeService;
import services.ContestService;
import services.IngredientService;
import services.RecipeService;
import services.SponsorService;
import services.StepService;
import services.UserService;

import domain.Contest;
import domain.ContestRecipe;
import domain.Recipe;
import domain.Sponsor;
import domain.User;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private StepService stepService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private ContestRecipeService contestRecipeService;
	
	@Autowired
	private IngredientService ingredientService;
	

	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;
				
		result = new ModelAndView("administrator/action-1");
		
		return result;
	}
	
	// Action-2 ---------------------------------------------------------------
	
	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;
				
		result = new ModelAndView("administrator/action-2");
		
		return result;
	}
	
	//DashBoard------------------------------------------------------------------
	
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView result;
		Double avgStep = 0.0;
		Double sdStep = 0.0;
		Double avgIngredient = 0.0;
		Double sdIngredient = 0.0;
		Integer maxRecipe = 0;
		Integer minRecipe = 0;
		Double avgRecipe = 0.0;
		Integer maxRecipesPerContest = 0;
		Integer minRecipesPerContest = 0;
		Double avgRecipesPerContest = 0.0;
		Collection<User> moreAuthoreds;
		Collection<Contest> contests;
		if(userService.findAll().isEmpty()){
			
		}else{
			maxRecipe = recipeService.MaximumNumberRecipesPerUser();
			minRecipe = recipeService.MinimumNumberRecipesPerUser();
			avgRecipe = recipeService.AverageNumberRecipesPerUser();
		}
		if(contestService.findAll().isEmpty()){
			
		}else{
			maxRecipesPerContest = contestService.maximumNumberRecipesQualified();
			minRecipesPerContest = contestService.minimumNumberRecipesQualified();
			avgRecipesPerContest = contestService.averageNumberRecipesQualified();
		}
		if(userService.findAll().isEmpty()){
			moreAuthoreds = new ArrayList<User>();
		}else{
			moreAuthoreds = userService.userWhoHaveAuthoredMoreRecipes();
		}
		if(contestService.findAll().isEmpty()){
			contests = new ArrayList<Contest>();
		}else{
			contests = contestService.contestWithMoreRecipesQualified();
		}
		
		if(recipeService.findAll().isEmpty()){
			avgStep = 0.0;
		}else{
			avgStep = stepService.averageStepsPerRecipe();
		}
		if(recipeService.findAll().isEmpty()){
			sdStep = 0.0;
		}else{
			sdStep = stepService.standarDeviationStepsPerRecipe();
		}
		if(recipeService.findAll().isEmpty()){
			avgIngredient = 0.0;
		}else{
			avgIngredient = stepService.averageStepsPerRecipe();
		}
		if(recipeService.findAll().isEmpty()){
			sdIngredient = 0.0;
		}else{
			sdIngredient = ingredientService.standarDeviationIngredientsPerRecipe();
		}
		Collection<User> popularUsers = userService.UsersMorePopulars();
		Collection<User> likeUsers = userService.UsersRegardingAverageLikesAndDislikes();
		
//		Integer maxCampaign = campaignService.maxCampaign();
//		Integer minCampaign = campaignService.minCampaign();
//		Double avgCampaign = campaignService.avgCampaign();
//		Integer maxACampaign = campaignService.maxActiveCampaign();
//		Integer minACampaign = campaignService.minActiveCampaign();
//		Double avgACampaign = campaignService.avgActiveCampaign();
//		Collection<String> rankingCompanies = sponsorService.rankingOfCompaniesByCampaigns();
//		Collection<String> rankingBCompanies = sponsorService.rankingOfCompaniesByBills();
//		Double avgPaidBill = billService.avgPaidBills();
//		Double avgUnpaidBill = billService.avgUnPaidBills();
//		Double sdPaidBill = billService.stddevPaidBills();
//		Double sdUnpaidBill = billService.stddevUnPaidBills();
//		Collection<Sponsor> inactiveSponsors = sponsorService.inactiveSponsors();
//		Collection<String> lessThanAvgSponsor = sponsorService.companiesThatHaveSpentLessThanTheAverage();
//		Collection<String> ninePerCent = sponsorService.companiesNinetyPerCent();
		
		result = new ModelAndView("administrator/display");
		result.addObject("maxRecipes", maxRecipe);
		result.addObject("minRecipes", minRecipe);
		result.addObject("avgRecipes", avgRecipe);
		result.addObject("moreAuthoreds", moreAuthoreds);
		result.addObject("maxRecipesPerContest", maxRecipesPerContest);
		result.addObject("minRecipesPerContest", minRecipesPerContest);
		result.addObject("avgRecipesPerContest", avgRecipesPerContest);
		result.addObject("ContestWithMore", contests);
		result.addObject("avgSteps", avgStep);
		result.addObject("sdSteps", sdStep);
		result.addObject("avgIngredient", avgIngredient);
		result.addObject("sdIngredient", sdIngredient);
		result.addObject("popularUsers", popularUsers);
		result.addObject("likeUsers", likeUsers);
//		result.addObject("maxCampaigns", maxCampaign);
//		result.addObject("minCampaigns", minCampaign);
//		result.addObject("avgCampaigns", avgCampaign);
//		result.addObject("maxACampaigns", maxACampaign);
//		result.addObject("minACampaigns", minACampaign);
//		result.addObject("avgACampaigns", avgACampaign);
//		result.addObject("rankingCompanies", rankingCompanies);
//		result.addObject("rankingBCompanies", rankingBCompanies);
//		result.addObject("avgPaidBills", avgPaidBill);
//		result.addObject("avgUnpaidBills", avgUnpaidBill);
//		result.addObject("sdPaidBills", sdPaidBill);
//		result.addObject("sdUnpaidBills", sdUnpaidBill);
//		result.addObject("inactiveSponsors", inactiveSponsors);
//		result.addObject("lessThanAvgSponsor", lessThanAvgSponsor);
//		result.addObject("ninePerCent", ninePerCent);
		result.addObject("requestURI", "administrator/display.do");
		
		return result;
	}
	
	@RequestMapping(value="/selectWinners", method=RequestMethod.GET)
	public ModelAndView selectWinners(){
		
		ModelAndView result;
		
		result = new ModelAndView("administrator/winnerProcess");
		
		return result;
	}
	
	//Selection of winners
	
	
	@RequestMapping(value="/calculateWinners", method=RequestMethod.GET)
	public ModelAndView winnerProcess(){
		
		ModelAndView result;
		Collection<Contest> closedContests;
		
		closedContests = contestService.findClosedContests();
		if(!closedContests.isEmpty()){
			for(Contest c : closedContests){
				int contestRecipeIdPrimero = 0;
				int restMax = -1000000000;
				Collection<ContestRecipe> copyContestRecipes = c.getContestRecipes();
				if(copyContestRecipes.isEmpty()){
				
				}else{
					for(ContestRecipe cr : copyContestRecipes){
						int restValue = contestRecipeService.findRestByContestRecipeId(cr.getId());
						if(restMax < restValue){
							restMax = restValue;
							contestRecipeIdPrimero = cr.getId();
							
						}
					}
					int contestRecipeIdSegundo = 0;
					int restSegundo = -1000000000;
					ContestRecipe primero = contestRecipeService.findOne(contestRecipeIdPrimero);
					copyContestRecipes.remove(primero);
					if(copyContestRecipes.isEmpty()){
						
					}else{
						for(ContestRecipe cr : copyContestRecipes){
							int restValue = contestRecipeService.findRestByContestRecipeId(cr.getId());
							if(restSegundo < restValue){
								restSegundo = restValue;
								contestRecipeIdSegundo = cr.getId();
							}
						}
						int contestRecipeIdTercero = 0;
						int restTercero = 0;
						ContestRecipe segundo = contestRecipeService.findOne(contestRecipeIdSegundo);
						copyContestRecipes.remove(segundo);
						if(copyContestRecipes.isEmpty()){
							
						}else{
							for(ContestRecipe cr : copyContestRecipes){
								int restValue = contestRecipeService.findRestByContestRecipeId(cr.getId());
								if(restTercero < restValue){
									restTercero = restValue;
									contestRecipeIdTercero = cr.getId();
								}
								
							}
							
							ContestRecipe w3 = contestRecipeService.findOne(contestRecipeIdTercero);
							w3.setWinner(true);
							contestRecipeService.save(w3);
						
						}
						
						ContestRecipe w2 = contestRecipeService.findOne(contestRecipeIdSegundo);
						w2.setWinner(true);
						contestRecipeService.save(w2);
					}
					
					ContestRecipe w1 = contestRecipeService.findOne(contestRecipeIdPrimero);
					w1.setWinner(true);
					contestRecipeService.save(w1);
				}
			}
			
			
		}
		result = new ModelAndView("redirect:/contest/list.do");
		return result;
	
	}
	
}