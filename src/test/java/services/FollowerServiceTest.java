package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Follower;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class FollowerServiceTest extends AbstractTest{

	//Service under test-------------------------------
		@Autowired
		private FollowerService followerService;
		

		//Test---------------------------------------------
		
		@Test
		public void testCreate(){
			Follower follower = followerService.create();
			Assert.notNull(follower);
			
		}
		
		@Test
		public void testSave(){
			int followerId = 29;
			
			Follower result = followerService.findOne(followerId);
			Assert.notNull(result);
			followerService.save(result);
			
		}
		
		@Test
		public void testDelete(){
			int followerId = 29;
			
			Follower result = followerService.findOne(followerId);
			Assert.notNull(result);
			followerService.delete(result);

		}
		
		@Test
		public void testFindOne() {
			int followerId = 29;
			Follower result = followerService.findOne(followerId);
			Assert.notNull(result);
		}
		
		@Test
		public void testFindAll() {
			Collection<Follower> result = followerService.findAll();
			Assert.notNull(result);
		}
}
