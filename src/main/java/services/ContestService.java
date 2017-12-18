package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ContestRepository;
import domain.Contest;
import domain.ContestRecipe;
import domain.Qualification;

@Service
@Transactional
public class ContestService {
	
		//Managed Repositories--------------------------
	
		@Autowired
		private ContestRepository contestRepository;
		
		//Supporting Services---------------------------
		
		@Autowired
		private AdministratorService administratorService;
		
		//Methods CRUDS---------------------------------

		public Contest create(){
			
			administratorService.checkPrincipal();
			Contest res;
			res = new Contest();
			
			Collection<Qualification> qualifications = new ArrayList<Qualification>();
			Collection<ContestRecipe> contestRecipes = new ArrayList<ContestRecipe>();
			
			res.setQualifications(qualifications);
			res.setContestRecipes(contestRecipes);
			
			return res;
			
		}
		
		public Contest save(Contest contest){
			
			administratorService.checkPrincipal();
			
			Assert.notNull(contest);
			Contest contestRes = contestRepository.save(contest);
			return contestRes;
			
		}
		
		public void delete(Contest contest) {
			
			administratorService.checkPrincipal();
			
			Assert.notNull(contest);
			Assert.isTrue(contest.getId() != 0);
			Assert.isTrue(contestRepository.exists(contest.getId()));
			Assert.isTrue(contest.getQualifications().isEmpty());
			contestRepository.delete(contest);

		}
		
		public Contest findOne(int contestId){
			Contest result;
			Assert.notNull(contestId);
			result = contestRepository.findOne(contestId);
			Assert.notNull(result);
			return result;
			
		}
		
		public Collection<Contest> findAll(){
			Collection<Contest> res;
			res = contestRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		
		//Other business-------------------------------
		
		public Integer minimumNumberRecipesQualified(){
			administratorService.checkPrincipal();
			Integer res;
			res = contestRepository.minimumNumberRecipesQualified();
			Assert.notNull(res);
			return res;
			
		}
			
		public Integer maximumNumberRecipesQualified(){
			administratorService.checkPrincipal();
			Integer res;
			res = contestRepository.maximumNumberRecipesQualified();
			Assert.notNull(res);
			return res;
			
		}
		
		public Double averageNumberRecipesQualified(){
			administratorService.checkPrincipal();
			Double res;
			res = contestRepository.averageNumberRecipesQualified();
			Assert.notNull(res);
			return res;
			
		}
		
		public Collection<Contest> contestWithMoreRecipesQualified(){
			administratorService.checkPrincipal();
			Collection<Contest> res;
			res = contestRepository.contestWithMoreRecipesQualified();
			Assert.notNull(res);
			return res;
			
		}
		
		public Collection<Contest> findClosedContests(){
			Collection<Contest> res;
			res = contestRepository.findClosedContests();
			Assert.notNull(res);
			return res;
		}
		
}

