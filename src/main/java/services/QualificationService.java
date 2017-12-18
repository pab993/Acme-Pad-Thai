	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

	
import repositories.QualificationRepository;

import domain.ContestRecipe;
import domain.LikeDislike;
import domain.Qualification;
import domain.Recipe;

	@Service
	@Transactional
	public class QualificationService {

		//Managed repository ------------------
		
		@Autowired
		private QualificationRepository qualificationRepository;
		
		@Autowired
		private RecipeService recipeService;
		
		@Autowired
		private ContestRecipeService contestRecipeService;
		
		//Supporting services -----------------
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Qualification create() {
			Qualification res;
			res = new Qualification();
			return res;

		}
		
		public Collection<Qualification> findAll(){
			Collection<Qualification>res;
			res=qualificationRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public Qualification findOne(int id){
			
			Assert.notNull(id);
			Qualification qualification = qualificationRepository.findOne(id);
			Assert.notNull(qualification);
			return qualification;
		}

		public Qualification save(Qualification qualification) {
			Assert.notNull(qualification);
			Qualification qualificationRes = qualificationRepository.save(qualification);
			Recipe recipe = recipeService.findOne(qualification.getRecipe().getId());
			ContestRecipe contestRecipe = new ContestRecipe();
			contestRecipe.setTicker(recipe.getTicker());
			contestRecipe.setSummary(recipe.getSummary());
			contestRecipe.setTitle(recipe.getTitle());
			contestRecipe.setMomentAuthored(recipe.getMomentAuthored());
			contestRecipe.setMomentLastUpdate(recipe.getMomentLastUpdate());
			contestRecipe.setPictures(recipe.getPictures());
			contestRecipe.setHints(recipe.getHints());
			contestRecipe.setWinner(false);
			int countLikes = 0;
			int countDislikes = 0;
			for(LikeDislike ld : recipe.getLikes()){
				if(ld.getLikeOrDislike() == true){
					countLikes = countLikes + 1;
				}else{
					countDislikes = countDislikes + 1;
				}
			}
			contestRecipe.setLikes(countLikes);
			contestRecipe.setDislikes(countDislikes);
			int countRest = countLikes - countDislikes;
			contestRecipe.setContest(qualification.getContest());
			contestRecipe.setRest(countRest);
			contestRecipeService.save(contestRecipe);
			return qualificationRes;

		}

		public void delete(Qualification qualification) {
			Assert.notNull(qualification);
			Assert.isTrue(qualification.getId() != 0);
			Assert.isTrue(qualificationRepository.exists(qualification.getId()));
			qualificationRepository.delete(qualification);

		}
}
