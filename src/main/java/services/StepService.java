package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import domain.Recipe;
import domain.Step;

import repositories.StepRepository;


@Service
@Transactional
public class StepService {
	
	//Managed Repositories--------------------------
	
		@Autowired
		private StepRepository stepRepository;
					
	//Supporting Services---------------------------
		@Autowired
		private AdministratorService administratorService;
		
		@Autowired
		private ActorService actorService;
		
					
	//Methods CRUDS---------------------------------
		
		public Step create() {
			actorService.check();
			Step res;
			res = new Step();
			return res;

		}

		public Step save(Step step) {
			actorService.checkStep(step);
			Assert.notNull(step);
			Step stepRes = stepRepository.save(step);
			return stepRes;

		}
		
		public void delete(Step step){
			actorService.checkStep(step);
			Assert.notNull(step);
			stepRepository.delete(step);
		}
		

		public Step findOne(int stepId) {

			Step result;
			Assert.notNull(stepId);

			result = stepRepository.findOne(stepId);
			Assert.notNull(result);
			return result;
		}
		
		public Collection<Step> findAll(){
			Collection<Step> result;
			result = stepRepository.findAll();
			Assert.notNull(result);
			return result;
			
		}
		
	//Other business methods-----------------------
		
		public Double averageStepsPerRecipe(){
			administratorService.checkPrincipal();
			Double res;
			res = stepRepository.averageStepsPerRecipe();
			Assert.notNull(res);
			
			return res;
			
		}
		
		public Double standarDeviationStepsPerRecipe(){
			administratorService.checkPrincipal();
			Double res;
			res = stepRepository.standarDeviationStepsPerRecipe();
			Assert.notNull(res);
			
			return res;
			
		}
		
		
}
