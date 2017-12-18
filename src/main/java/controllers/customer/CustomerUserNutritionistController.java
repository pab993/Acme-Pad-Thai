package controllers.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.CustomerService;
import services.FollowerService;
import services.LikeDislikeService;
import services.RecipeService;
import services.UserService;

import controllers.AbstractController;
import domain.Category;
import domain.Customer;
import domain.Follower;
import domain.LikeDislike;
import domain.Recipe;
import domain.User;

@Controller
@RequestMapping("/customer")
public class CustomerUserNutritionistController extends AbstractController{

	//Services ----------------------------------------------------------------
	
		@Autowired
		private CustomerService customerService;
		
		@Autowired
		private FollowerService followerService;
		
		@Autowired
		private RecipeService recipeService;
		
		@Autowired
		private LikeDislikeService likeDislikeService;
		
		@Autowired
		private UserService userService;
	
		
	//Lists ---------------------------------------------------------------------
		
		
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public ModelAndView list(){
			
			ModelAndView result;
			Collection<Customer> customers;
			Customer principalCustomer = customerService.findOneByuserAccountId(LoginService.getPrincipal().getId());
			
			customers = customerService.findAll();
			result = new ModelAndView("customer/list");
			result.addObject("customer", customers);
			result.addObject("customerVar", principalCustomer);
			result.addObject("requestURI", "customer/list.do");
			
			return result;
		}
		
	//Edits --------------------------------------------------------------------
		
		@RequestMapping(value="/editFollow", method=RequestMethod.GET)
		public ModelAndView editFollow(@RequestParam int customerId){
			
			ModelAndView result;
			Customer customer;
			
			customer = customerService.findOne(customerId);
			Assert.notNull(customer);
			Customer principalCustomer = customerService.findOneByuserAccountId(LoginService.getPrincipal().getId());
			Collection<Follower> followeds = principalCustomer.getFollowers();
			if(principalCustomer.getId() == customer.getId()){
				result = new ModelAndView("redirect:/customer/list.do");
				JOptionPane.showMessageDialog(null,"You can not follow yourself");
				return result;
			}
			for(Follower f : followeds){
				if(f.getFollowed().equals(customer)){
					JOptionPane.showMessageDialog(null,"You are following this customer already");
					result = new ModelAndView("redirect:/customer/list.do");
					return result;
				}
			}
			Follower newF = followerService.create();
			newF.setFollower(principalCustomer);
			newF.setFollowed(customer);
			principalCustomer.getFollowers().add(newF);
			customer.getFolloweds().add(newF);
			followerService.save(newF);
			customerService.save(principalCustomer);
			customerService.save(customer);
			result = new ModelAndView("redirect:/customer/list.do");
			JOptionPane.showMessageDialog(null,"You are following this customer now");
			return result;

		}
		
		@RequestMapping(value="/editUnfollow", method=RequestMethod.GET)
		public ModelAndView editUnfollow(@RequestParam int customerId){
			
			ModelAndView result;
			Customer customer;
			
			customer = customerService.findOne(customerId);
			Assert.notNull(customer);
			Customer principalCustomer = customerService.findOneByuserAccountId(LoginService.getPrincipal().getId());
			Collection<Follower> followeds = principalCustomer.getFollowers();
			if(principalCustomer.getId() == customer.getId()){
				result = new ModelAndView("redirect:/customer/list.do");
				JOptionPane.showMessageDialog(null,"You can not follow yourself");
				return result;
			}
			for(Follower f : followeds){
				if(f.getFollowed().equals(customer)){
					//Deja de seguir
					principalCustomer.getFollowers().remove(f);
					customer.getFolloweds().remove(f);
					followerService.delete(f);
					customerService.save(principalCustomer);
					customerService.save(customer);
					result = new ModelAndView("redirect:/customer/list.do");
					JOptionPane.showMessageDialog(null,"You do not follow this customer anymore");
					return result;
				}
			}
			JOptionPane.showMessageDialog(null,"You are not following this customer");
			result = new ModelAndView("redirect:/customer/list.do");
			return result;

		}
		
		
		@RequestMapping(value="/recipe/like", method=RequestMethod.GET)
		public ModelAndView editLike(@RequestParam int recipeId){
			
			ModelAndView result;
			Recipe recipe;
			Customer customer;
			
			recipe = recipeService.findOne(recipeId);
			customer = customerService.findOneByuserAccountId(LoginService.getPrincipal().getId());
			//User user = userService.findOneByuserAccountId(LoginService.getPrincipal().getId());
			Collection<LikeDislike>ldC = customer.getLikes(); 
			if(recipe.getUser().getId() == customer.getId()){
				JOptionPane.showMessageDialog(null,"You can not give a like or dislike to your own recipe");
				result = new ModelAndView("redirect:/user/recipes/list.do");
				return result;
			}else{
				for(LikeDislike ld : ldC){
					if(ld.getRecipe().getId() == recipe.getId()){
						//No se crea sólo se transforma
						result = createEditModelAndView(ld);
						return result;
					}
				}
				//Se crea una nueva entidad LikeDislike
				LikeDislike newLd = likeDislikeService.create();
				newLd.setCustomer(customer);
				newLd.setRecipe(recipe);
				result = createEditModelAndView(newLd);
				return result;
			}
		}
		
		@RequestMapping(value = "/recipe/like", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid LikeDislike likeDislike, BindingResult binding) {
			ModelAndView result;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(likeDislike);
			} else {
				try {
					likeDislikeService.save(likeDislike);
					result = new ModelAndView("redirect:/recipe/watch/list.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(likeDislike,
							"user.commit.error");
				}
			}

			return result;
		}
		
		@RequestMapping(value = "recipe/like", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(LikeDislike likeDislike, BindingResult binding) {
			ModelAndView result;

			try {
				likeDislikeService.delete(likeDislike);
				result = new ModelAndView("redirect:/recipe/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(likeDislike, "user.commit.error");

			}
			return result;
		}
		
		
	// Ancillary methods ----------------------------------------------------------------

		protected ModelAndView createEditModelAndView(LikeDislike ld) {
			ModelAndView result;
						
			result = createEditModelAndView(ld, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(LikeDislike ld,
				String message) {
			ModelAndView result;
			Boolean test1 = true;
			Boolean test2 = false;
						
			result = new ModelAndView("likeDislike/edit");
			result.addObject("likeDislike", ld);
			result.addObject("test1", test1);
			result.addObject("test2", test2);
			result.addObject("message", message);
			return result;
		}
		
}
