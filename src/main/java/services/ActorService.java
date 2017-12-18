package services;


import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Campaign;
import domain.Comment;
import domain.Folder;
import domain.Message;
import domain.SocialIdentity;
import domain.Step;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {
	
	//Managed Repositories--------------------------
	
	@Autowired
	private ActorRepository actorRepository;
	
	//Supporting Services---------------------------
	
	//Methods CRUDS---------------------------------
	
	public Actor create(){
		Actor res;
		res = new Actor();
		return res;
	}
	
	public Actor save(Actor actor){
		Assert.notNull(actor);
		Actor actorRes = actorRepository.save(actor);
		return actorRes;
		
	}
	
	public void delete(Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(actorRepository.exists(actor.getId()));
		actorRepository.delete(actor);

	}
	
	public Actor findOne(int actorId){
		
		Assert.notNull(actorId);
		Actor a = actorRepository.findOne(actorId);
//		Assert.notNull(a);
		return a;
	}
	
	public Collection<Actor> findAll(){
		
		Collection<Actor>a = actorRepository.findAll();
		Assert.notNull(a);
		return a;
		
	}
	
	//Other business methods-------------------------------
	
	public Actor gettingActor(int id){
		Actor actor = actorRepository.findByActorAccountId(id);
		return actor;
	}

	public void check(){
		UserAccount userAccount = LoginService.getPrincipal();
		UserAccount userAccount2 = actorRepository.findByUserAccountId(userAccount.getId());
		Assert.isTrue(userAccount.getId() == userAccount2.getId());
		
	}
	
	public void checkSocialIdentity(SocialIdentity si){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Assert.isTrue(actor.getSocialIdentities().contains(si));
	}
	
	public void checkComment(Comment c){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Assert.isTrue(actor.getCommentsActor().contains(c));
	}
	
	public void checkStep(Step s){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Assert.isTrue(s.getRecipe().getUser().getId() == actor.getId());
	}
	
	public void checkSponsor(){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Authority i = new Authority();
		i.setAuthority("SPONSOR");
		Assert.isTrue(actor.getUserAccount().getAuthorities().contains(i));
	}
	
	public void checkCampaign(Campaign c){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Assert.isTrue(c.getSponsor().getId() == actor.getId());
	}
	
	public void checkNutritionist(){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Authority i = new Authority();
		i.setAuthority("NUTRITIONIST");
		Assert.isTrue(actor.getUserAccount().getAuthorities().contains(i));
	}
	
	public void checkMessage(Message m){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Assert.isTrue(m.getSenderActor().getId() == actor.getId()||(m.getRecipientActor().getId()==actor.getId()));
	}
	
	public void checkFolder(Folder f){
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorRepository.findByActorAccountId(userAccount.getId());
		Assert.isTrue(f.getActor().getId() == actor.getId());
	}
	
	public Actor findByActorAccountId(int id){
		Assert.notNull(id);
		Actor result = actorRepository.findByActorAccountId(id);
		Assert.notNull(result);
		return result;
	}
}
