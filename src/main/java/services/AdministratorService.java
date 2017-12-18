package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Folder;

@Service
@Transactional

public class AdministratorService {

	public AdministratorService(){
		super();
	}
	
	//Managed repository --------------
	@Autowired
	private AdministratorRepository administratorRepository;
		
	//Supporting services--------------
	
		
	//Methods CRUDs ------------------
	
	public Administrator create(){
		
		UserAccount account = new UserAccount();
		
		Collection<Authority> authorities = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setAuthority("ADMIN");
		authorities.add(authority);
		account.setAuthorities(authorities);
		
		Administrator administrator = new Administrator();
		administrator.setUserAccount(account);
		
		return administrator;
	}
	
	public Collection<Administrator> findAll(){
		
		Collection<Administrator> administrators = administratorRepository.findAll();
		Assert.notNull(administrators);
		return administrators;
	}
	
	public Administrator findOne(int id){
		
		Assert.notNull(id);
		Administrator a = administratorRepository.findOne(id);
		Assert.notNull(a);
		return a;
	}
	
	public Administrator save(Administrator a){
		
		Assert.notNull(a);
		String hashCode = new Md5PasswordEncoder().
				encodePassword(a.getUserAccount().getPassword(), null);
		
		a.getUserAccount().setPassword(hashCode);
		Administrator administratorRes = administratorRepository.save(a);
		return administratorRes;
	}
	
	public void delete(Administrator a){
		checkPrincipal();
		Assert.isTrue(administratorRepository.exists(a.getId()));
		administratorRepository.delete(a);
	}
	
	//Other business Methods----------------------
	
	public void checkPrincipal() {
		
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
	}

	public Administrator findOneByuserAccountId(int id){
		Assert.notNull(id);
		Administrator result = administratorRepository.findOneByuserAccountId(id);
		Assert.notNull(result);
		return result;
	}

	public void assignFolder(Folder f, UserAccount userAccount){
		Administrator admin = findOneByuserAccountId(userAccount.getId());
		f.setActor(admin);
		admin.getFolders().add(f);
	}

}
