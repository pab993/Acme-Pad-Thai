package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.UnitSystem;

import repositories.UnitSystemRepository;


@Service
@Transactional
public class UnitSystemService {
	
	//Managed Repositories--------------------------
	
	@Autowired
	private UnitSystemRepository unitSystemRepository;
					
	//Supporting Services---------------------------
					
	//Methods CRUDS---------------------------------
	
	public UnitSystem create(){
		UnitSystem uS;
		uS = new UnitSystem();
		return uS;
		
	}
	
	public UnitSystem save(UnitSystem uS){
		Assert.notNull(uS);
		UnitSystem unitSystemRes = unitSystemRepository.save(uS);
		return unitSystemRes;
	}
	
	public void delete(UnitSystem uS){
		Assert.notNull(uS);
		Assert.isTrue(uS.getId() != 0);
		Assert.isTrue(unitSystemRepository.exists(uS.getId()));
		unitSystemRepository.delete(uS);
	}
	
	public UnitSystem findOne(int unitSystemId){
		UnitSystem result;
		Assert.notNull(unitSystemId);
		result = unitSystemRepository.findOne(unitSystemId);
		Assert.notNull(result);
		return result;
		
	}
	
	//Others business methods-----------------------

	public UnitSystem unitSystemByRecipeAndIngredient(int recipeId, int ingredientId){
		UnitSystem result;
		Assert.notNull(recipeId);
		Assert.notNull(ingredientId);
		result = unitSystemRepository.unitSystemByRecipeAndIngredient(recipeId, ingredientId);
		Assert.notNull(result);
		return result;
	}
}
