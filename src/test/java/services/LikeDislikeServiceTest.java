package services;


import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.LikeDislike;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class LikeDislikeServiceTest extends AbstractTest{

	//Service under test-------------------------------
	
	@Autowired
	private LikeDislikeService likeDislikeService;
		
	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		LikeDislike likeDislike = likeDislikeService.create();
		Assert.notNull(likeDislike);

	}
	
	
	@Test
	public void testSave(){
		int likeDislikeId = 90;
		Boolean likeDislike = true;
		LikeDislike ld = likeDislikeService.findOne(likeDislikeId);
		Assert.notNull(ld);
		System.out.println(ld.getLikeOrDislike());
		ld.setLikeOrDislike(likeDislike);
		likeDislikeService.save(ld);
		LikeDislike ld2 = likeDislikeService.findOne(likeDislikeId);
		Assert.notNull(ld2);
		System.out.println(ld.getLikeOrDislike());
		ld2.setLikeOrDislike(false);
		likeDislikeService.save(ld2);
	}
	
	@Test
	public void testFindOne(){
		LikeDislike result;
		int likeDislikeid = 90;
		
		result = likeDislikeService.findOne(likeDislikeid);
		Assert.notNull(result);
		System.out.println(result.getLikeOrDislike());
		
	}
	
	@Test
	public void testDelete(){
		int likeDislikeId = 90;
		
		LikeDislike result = likeDislikeService.findOne(likeDislikeId);
		Assert.notNull(result);
		System.out.println(result.getLikeOrDislike());
		likeDislikeService.delete(result);
		System.out.println("Deleted");
		
		
	}
}
