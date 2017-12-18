package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Sponsor;

import repositories.SponsorRepository;
import security.UserAccount;
import security.Authority;

@Service
@Transactional
public class SponsorService {

	//Managed Repositories--------------------------
	
	@Autowired
	private SponsorRepository sponsorRepository;
				
	//Supporting Services---------------------------
	
	@Autowired
	private AdministratorService administratorService;
				
	//Methods CRUDS---------------------------------
	
	public Sponsor create(){
		UserAccount sp = new UserAccount();
		Sponsor res = new Sponsor();
		Collection<Authority>au = new ArrayList<Authority>();
		Authority i = new Authority();
		i.setAuthority("SPONSOR");
		au.add(i);
		sp.setAuthorities(au);
		res.setUserAccount(sp);
		
		return res;
		
	}
	
	public Sponsor save(Sponsor s){
		Assert.notNull(s);
		String password = s.getUserAccount().getPassword();
		Md5PasswordEncoder encoder= new Md5PasswordEncoder();
		String md5 =encoder.encodePassword(password, null);
		s.getUserAccount().setPassword(md5);
		Sponsor sponsorRes = sponsorRepository.save(s);
		return sponsorRes;
	}
	
	
	public Sponsor findOne(int sponsorId){
		
		Sponsor result;
		Assert.notNull(sponsorId);
		
		result = sponsorRepository.findOne(sponsorId);
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Sponsor> findAll(){
		Collection<Sponsor>res;
		res=sponsorRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	//Other business methods-------------------------

	public Sponsor findBySponsorAccountId(int s){
		Sponsor res;
		res=sponsorRepository.findBySponsorAccountId(s);
		Assert.notNull(res);
		return res;
		
	}
	
	public Collection<String> companiesThatHaveSpentLessThanTheAverage(){
		administratorService.checkPrincipal();
		Collection<String> res;
		res = sponsorRepository.companiesThatHaveSpentLessThanTheAverage();
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Sponsor> inactiveSponsors(){
		administratorService.checkPrincipal();
		Collection<Sponsor> res;
		res = sponsorRepository.inactiveSponsors();
		Assert.notNull(res);
		return res;
	}
	
	public Collection<String> companiesNinetyPerCent(){
		administratorService.checkPrincipal();
		Collection<String> res;
		res = sponsorRepository.companiesNinetyPerCent();
		Assert.notNull(res);
		return res;
	}

	public Collection<String> rankingOfCompaniesByCampaigns(){
		administratorService.checkPrincipal();
		Collection<String> res;
		res = sponsorRepository.rankingOfCompaniesByCampaigns();
		Assert.notNull(res);
		return res;
	}
	
	public Collection<String> rankingOfCompaniesByBills(){
		administratorService.checkPrincipal();
		Collection<String> res;
		res = sponsorRepository.rankingOfCompaniesByBills();
		Assert.notNull(res);
		return res;
	}
}
