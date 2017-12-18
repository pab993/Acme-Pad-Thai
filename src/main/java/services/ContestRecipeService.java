package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import domain.ContestRecipe;

import repositories.ContestRecipeRepository;


@Service
@Transactional
public class ContestRecipeService {
	
	//Managed Repositories--------------------------
	
	@Autowired
	private ContestRecipeRepository contestRecipeRepository;
			
	//Supporting Services---------------------------
	

			
	//Methods CRUDS---------------------------------
	
	public ContestRecipe create(){
		
		ContestRecipe res;
		res = new ContestRecipe();
		return res;
	}
	
	public ContestRecipe save(ContestRecipe contestRecipe){
		Assert.notNull(contestRecipe);
		ContestRecipe contestRecipeRes = contestRecipeRepository.save(contestRecipe);
		return contestRecipeRes;
		
	}
	
	//Creo que esto no hace falta
	public void delete(ContestRecipe contestRecipe){
		Assert.notNull(contestRecipe);
		Assert.isTrue(contestRecipe.getId() != 0);
		Assert.isTrue(contestRecipeRepository.exists(contestRecipe.getId()));
		contestRecipeRepository.delete(contestRecipe);
	}
	

	public ContestRecipe findOne(int contestRecipeId){
		ContestRecipe result;
		Assert.notNull(contestRecipeId);
		result = contestRecipeRepository.findOne(contestRecipeId);
		Assert.notNull(result);
		return result;
		
	}
	
	public Collection<ContestRecipe> findAll(){
		Collection<ContestRecipe> res;
		res = contestRecipeRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	//Other business-------------------------------
	
	public int findRestByContestRecipeId(int contestRecipeId){
		int rest;
		rest = contestRecipeRepository.findRestByContestRecipeId(contestRecipeId);
		Assert.notNull(rest);
		return rest;
		
	}
	
	public Collection<ContestRecipe> findAllByContest(int id) {
		// TODO Auto-generated method stub
		Assert.notNull(id);
		
		Collection<ContestRecipe> resul = new ArrayList<ContestRecipe>();
		resul = contestRecipeRepository.findAllByContest(id);
		
		Assert.notNull(resul);
		return resul;
	}
	
}
