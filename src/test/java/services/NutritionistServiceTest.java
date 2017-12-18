package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Nutritionist;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class NutritionistServiceTest extends AbstractTest {

	// service run Test
	@Autowired
	private NutritionistService nutritionistService;
	
	//TEST -----------------
	
	@Test
	public void testCreate(){
		
		Nutritionist nutritionist = nutritionistService.create();
		Assert.notNull(nutritionist);
	}
	
	@Test
	public void testFindAllNutriotionists(){
		
		Collection<Nutritionist> nutritionists = nutritionistService.findAll();
		Assert.notNull(nutritionists);
	}
	
	@Test
	public void testFindOneNutritionist(){
		
		Nutritionist nutritionist = nutritionistService.findOne(27);
		Assert.notNull(nutritionist);
	}
	
	@Test
	public void testSaveNutritionist(){
		 Nutritionist nutritionist, saved;
		 nutritionist = nutritionistService.findOne(27);
		 
		 saved = nutritionistService.save(nutritionist);
		 
		 Collection<Nutritionist> nutritionists = nutritionistService.findAll();
		 Assert.isTrue(nutritionists.contains(saved));
	}
	
	/*
	 *  los nutricionistas no se pueden eliminar
	 */
}
