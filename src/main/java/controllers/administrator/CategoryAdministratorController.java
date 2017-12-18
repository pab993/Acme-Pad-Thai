package controllers.administrator;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import domain.Category;
import domain.Recipe;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController {

	// Services ---------------------------------------

	@Autowired
	private CategoryService categoryService;

	// Constructors -----------------------------------------------------

	public CategoryAdministratorController() {
		super();
	}

	// Listing ------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Category> categoriesBeta;
		Collection<Category> categories = categoryService.findAll();

		/*categoriesBeta = categoryService.findAll();
		for(Category c: categoriesBeta){
			if(c.getOneCategory()==null){
				categories.add(c);
			}
		}*/

		result = new ModelAndView("category/list");
		result.addObject("requestURI", "category/administrator/list.do");
		result.addObject("categories", categories);

		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int categoryId) {
		ModelAndView result;

		Collection<Category> categories;

		categories = categoryService.findOne(categoryId).getCategories();

		result = new ModelAndView("category/display");
		result.addObject("requestURI", "category/administrator/display.do");
		result.addObject("categories", categories);

		return result;
	}
	// Create methods---------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Category category;
		

		category = categoryService.create();
		result = createEditModelAndViewX(category);

		return result;
	}

	// Edition------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId) {
		ModelAndView result;
		Category category;

		category = categoryService.findOne(categoryId);
		Assert.notNull(category);
		result = createEditModelAndView(category);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Category category, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndViewX(category);
		} else {
			try {
				categoryService.save(category);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(category,
						"category.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Category category, BindingResult binding) {
		ModelAndView result;

		try {
			categoryService.delete(category);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(category, "category.commit.error");

		}
		return result;
	}

	// Ancillary methods------------------------------

	protected ModelAndView createEditModelAndView(Category category) {
		ModelAndView result;

		result = createEditModelAndView(category, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Category category,
			String message) {
		ModelAndView result;
		Collection<Category> rest=category.getCategories();
		Collection<Category> res=categoryService.findAll();
		res.removeAll(rest);
		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("message", message);
		result.addObject("oneCategories", res);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndViewX(Category category) {
		ModelAndView result;

		result = createEditModelAndViewX(category, null);
		return result;
	}

	protected ModelAndView createEditModelAndViewX(Category category,
			String message) {
		ModelAndView result;
		Collection<Category> res=categoryService.findAll();
		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("message", message);
		result.addObject("oneCategories", res);
		
		return result;
	}
	
	protected Collection<Category> SearchCategories(Category a){
		Collection<Category> rest=a.getCategories();
		if(!(a.getCategories().isEmpty())){
			for(Category c:a.getCategories()){
			SearchCategoriesIterator(c,rest);}
		}
		return rest;
		
	}
	protected Collection<Category> SearchCategoriesIterator(Category a, Collection<Category> res){
		res.addAll(a.getCategories());
		if(!(a.getCategories().isEmpty())){
			for(Category c:a.getCategories()){
			SearchCategoriesIterator(c,res);}
		}
		return res;
		
	}
}
