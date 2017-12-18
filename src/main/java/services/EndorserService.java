package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Endorser;
import domain.Message;
import domain.Nutritionist;

@Service
@Transactional

public class EndorserService {

	public EndorserService(){
		super();
	}
	
	@Autowired
	private EndorserRepository endorserRepository;
	// supporting services
	@Autowired
	private NutritionistService nutritionistService;
	//Methods CRUDs ------------------
	
	public Endorser create(){
		Endorser e = new Endorser();
		return e;
	}

	public Collection<Endorser> findAll(){
		
		Collection<Endorser> collectioEndorsers = endorserRepository.findAll();
		Assert.notNull(endorserRepository);
		return collectioEndorsers;
	}
	
	public Endorser findOne(int id){
		
		Assert.notNull(id, "id recibido nulo");
		Endorser endorser = endorserRepository.findOne(id);
		Assert.notNull(endorser, "no se ha encontrado endorser");
		return endorser;
	}
	
	public Endorser save(Endorser e){
		
		checkPrincipal();
		Assert.notNull(e);
		Endorser endorserRes = endorserRepository.save(e);
		if(!(endorserRes.getCurriculum().getEndorsers().contains(endorserRes))){
			endorserRes.getCurriculum().getEndorsers().add(endorserRes);
			
		}
		return endorserRes;
	}
	
	public void delete(Endorser e){
		
		checkPrincipal();
		checkPrincipalHasEndorser(e);
		Assert.isTrue(endorserRepository.exists(e.getId()));
		endorserRepository.delete(e);
	}

	private void checkPrincipalHasEndorser(Endorser e) {
		// TODO Auto-generated method stub
		UserAccount account = LoginService.getPrincipal();
		Nutritionist nutritionist = nutritionistService.findOneByUserAccountId(account.getId());
		Assert.notNull(nutritionist);
		Assert.isTrue(nutritionist.getCurriculum().getEndorsers().contains(e), "este endroser no pertenece a este nutricionista");
	}

	//Other bussines methods
	
	private void checkPrincipal() {
		// TODO Auto-generated method stub
		Authority authority = new Authority();
		authority.setAuthority("NUTRITIONIST");
		
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(authority), "Usted no es un nutricionista");
	}

	public Collection<Endorser> findByCurriculum(int curriculumId) {
		Collection<Endorser> result = endorserRepository.findByCurriculum(curriculumId);
		Assert.notNull(result, "There's not messages in this folder");
		
		return result;
	}

	public Collection<Endorser> findAllByCurriculum(int curriculumId) {
		// TODO Auto-generated method stub
		Assert.notNull(curriculumId);
		
		Collection<Endorser> endorsers = endorserRepository.findAllByCurriculum(curriculumId);
		Assert.notNull(endorsers);
		
		return endorsers;
	}
}
