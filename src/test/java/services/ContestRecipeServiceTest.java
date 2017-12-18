package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.ContestRecipe;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ContestRecipeServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
		@Autowired
		private ContestRecipeService contestRecipeService;

	//Test---------------------------------------------
		
		@Test
		public void testCreate(){
			ContestRecipe contestRecipe = contestRecipeService.create();
			Assert.notNull(contestRecipe);
			
		}
		
		@Test
		public void testSave(){
			int contestRecipeId = 132;
			String title = "Titulo de pruebas de copias de recetas";
			
			ContestRecipe result = contestRecipeService.findOne(contestRecipeId);
			Assert.notNull(result);
			System.out.println(result.getTitle());
			result.setTitle(title);
			contestRecipeService.save(result);
			
			ContestRecipe result2 = contestRecipeService.findOne(contestRecipeId);
			Assert.notNull(result2);
			System.out.println(result2.getTitle());
			Assert.isTrue(result2.getTitle()== title);
			
		}
		
		@Test
		public void testDelete(){
			int contestRecipeId = 132;
			
			ContestRecipe result = contestRecipeService.findOne(contestRecipeId);
			Assert.notNull(result);
			System.out.println(result.getTitle());
			contestRecipeService.delete(result);

		}
		
		@Test
		public void testFindOne() {
			int contestRecipeId = 132;
			ContestRecipe result = contestRecipeService.findOne(contestRecipeId);
			Assert.notNull(result);
			System.out.println(result.getTitle());
		}
		
		@Test
		public void testFindAll() {
			Collection<ContestRecipe> result = contestRecipeService.findAll();
			Assert.notNull(result);
			for(ContestRecipe c : result){
				System.out.println(c.getTitle());
			}
		}
}
