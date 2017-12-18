	package services;

	import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

import repositories.CategoryRepository;


import domain.Category;
import domain.Recipe;


	@Service
	@Transactional
	public class CategoryService {

		//Managed repository ------------------
		
		@Autowired
		private CategoryRepository categoryRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private AdministratorService administratorService;
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Category create() {
			administratorService.checkPrincipal();
			Category res;
			res = new Category();
			return res;

		}
		
		public Collection<Category> findAll(){
			Collection<Category>res;
			res=categoryRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public Category findOne(int id){
			
			Assert.notNull(id);
			Category category = categoryRepository.findOne(id);
			Assert.notNull(category);
			return category;
		}

		public Category save(Category category) {
			administratorService.checkPrincipal();
			Assert.notNull(category);
			Category categoryRes = categoryRepository.save(category);
			return categoryRes;

		}

		public void delete(Category categore) {
			administratorService.checkPrincipal();
			Category category=findOne(categore.getId());
			Boolean resa=category.getRecipes().isEmpty();
			Boolean resb=category.getRecipes()==null;
			Assert.notNull(category);
			Assert.isTrue(category.getId() != 0);
			Assert.isTrue(categoryRepository.exists(category.getId()));
			Assert.isTrue(resa||resb);
			if(!(category.getCategories().isEmpty())){
				for (Category c: category.getCategories()){
					delete(c);
				}
			}
			categoryRepository.delete(category);

		}
}


