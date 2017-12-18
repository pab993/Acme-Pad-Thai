package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;

import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SponsorServiceTest extends AbstractTest{

	//Service under test-------------------------------
	
	@Autowired
	private SponsorService sponsorService;
		
	//Test---------------------------------------------
		
	@Test
	public void testCreate(){
		Sponsor sponsor = sponsorService.create();
		Assert.notNull(sponsor);
	}
	
	@Test
	public void testSave(){
		int sponsorId = 24;
		Sponsor sponsor = sponsorService.findOne(sponsorId);
		Assert.notNull(sponsor);
		sponsorService.save(sponsor);
	}
	
	
	@Test
	public void testFindOne(){
		int sponsorId = 25;
		Sponsor sponsor = sponsorService.findOne(sponsorId);
		Assert.notNull(sponsor);
		System.out.println(sponsor.getName());
	}
	
	@Test
	public void testFindAll(){
		Collection<Sponsor> sponsors = sponsorService.findAll();
		Assert.notNull(sponsors);
		for(Sponsor s : sponsors){
			System.out.println(s.getName());
		}
	}
	
	//Other business methods tests---------------
	@Test
	public void testFindByUserAccountId(){
		int ua = 24;
		Sponsor sponsor = sponsorService.findBySponsorAccountId(ua);
		Assert.notNull(sponsor);
	}
	
	@Test
	public void testCompaniesThatHaveSpentLessThanTheAverage(){
		authenticate("admin1");
		Collection<String> companies;
		companies = sponsorService.companiesThatHaveSpentLessThanTheAverage();
		Assert.notNull(companies);
		for(String c: companies){
			System.out.println(c);
			
		}
		
	}
	
	@Test
	public void testInactiveSponsors(){
		authenticate("admin1");
		Collection<Sponsor> sponsors;
		sponsors = sponsorService.inactiveSponsors();
		Assert.notNull(sponsors);
		for(Sponsor s: sponsors){
			System.out.println(s);
			
		}
	}
	
	@Test
	public void testCompaniesNinetyPerCent(){
		authenticate("admin1");
		Collection<String> companies;
		companies = sponsorService.companiesNinetyPerCent();
		Assert.notNull(companies);
		for(String s: companies){
			System.out.println(s);
			
		}
	}
	
	@Test
	public void testRankingOfCompaniesByCampaigns(){
		authenticate("admin1");
		Collection<String> companies;
		companies = sponsorService.rankingOfCompaniesByCampaigns();
		Assert.notNull(companies);
		for(String s: companies){
			System.out.println(s);
			
		}
	}
	
	@Test
	public void testRankingOfCompaniesByBills(){
		authenticate("admin1");
		Collection<String> companies;
		companies = sponsorService.rankingOfCompaniesByBills();
		Assert.notNull(companies);
		for(String s: companies){
			System.out.println(s);
			
		}
	}
	
}
