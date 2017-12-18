	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

import repositories.IngredientRepository;

import domain.Ingredient;

	@Service
	@Transactional
	public class IngredientService {

		//Managed repository ------------------
		
		@Autowired
		private IngredientRepository ingredientRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private AdministratorService administratorService;
		
		@Autowired
		private ActorService actorService;
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Ingredient create() {
			actorService.checkNutritionist();
			Ingredient res;
			res = new Ingredient();
			return res;

		}
		
		public Collection<Ingredient> findAll(){
			Collection<Ingredient>res;
			res=ingredientRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		

		public Ingredient save(Ingredient ingredient) {
			actorService.checkNutritionist();
			Assert.notNull(ingredient);
			Ingredient ingredientRes = ingredientRepository.save(ingredient);
			return ingredientRes;

		}

		public void delete(Ingredient ingredient) {
			actorService.checkNutritionist();
			Assert.notNull(ingredient);
			Assert.isTrue(ingredient.getId() != 0);
			Assert.isTrue(ingredient.getUnitSystems().isEmpty());
			Assert.isTrue(ingredientRepository.exists(ingredient.getId()));
			
			ingredientRepository.delete(ingredient);

		}
		
		public Ingredient findOne(int ingredientId){
			Ingredient result;
			Assert.notNull(ingredientId);
			result = ingredientRepository.findOne(ingredientId);
			Assert.notNull(result);
			return result;
		}
		
	//Other business methods
		
		public Double averageIngredientsPerRecipe(){
			administratorService.checkPrincipal();
			Double res;
			res = ingredientRepository.averageIngredientsPerRecipe();
			Assert.notNull(res);
			return res;
			
		}
		
		public Double standarDeviationIngredientsPerRecipe(){
			administratorService.checkPrincipal();
			Double res;
			res = ingredientRepository.standarDeviationIngredientsPerRecipe();
			Assert.notNull(res);
			return res;
			
		}
}
