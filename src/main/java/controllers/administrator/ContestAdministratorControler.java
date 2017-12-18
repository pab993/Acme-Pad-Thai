package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import controllers.AbstractController;
import domain.Contest;

@Controller
@RequestMapping("/contest/administrator")
public class ContestAdministratorControler extends AbstractController {

	@Autowired
	ContestService contestService;
	
	public ContestAdministratorControler(){
		super();
	}
	// listing ---------------------------------------------------------------------------
	/*
	 * Ya está hecho en ContestController
	 */
	// Edit ------------------------------------------------------------------------------
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contestId){
		
		ModelAndView result = new ModelAndView("contest/edit");
		
		Contest contest = contestService.findOne(contestId);
		result.addObject("contest", contest);
		
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Contest contest, BindingResult binding){
		
		ModelAndView resul;
		
		if(binding.hasErrors()){
			
			resul = createEditModelAndView(contest);
		}
		else{
			try {
				contestService.save(contest);
				resul = new ModelAndView("redirect:/contest/list.do");
			} catch (Throwable oops) {
				resul = createEditModelAndView(
						contest, "contest.commit.error");
			}
		}
		return resul;
	}
	
	
	// Edit2 ------------------------------------------------------------------------------
	
	@RequestMapping(value="/edit2", method=RequestMethod.GET)
	public ModelAndView edit2(@RequestParam int contestId){
		
		ModelAndView result = new ModelAndView("contest/edit2");
		
		Contest contest = contestService.findOne(contestId);
		result.addObject("contest", contest);
		
		return result;
	}
	
	@RequestMapping(value="/edit2", method=RequestMethod.POST, params="save2")
	public ModelAndView save2(@Valid Contest contest, BindingResult binding){
		
		ModelAndView resul;
		
		if(binding.hasErrors()){
			
			resul = createEdit2ModelAndView(contest);
		}
		else{
			try {
				
				Contest contest2 = contestService.findOne(contest.getId());
				
				Assert.isTrue(!contest.getClosingTime().before(
						contest2.getClosingTime()));
				
				contestService.save(contest);
				resul = new ModelAndView("redirect:/contest/list.do");
			} catch (Throwable oops) {
				resul = createEdit2ModelAndView(
						contest, "contest.commit.error2");
			}
		}
		return resul;
	}

	@RequestMapping(value="/edit2", method=RequestMethod.POST, params="delete")
	public ModelAndView delete(@Valid Contest contest, BindingResult binding){
		
		ModelAndView resul;
		
		if(binding.hasErrors()){
			
			resul = createEdit2ModelAndView(contest);
		}
		else{
			try {
				contestService.delete(contest);
				resul = new ModelAndView("redirect:/contest/list.do");
			} catch (Throwable oops) {
				resul = createEdit2ModelAndView(
						contest, "contest.commit.error");
			}
		}
		return resul;
	}
	
	// Create ----------------------------------------------------------------------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView resul;
		
		Contest contest = contestService.create();
		
		resul = new ModelAndView("contest/edit");
		resul.addObject("contest", contest);
		
		return resul;
	}
	
	
	
	
	// Ancilliary methods -------------------------------------------------------
	
	
	protected ModelAndView createEditModelAndView(Contest contest, String message) {
		// TODO Auto-generated method stub
		
		ModelAndView result = new ModelAndView("contest/edit");
		result.addObject("contest", contest);
		
		result.addObject("message", message);
		
		return result;
	}
	protected ModelAndView createEditModelAndView(Contest contest) {
		// TODO Auto-generated method stub
		return createEditModelAndView(contest, null);
	}
	
	
	protected ModelAndView createEdit2ModelAndView(Contest contest, String message) {
		// TODO Auto-generated method stub
		
		ModelAndView result = new ModelAndView("contest/edit2");
		result.addObject("contest", contest);
		
		result.addObject("message", message);
		
		return result;
	}
	protected ModelAndView createEdit2ModelAndView(Contest contest) {
		// TODO Auto-generated method stub
		return createEdit2ModelAndView(contest, null);
	}
	
}
