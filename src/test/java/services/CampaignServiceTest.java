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
import domain.Campaign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CampaignServiceTest extends AbstractTest{

	@Autowired
	private CampaignService campaignService;
	
	//TEST ----------
	
	@Test
	public void createTest(){
		authenticate("sponsor1");
		Campaign campaign = campaignService.create();
		Assert.notNull(campaign);
	}
	
	@Test
	public void testFindOne(){
		
		Campaign campaign = campaignService.findOne(58);
		Assert.notNull(campaign);
	}
	
	@Test
	public void testFindAll(){
		
		Collection<Campaign> campaigns = campaignService.findAll();
		Assert.notNull(campaigns);
		
	}
	
	@Test
	public void testSaveCampaign(){
		
		authenticate("sponsor3");
		
		Campaign campaign, saved;
		campaign = campaignService.findOne(58);
		
		saved = campaignService.save(campaign);
		
		Collection<Campaign> campaigns = campaignService.findAll();
		Assert.isTrue(campaigns.contains(saved));
		
	}
	
	public void testDelete(){
		authenticate("sponsor3");
		Campaign campaign = campaignService.findOne(58);
		campaignService.delete(campaign);
	}
	
	//Other business methods---------------------
	
	public void minCampaign(){
		authenticate("admin");
		Integer res;
		res=campaignService.minCampaign();
		Assert.notNull(res);
	}
	
	public void maxCampaign(){
		authenticate("admin");
		Integer res;
		res=campaignService.maxCampaign();
		Assert.notNull(res);
	}
	
	public void avgCampaign(){
		authenticate("admin");
		Double res;
		res=campaignService.avgCampaign();
		Assert.notNull(res);
	}
	
	public void minActiveCampaign(){
		authenticate("admin");
		Integer res;
		res=campaignService.minActiveCampaign();
		Assert.notNull(res);
	}
	
	public void maxActiveCampaign(){
		authenticate("admin");
		Integer res;
		res=campaignService.maxActiveCampaign();
		Assert.notNull(res);
	}
	
	public void avgActiveCampaign(){
		authenticate("admin");
		Integer res;
		res=campaignService.minCampaign();
		Assert.notNull(res);
	}
}
