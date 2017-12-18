package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Recipe;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class RecipeServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	@Autowired
	private RecipeService recipeService;

	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		authenticate("user1");
		Recipe c = recipeService.create();
		Assert.notNull(c);
		
	}
	
	@Test
	public void testSave() {
		authenticate("user1");
		System.out.println("-------------------testSave-------------------");
		int RecipeId = 76;
		String title = "TituloNuevo";
		long l = 10;
		Date d = new Date(System.currentTimeMillis()- l);
		

		Recipe a = recipeService.findOne(RecipeId);
		System.out.println("Before saving" + ":" + " " + a.getTitle());
		System.out.println("Before saving" + ":" + " " + a.getMomentLastUpdate());
		a.setTitle(title);
		a.setMomentLastUpdate(d);
		recipeService.save(a);

		Recipe newac = recipeService.findOne(RecipeId);
		System.out.println("After saving" + ":" + " " + newac.getTitle());
		System.out.println("After saving" + ":" + " " + newac.getMomentLastUpdate());
		Assert.isTrue(newac.getTitle() == title);
		System.out.println("------------------------------------------------");

	}

	@Test
	public void testDelete() {
		authenticate("user3");
		System.out.println("-------------------testDelete-------------------");
		Recipe b = recipeService.findOne(76);
		Collection<Recipe> all1;
		all1 = recipeService.findAll();
		System.out.println("Before deleting"+":"+" "+all1);

		recipeService.delete(b);

	}

	@Test
	public void testFindOne() {
		int RecipeId = 84;
		Recipe result = recipeService.findOne(RecipeId);
		System.out.println(result.getLikes());
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Recipe> result;
		result = recipeService.findAll();
		Assert.notNull(result);
	}
	
	//Other bussines methods------------------------
	
	@Test
	public void testMinimumNumberRecipesPerUser(){
		authenticate("admin1");
		Integer minimum = recipeService.MinimumNumberRecipesPerUser();
		System.out.println("El mínimo es: ");
		System.out.println(minimum);
	}
	
	@Test
	public void testMaximumNumberRecipesPerUser(){
		authenticate("admin1");
		Integer maximum = recipeService.MaximumNumberRecipesPerUser();
		System.out.println("El máximo es: ");
		System.out.println(maximum);
	}
	
	@Test
	public void testAverageNumberRecipesPerUser(){
		authenticate("admin1");
		Double average = recipeService.AverageNumberRecipesPerUser();
		System.out.println("La media es: ");
		System.out.println(average);
	}
	
	@Test
	public void testRecipeSearch(){
		String ticker = "537121-djts";
		Collection<Recipe> recipe = recipeService.RecipeSearch(ticker);
		System.out.println("Recetas obtenidas: ");
		System.out.println(recipe);
	}
	
	@Test
	public void testShowRecipesByCategory(){
		Collection<Recipe> recipes = recipeService.ShowRecipesByCategory();
		System.out.println("Las recetas ordenadas por categoría: ");
		for(Recipe recipe : recipes){
			System.out.println(recipe);
		}
	}
	
}
