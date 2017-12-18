package controllers;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Customer;
import domain.Recipe;

import security.LoginService;
import services.CommentService;
import services.CustomerService;
import services.RecipeService;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController{
	
	//Services------------------------------
	
		@Autowired
		private RecipeService recipeService;
		
		@Autowired
		private CommentService commentService;
		
		@Autowired
		private CustomerService customerService;
		
		
	//listing---------------------------------
		
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public ModelAndView commentList(@RequestParam int recipeId){
			
			ModelAndView result;
			Collection<Comment>comments;
				
			comments = commentService.findCommentsByRecipe(recipeId);
			result = new ModelAndView("comment/list");
			result.addObject("comment",comments);
			result.addObject("recipeId", recipeId);
			result.addObject("requestURI", "comment/list.do");
			
			return result;
		}
		
	//Editing-----------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Comment comment, BindingResult binding) {
			ModelAndView result;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(comment);
			} else {
				try {
					String direccion= "redirect:/comment/list.do?recipeId=";
					int valor = comment.getRecipe();
					direccion += valor;
					long l = 10;
					Date d = new Date(System.currentTimeMillis()- l);
					int id = comment.getRecipe();
					comment.setMomentOfCreation(d);
					Recipe recipe = recipeService.findOne(id);
					recipe.getComments().add(comment);
					recipeService.save(recipe);
					comment.setRecipe(0);
					commentService.save(comment);
					result = new ModelAndView(direccion);
				} catch (Throwable oops) {
					result = createEditModelAndView(comment,
							"user.commit.error");
				}
			}

			return result;
		}

	//Creating--------------------------------------
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int recipeId) {
			ModelAndView result;
			Comment comment;
			Customer customer;
			
			comment = commentService.create();
			customer = customerService.findOneByuserAccountId(LoginService.getPrincipal().getId());
			
			comment.setRecipe(recipeId);
			comment.setActor(customer);
			result = createEditModelAndView(comment);

			return result;
		}
		
		// Ancillary methods ----------------------------------------------------------------

			protected ModelAndView createEditModelAndView(Comment comment) {
				ModelAndView result;
						
				result = createEditModelAndView(comment, null);

				return result;
			}
			
			protected ModelAndView createEditModelAndView(Comment comment, String message) {
				ModelAndView result;
				
				result = new ModelAndView("comment/edit");
				result.addObject("comment", comment);
				result.addObject("message", message);

				return result;
			}
		
		
}
