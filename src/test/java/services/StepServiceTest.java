package services;


import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Step;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class StepServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	
		@Autowired
		private StepService stepService;
			
	//Test---------------------------------------------
		
		@Test
		public void testCreate(){
			authenticate("user1");
			Step step = stepService.create();
			Assert.notNull(step);
		}
		
		@Test
		public void testFindOne() {
			int stepId = 80;
			Step result = stepService.findOne(stepId);
			Assert.notNull(result);
			System.out.println(result.getNumber());
		}
		
		
		@Test
		public void testSave(){
			authenticate("user3");
			int stepId = 78;
			String newHints = "Sasonar";
			
			Step result = stepService.findOne(stepId);
			Assert.notNull(result);
			System.out.println(result.getHints());
			result.setHints(newHints);
			stepService.save(result);
			
			Step result2 = stepService.findOne(stepId);
			Assert.notNull(result2);
			System.out.println(result2.getHints());
			Assert.isTrue(result2.getHints()== newHints);
			
			
		}
		
		@Test
		public void testDelete(){
			authenticate("user3");
			int stepId = 78;
			
			Step result = stepService.findOne(stepId);
			Assert.notNull(result);
			System.out.println(result.getDescription());
			stepService.delete(result);
			
		}
		
		//Other bussiness tests-----------------------
		
		@Test
		public void testAverageStepsPerRecipe(){
			authenticate("admin1");
			Double average = stepService.averageStepsPerRecipe();
			System.out.println("La media es: ");
			System.out.println(average);
		}
		
		@Test
		public void testStandarDeviationStepsPerRecipe(){
			authenticate("admin1");
			Double desviation = stepService.standarDeviationStepsPerRecipe();
			System.out.println("La desviación es: ");
      		System.out.println(desviation);
		}
		

}
