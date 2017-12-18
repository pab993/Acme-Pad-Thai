package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import security.LoginService;
import security.UserAccount;
import domain.Curriculum;
import domain.Endorser;
import domain.Nutritionist;

@Service
@Transactional
public class CurriculumService {

	public CurriculumService(){
		super();
	}
	
	@Autowired
	private CurriculumRepository curriculumRepository;
	
	//aux
	@Autowired
	private NutritionistService nutritionistService;
	@Autowired
	private EndorserService endorserService;
	
	
	//Methods CRUDs ------------------
	
			public Curriculum create(){
				Curriculum c = new Curriculum();
				return c;
			}
			
			public Collection<Curriculum> findAll(){
				
				return curriculumRepository.findAll();
			}
			
			public Curriculum findOne(int id){
				
				return curriculumRepository.findOne(id);
			}
			
			public Curriculum save(Curriculum c){
				Assert.notNull(c);
				UserAccount uA=LoginService.getPrincipal();
				Curriculum curriculumRes = curriculumRepository.save(c);
				Nutritionist n=nutritionistService.findOneByUserAccountId(uA.getId());
				n.setCurriculum(c);
				nutritionistService.save(n);
				
				return curriculumRes;
			}
			
			

			public void delete(Curriculum c){
				 
				Assert.notNull(c);
				checkPrincipal(c);
				for(Endorser e : c.getEndorsers()){
					endorserService.delete(e);
				}
				Collection<Endorser> endorsers = new ArrayList<Endorser>();
				c.setEndorsers(endorsers);
				save(c);
				
				Nutritionist n = nutritionistService.findOneByUserAccountId(LoginService.getPrincipal().getId());
				n.setCurriculum(null);
				nutritionistService.save(n);
				curriculumRepository.delete(c);
			}
			
			//Other bussiness methods ---------
			
			private void checkPrincipal(Curriculum c) {
				// comprueba qe el curriculum pertence al usuario
				UserAccount userAccount = LoginService.getPrincipal();
				Nutritionist n = nutritionistService.findByUserAccountId(userAccount.getId());
				Assert.isTrue(n.getCurriculum().equals(c));
			}
}
