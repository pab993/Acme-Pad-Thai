package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	//Managed Repositories--------------------------
	
	@Autowired
	private SocialIdentityRepository socialIdentityRepository;
			
	//Supporting Services---------------------------
	
	@Autowired
	private ActorService actorService;
			
	//Methods CRUDS---------------------------------
	
	public SocialIdentity create() {
		actorService.check();
		
		SocialIdentity si;
		si = new SocialIdentity();
		
		Actor actor = actorService.gettingActor(
				LoginService.getPrincipal().getId());
		si.setActor(actor);
		
		return si;
	}

	public SocialIdentity save(SocialIdentity si) {
		actorService.check();
		Assert.notNull(si);
		SocialIdentity socialIdentityRes = socialIdentityRepository.save(si);
		return socialIdentityRes;

	}
	
	public void delete(SocialIdentity si){
		Assert.notNull(si);
		Assert.isTrue(si.getId() != 0);
		Assert.isTrue(socialIdentityRepository.exists(si.getId()));
		actorService.checkSocialIdentity(si);
		socialIdentityRepository.delete(si);
		
	}

	public SocialIdentity findOne(int si) {
		SocialIdentity result;
		Assert.notNull(si);
		result = socialIdentityRepository.findOne(si);
		Assert.notNull(result);
		return result;
	}
	
	public Collection<SocialIdentity> findAll() {
		Collection<SocialIdentity> result;
		result = socialIdentityRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Collection<SocialIdentity> findAllByPrincipal(UserAccount principal) {
		// TODO Auto-generated method stub
		Assert.notNull(principal);
		
		Collection<SocialIdentity> result = socialIdentityRepository.findAllByPrincipal(principal);
		Assert.notNull(result);
		
		return result;
	}

	// Business methods-----------------------------
	
}
