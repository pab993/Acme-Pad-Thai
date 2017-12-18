	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

	
import repositories.PropertyRepository;

import domain.Property;

	@Service
	@Transactional
	public class PropertyService {

		//Managed repository ------------------
		
		@Autowired
		private PropertyRepository propertyRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private ActorService actorService;
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Property create() {
			actorService.checkNutritionist();
			Property res;
			res = new Property();
			return res;

		}
		
		public Collection<Property> findAll(){
			Collection<Property>res;
			res=propertyRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public Property findOne(int id){
			
			Assert.notNull(id);
			Property property = propertyRepository.findOne(id);
			Assert.notNull(property);
			return property;
		}
		

		public Property save(Property property) {
			actorService.checkNutritionist();
			Assert.notNull(property);
			Property propertyRes = propertyRepository.save(property);
			return propertyRes;
		}

		public void delete(Property property) {
			actorService.checkNutritionist();
			Assert.notNull(property);
			Assert.isTrue(property.getId() != 0);
			Assert.isTrue(propertyRepository.exists(property.getId()));
			Assert.isTrue(property.getPossesions().isEmpty());
			propertyRepository.delete(property);

		}
}
