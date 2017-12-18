package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import domain.Contest;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ContestServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	
	@Autowired
	private ContestService contestService;
	
	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		authenticate("admin1");
		Contest contest = contestService.create();
		Assert.notNull(contest);
	}
	
	@Test
	public void testFindOne() {
		int contestId = 126;
		Contest result = contestService.findOne(contestId);
		Assert.notNull(result);
		System.out.println(result.getTitle());
	}
	
	@Test
	public void testFindAll() {
		Collection<Contest> result = contestService.findAll();
		Assert.notNull(result);
		for(Contest c : result){
			System.out.println(c.getTitle());
		}
	}
	
	@Test
	public void testSave(){
		authenticate("admin1");
		int contestId = 126;
		String title = "Titulo de pruebas";
		
		Contest result = contestService.findOne(contestId);
		Assert.notNull(result);
		System.out.println(result.getTitle());
		result.setTitle("Titulo de pruebas");
		contestService.save(result);
		
		Contest result2 = contestService.findOne(contestId);
		Assert.notNull(result2);
		System.out.println(result2.getTitle());
		Assert.isTrue(result2.getTitle()== title);
		
		
	}
	
	@Test
	public void testDelete(){
		authenticate("admin1");
		int itemId = 128;
		
		Contest result = contestService.findOne(itemId);
		Assert.notNull(result);
		System.out.println(result.getTitle());
		contestService.delete(result);
		
	} 
	
	@Test
	public void testMinimumNumberRecipesQualified(){
		authenticate("admin1");
		Integer minimum = contestService.minimumNumberRecipesQualified();
		System.out.println("El mínimo es: ");
		System.out.println(minimum);
	}
	
	@Test
	public void testMaximumNumberRecipesQualified(){
		authenticate("admin1");
		Integer maximum = contestService.maximumNumberRecipesQualified();
		System.out.println("El máximo es: ");
		System.out.println(maximum);
	}
	
	@Test
	public void testAverageNumberRecipesQualified(){
		authenticate("admin1");
		Integer average = contestService.maximumNumberRecipesQualified();
		System.out.println("El máximo es: ");
		System.out.println(average);
	}
	
	@Test
	public void testContestWithMoreRecipesQualified(){
		authenticate("admin1");
		Collection<Contest> moreQualified = contestService.contestWithMoreRecipesQualified();
		System.out.println("El concurso con más recetas es: ");
		for(Contest c : moreQualified){
			System.out.println(c);
		}
	}
	
}
