	package services;

	import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

import repositories.CampaignRepository;

import domain.Campaign;

	@Service
	@Transactional
	public class CampaignService {

		//Managed repository ------------------
		
		@Autowired
		private CampaignRepository campaignRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private ActorService actorService;
		
		@Autowired
		private AdministratorService administratorService;
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Campaign create() {
			actorService.checkSponsor();
			Campaign res;
			res = new Campaign();
			return res;

		}
		
		public Collection<Campaign> findAll(){
			Collection<Campaign>res;
			res=campaignRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		
		public Campaign findOne(int id){
			
			Assert.notNull(id);
			Campaign campaign = campaignRepository.findOne(id);
			Assert.notNull(campaign);
			return campaign;
		}
		

		public Campaign save(Campaign campaign) {
			actorService.checkCampaign(campaign);
			Assert.notNull(campaign);
			Campaign campaignRes = campaignRepository.save(campaign);
			return campaignRes;

		}

		public void delete(Campaign campaign) {
			actorService.checkCampaign(campaign);
			Date a= new Date();
			Assert.notNull(campaign);
			Assert.isTrue(campaign.getStartDate().before(a));
			Assert.isTrue(campaign.getId() != 0);
			Assert.isTrue(campaignRepository.exists(campaign.getId()));
			campaignRepository.delete(campaign);

		}
		
		//Other business methods---------------------
		
		public Integer minCampaign(){
			administratorService.checkPrincipal();
			Integer res;
			res=campaignRepository.minCampaign();
			Assert.notNull(res);
			return res;	
		}
		
		public Integer maxCampaign(){
			administratorService.checkPrincipal();
			Integer res;
			res=campaignRepository.maxCampaign();
			Assert.notNull(res);
			return res;
		}
		
		public Double avgCampaign(){
			administratorService.checkPrincipal();
			Double res;
			res=campaignRepository.avgCampaign();
			Assert.notNull(res);
			return res;
		}
		
		public Integer minActiveCampaign(){
			administratorService.checkPrincipal();
			Integer res;
			res=campaignRepository.minActiveCampaign();
			Assert.notNull(res);
			return res;	
		}
		
		public Integer maxActiveCampaign(){
			administratorService.checkPrincipal();
			Integer res;
			res=campaignRepository.maxActiveCampaign();
			Assert.notNull(res);
			return res;	
		}
		
		public Double avgActiveCampaign(){
			administratorService.checkPrincipal();
			Double res;
			res=campaignRepository.avgActiveCampaign();
			Assert.notNull(res);
			return res;	
		}
}

