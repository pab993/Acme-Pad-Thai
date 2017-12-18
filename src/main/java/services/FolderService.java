package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mysql.jdbc.Security;

import domain.Actor;
import domain.Folder;
import domain.User;

import repositories.FolderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
;


@Service
@Transactional
public class FolderService {

	//Managed repository ------------------
	
		@Autowired
		private FolderRepository folderRepository;
				
			
		//Supporting services -----------------
		
		@Autowired
		private AdministratorService administratorService;
		@Autowired
		private NutritionistService nutritionistService;
		@Autowired
		private UserService userService;
		@Autowired
		private ActorService actorService;
		//Constructors ------------------------

		// Simple CRUD methods -----------------
	
	public Folder create() {
		Folder res = new Folder();
		Authority ad = new Authority();
		Authority us = new Authority();
		Authority nut = new Authority();
		ad.setAuthority("ADMIN");
		us.setAuthority("USER");
		nut.setAuthority("NUTRITIONIST");
		UserAccount uA=LoginService.getPrincipal();
		if (uA.getAuthorities().contains(ad)){
		administratorService.assignFolder(res,uA);}
		else if (uA.getAuthorities().contains(nut)){
			nutritionistService.assignFolder(res,uA);}
		else if (uA.getAuthorities().contains(us)){
			userService.assignFolder(res,uA);}
		return res;

	}
	public Folder createWithoutUserAccount() {
		Folder res = new Folder();
		return res;

	}
	
	public Collection<Folder> findAll(){
		Collection<Folder>res;
		actorService.findAll();
		res=folderRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Folder findOne(int id){
		
		Assert.notNull(id);
		Folder folder = folderRepository.findOne(id);
		Assert.notNull(folder);
		return folder;
	}

	public Folder save(Folder folder) {
		
		Assert.notNull(folder);
		Assert.isTrue(checkFolder(folder));
		//Assert.isTrue(checkFolder(folder.getActor(), folder));
		Folder folderRes = folderRepository.save(folder);
		return folderRes;

	}
	
public Folder saveSend(Folder folder) {
		
		Assert.notNull(folder);
		Folder folderRes = folderRepository.save(folder);
		return folderRes;

	}
	public Folder InitialSave(Folder folder) {
		//actorService.checkFolder(folder);
		Assert.notNull(folder);
		//Assert.isTrue(checkFolder(folder));
		//Assert.isTrue(checkFolder(folder.getActor(), folder));
		Folder folderRes = folderRepository.save(folder);
		return folderRes;

	}

	//Faltaría comprobar que no se pueden eliminar las 4 carpetas iniciales
	public void delete(Folder folder) {
		actorService.checkFolder(folder);
		Assert.notNull(folder);
		Assert.isTrue(folder.getId() != 0);
		Assert.isTrue(checkFolder(folder));
		Assert.isTrue(folderRepository.exists(folder.getId()));
		folderRepository.delete(folder);

	}
	public Boolean checkFolder(Folder f){
		Boolean res = true;
		if(f.getName().equalsIgnoreCase("inbox")||f.getName().equalsIgnoreCase("outbox")||f.getName().equalsIgnoreCase("spambox")||f.getName().equalsIgnoreCase("trashbox")){
				res=false;}
		return res;
	}
	
	public Boolean checkFolder(Actor user, Folder folder){
		Boolean res = true;
		for(Folder f:user.getFolders()){
			if (folder.getName().equalsIgnoreCase(f.getName())){
				if(!(folder.getId()==f.getId())){
				res=false;
				break;}}
		}
		return res;
	}
}
