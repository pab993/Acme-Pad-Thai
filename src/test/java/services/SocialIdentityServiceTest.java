package services;


import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.SocialIdentity;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SocialIdentityServiceTest extends AbstractTest{

	//Service under test-------------------------------
	
		@Autowired
		private SocialIdentityService socialIdentityService;
		
	//Test---------------------------------------------
		
		@Test
		public void testCreate(){
			authenticate("nutritionist1");
			SocialIdentity socialIdentity = socialIdentityService.create();
			Assert.notNull(socialIdentity);
		}
		
		@Test
		public void testFindOne() {
			int socialIdentityId = 67;
			SocialIdentity result = socialIdentityService.findOne(socialIdentityId);
			Assert.notNull(result);
			System.out.println(result.getNick());
		}
		
		
		@Test
		public void testSave(){
			authenticate("user1");
			int socialIdentityId = 67;
			String newNick = "JonnyElTuerceEsquinas";
			
			SocialIdentity result = socialIdentityService.findOne(socialIdentityId);
			Assert.notNull(result);
			System.out.println(result.getNick());
			result.setNick(newNick);
			socialIdentityService.save(result);
			
			SocialIdentity result2 = socialIdentityService.findOne(socialIdentityId);
			Assert.notNull(result2);
			System.out.println(result2.getNick());
			Assert.isTrue(result2.getNick()== newNick);
			
			
		}
		
		@Test
		public void testDelete(){
			authenticate("admin2");
			int socialIdentityId = 69;
			
			SocialIdentity result = socialIdentityService.findOne(socialIdentityId);
			Assert.notNull(result);
			System.out.println(result.getNick());
			socialIdentityService.delete(result);
			
		}
}
