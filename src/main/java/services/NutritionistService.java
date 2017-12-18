package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NutritionistRepository;
import security.Authority;
import security.UserAccount;
import domain.Curriculum;
import domain.Folder;
import domain.Nutritionist;
import domain.User;

@Transactional
@Service
public class NutritionistService {

	//Managed Repositories--------------------------
	@Autowired
	private NutritionistRepository nutritionistRepository;

	//Supporting Services---------------------------
	
	@Autowired
	private CurriculumService curriculumService;
	@Autowired
	private FolderService	folderService;
	
	//Methods CRUDS---------------------------------
	
	
	public Nutritionist findByUserAccountId(int id) {
		
		Nutritionist n = nutritionistRepository.findByUserAccountId(id);
		Assert.notNull(n);
		return n;
	}
	
	
	public Nutritionist create(){
		
		UserAccount account = new UserAccount();
		
		Collection<Authority> authorities = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setAuthority("NUTRITIONIST");
		authorities.add(authority);
		account.setAuthorities(authorities);
		
		Nutritionist nutritionist = new Nutritionist();
		nutritionist.setUserAccount(account);
		Curriculum c= curriculumService.create();
		nutritionist.setCurriculum(c);
		
		return nutritionist;
	}
	
	public Collection<Nutritionist> findAll(){
		
		Collection<Nutritionist> nutritionists  = nutritionistRepository.findAll();
		Assert.notNull(nutritionists);
		return nutritionists;
	}
	
	public Nutritionist findOne(int id){
		
		Assert.notNull(id);
		Nutritionist nutritionist = nutritionistRepository.findOne(id);
		Assert.notNull(nutritionist);
		return nutritionist;
	}
	
	public Nutritionist save(Nutritionist nutritionist){
		
		Assert.notNull(nutritionist);
		if(!nutritionist.equals(nutritionistRepository.findOne(nutritionist.getId()))){
			
			String hashCode = new Md5PasswordEncoder().encodePassword(nutritionist.getUserAccount().getPassword(), null);
			
			nutritionist.getUserAccount().setPassword(hashCode);
			Folder inbox = folderService.createWithoutUserAccount();
			inbox.setName("inbox");
			nutritionist.getFolders().add(inbox);
			//folderService.save(inbox);
			Folder outbox = folderService.createWithoutUserAccount();
			outbox.setName("outbox");
			nutritionist.getFolders().add(outbox);
			//folderService.save(outbox);
			Folder spambox = folderService.createWithoutUserAccount();
			spambox.setName("spambox");
	
			nutritionist.getFolders().add(spambox);
			//folderService.save(spambox);
			Folder trashbox = folderService.createWithoutUserAccount();
			trashbox.setName("trashbox");
			nutritionist.getFolders().add(trashbox);
			
	
			//folderService.save(trashbox);
		
			Nutritionist nutritionistRes = nutritionistRepository.save(nutritionist);
			inbox.setActor(nutritionistRes);
			outbox.setActor(nutritionistRes);
			trashbox.setActor(nutritionistRes);
			spambox.setActor(nutritionistRes);
			folderService.InitialSave(inbox);
			folderService.InitialSave(outbox);
			folderService.InitialSave(spambox);
			folderService.InitialSave(trashbox);
			
			return nutritionistRes;
		}else{
			nutritionist = nutritionistRepository.save(nutritionist);
			return nutritionist;
		}
	}
	
	public Nutritionist findOneByUserAccount(UserAccount principal) {
		// TODO Auto-generated method stub
		Assert.notNull(principal);
		
		Nutritionist result =  nutritionistRepository.findOneByUserAccount(principal);
		Assert.notNull(result);
		
		return result;
	}
	/*
	 *  NO PODEMOS ELIMINAR NUTRICIONISTAS
	 */
	 
	 public Nutritionist findOneByUserAccountId(int id) {
		
		Nutritionist n = nutritionistRepository.findOneByUserAccountId(id);
		Assert.notNull(n);
		return n;
	}
	
	public void assignFolder(Folder f, UserAccount userAccount){
		Nutritionist admin = findOneByUserAccountId(userAccount.getId());
		f.setActor(admin);
		admin.getFolders().add(f);
	}
	
}
