	package services;

	import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

	
import repositories.RecipeRepository;

import domain.LikeDislike;
import domain.Qualification;
import domain.Recipe;
import domain.Step;
import domain.UnitSystem;


	@Service
	@Transactional
	public class RecipeService {

		//Managed repository ------------------
		
		@Autowired
		private RecipeRepository recipeRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private QualificationService qualificationService;
		
		@Autowired
		private LikeDislikeService likeDislikeService;
		
		@Autowired
		private UnitSystemService unitSystemService;
		
		@Autowired
		private AdministratorService administratorService;
		
		@Autowired
		private StepService stepService;
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Recipe create() {
			long l = 10;
			Date d = new Date(System.currentTimeMillis()- l);
			Recipe res;
			res = new Recipe();
			res.setMomentAuthored(d);
			String ticker = createTicker();
			res.setTicker(ticker);

			return res;

		}
		
		public Collection<Recipe> findAll(){
			Collection<Recipe>res;
			res=recipeRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public Recipe findOne(int id){
			
			Assert.notNull(id);
			Recipe recipe = recipeRepository.findOne(id);
			Assert.notNull(recipe);
			return recipe;
		}
		

		public Recipe save(Recipe recipe) {
			long l = 10;
			Date d = new Date(System.currentTimeMillis()- l);
			Assert.notNull(recipe);
			recipe.setMomentLastUpdate(d);
			if(recipe.getSteps().isEmpty()){
				Collection<Step> steps = new ArrayList<Step>();
				Step step = stepService.create();
				step.setRecipe(recipe);
				steps.add(step);
				recipe.setSteps(steps);
				step.setDescription("Introduzca aquí el primer paso de su receta");
				step.setNumber(1);
			}
			Recipe recipeRes = recipeRepository.save(recipe);
			return recipeRes;
		}

		public void deleteRelations(Recipe recipe) {
			Assert.notNull(recipe);
			Assert.isTrue(recipe.getId() != 0);
			Assert.isTrue(recipeRepository.exists(recipe.getId()));
			
			for(LikeDislike likeDislike : recipe.getLikes()){
				likeDislikeService.delete(likeDislike);
				}
			for(Qualification qualification : recipe.getQualifications()){
				qualificationService.delete(qualification);
			}
			for(UnitSystem unitSystem : recipe.getUnitSystems()){
				unitSystemService.delete(unitSystem);
				}
			
			Collection<LikeDislike> likes = new ArrayList<LikeDislike>();
			Collection<Qualification> qualifications = new ArrayList<Qualification>();
			Collection<UnitSystem> unitSystems = new ArrayList<UnitSystem>();
//			Collection<Comment> comments = new ArrayList<Comment>();
			
			recipe.setLikes(likes);
			recipe.setQualifications(qualifications);
			recipe.setUnitSystems(unitSystems);
//			recipe.setComments(comments);
			Recipe recipe2 = save(recipe);
			delete(recipe2);

		}
		
		public void delete(Recipe recipe){
			Assert.notNull(recipe);
			Assert.isTrue(recipe.getId() != 0);
			Assert.isTrue(recipeRepository.exists(recipe.getId()));
			
			recipeRepository.delete(recipe);
		}
		//Other business methods
		
		public Collection<Recipe> findByUser(int userId){
			Assert.notNull(userId);
			Collection<Recipe> res = recipeRepository.findByUser(userId);
			return res;
		}
		
		
		public Integer MinimumNumberRecipesPerUser(){
			administratorService.checkPrincipal();
			Integer res=recipeRepository.minimumNumberRecipesPerUser();
			Assert.notNull(res);
			return res;
			
		}
		
		public Double AverageNumberRecipesPerUser(){
			administratorService.checkPrincipal();
			Double res=0.0;
			res=recipeRepository.averageNumberRecipesPerUser();
			Assert.notNull(res);
			return res;
			
		}
		
		public Integer MaximumNumberRecipesPerUser(){
			administratorService.checkPrincipal();
			Integer res=0;
			res=recipeRepository.maximumNumberRecipesPerUser();
			Assert.notNull(res);
			return res;
			
		}
		
		public Collection<Recipe> RecipeSearch(String string){
			Collection<Recipe> res;
			res = recipeRepository.recipeSearch(string);
			Assert.notNull(res);
			return res;	
		}
		
		public Collection<Recipe> ShowRecipesByCategory(){
			Collection<Recipe> res;
			res=recipeRepository.showRecipesByCategory();
			Assert.notNull(res);
			return res;
			
		}
		
		public Collection<Recipe> findByContest(int contestId){
			
			Assert.notNull(contestId);
			
			Collection<Recipe> result = recipeRepository.findByContest(contestId);
			Assert.notNull(result, "There's not recipes qualificating");
			
			return result;
		}
		
		public Collection<Recipe> findFollowedsRecipes(int customerId){
			
			Assert.notNull(customerId);
			Collection<Recipe> result = recipeRepository.findFollowedsRecipes(customerId);
			return result;
		}
		
		public Collection<Recipe> findRecentRecipes(int customerId){
			
			Assert.notNull(customerId);
			Collection<Recipe> result = recipeRepository.findRecentRecipes(customerId);
			return result;
		}
		
		
		// Método para crear el ticker de una receta.
				public String createTicker(){
					String result = "";
					String digits = "0123456789";
					String alphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
					Random rnd = new Random();
					
					boolean equal = false;
					do{
						for(int i=0;i<6;i++){
							result = result + digits.charAt(rnd.nextInt(digits.length()));
						}
						result = result + "-";
						
						for(int i=0;i<4;i++){
							result = result + alphas.charAt(rnd.nextInt(alphas.length()));
						}
						Assert.isTrue(result.matches("^[0-9]{6}[-][a-zA-Z]{4}$"));
						
						Collection<Recipe> recipes = recipeRepository.findAll();
						for(Recipe r:recipes){
							equal = result.contentEquals(r.getTicker());
							if(equal == true){
								break;
							}
						}
					}while(equal);
					
					return result;
				}		
}
