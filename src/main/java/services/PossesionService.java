	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;


import repositories.PossesionRepository;

import domain.Possesion;
import domain.UnitSystem;

	@Service
	@Transactional
	public class PossesionService {

		//Managed repository ------------------
		
		@Autowired
		private PossesionRepository possesionRepository;
		
		
		//Supporting services -----------------
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Possesion create() {
			Possesion res;
			res = new Possesion();
			return res;

		}
		
		public Collection<Possesion> findAll(){
			Collection<Possesion>res;
			res=possesionRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public Possesion findOne(int id){
			
			Assert.notNull(id);
			Possesion possesion = possesionRepository.findOne(id);
			Assert.notNull(possesion);
			return possesion;
		}

		public Possesion save(Possesion possesion) {
			Assert.notNull(possesion);
			Possesion possesionRes = possesionRepository.save(possesion);
			return possesionRes;

		}

		public void delete(Possesion possesion) {
			Assert.notNull(possesion);
			Assert.isTrue(possesion.getId() != 0);
			Assert.isTrue(possesionRepository.exists(possesion.getId()));
			possesionRepository.delete(possesion);

		}
		
		//Other bussiness methods--------------------------------------
		
		public Possesion possesionByIngredientAndProperty(int ingredientId, int propertyId){
			Possesion result;
			Assert.notNull(ingredientId);
			Assert.notNull(propertyId);
			result = possesionRepository.possesionByIngredientAndProperty(ingredientId, propertyId);
			Assert.notNull(result);
			return result;
		}
}
