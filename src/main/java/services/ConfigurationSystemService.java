	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;


	
	import repositories.ConfigurationSystemRepository;

import domain.ConfigurationSystem;

	@Service
	@Transactional
	public class ConfigurationSystemService {

		//Managed repository ------------------
		
		@Autowired
		private ConfigurationSystemRepository configurationSystemRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private AdministratorService administratorService;
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public ConfigurationSystem create() {
			administratorService.checkPrincipal();
			ConfigurationSystem res;
			res = new ConfigurationSystem();
			return res;

		}
		
		public Collection<ConfigurationSystem> findAll(){
			Collection<ConfigurationSystem>res;
			res=configurationSystemRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public ConfigurationSystem findOne(int id){
			
			Assert.notNull(id);
			ConfigurationSystem configurationSystem = configurationSystemRepository.findOne(id);
			Assert.notNull(configurationSystem);
			return configurationSystem;
		}
		

		public ConfigurationSystem save(ConfigurationSystem configurationSystem) {
			administratorService.checkPrincipal();
			Assert.notNull(configurationSystem);
			ConfigurationSystem configurationSystemRes = configurationSystemRepository.save(configurationSystem);
			return configurationSystemRes;

		}

		public void delete(ConfigurationSystem configurationSystem) {
			administratorService.checkPrincipal();
			Assert.notNull(configurationSystem);
			Assert.isTrue(configurationSystem.getId() != 0);
			Assert.isTrue(configurationSystemRepository.exists(configurationSystem.getId()));
			configurationSystemRepository.delete(configurationSystem);

		}
}
