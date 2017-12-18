package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Ingredient;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class IngredientServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	@Autowired
	private IngredientService ingredientService;
	
	//Other services----------------------------------

	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		authenticate("nutritionist1");
		Ingredient c = ingredientService.create();
		Assert.notNull(c);
		
	}
	
	@Test
	public void testSave() {
		authenticate("nutritionist1");
		System.out.println("-------------------testSave-------------------");
		int ingredientId = 97;
		String title = "TituloNuevo";

		Ingredient a = ingredientService.findOne(ingredientId);
		System.out.println("Before saving" + ":" + " " + a.getName());
		a.setName(title);
		ingredientService.save(a);

		Ingredient newac = ingredientService.findOne(ingredientId);
		System.out.println("After saving" + ":" + " " + newac.getName());
		Assert.isTrue(newac.getName() == title);
		System.out.println("------------------------------------------------");

	}

	@Test
	public void testDelete() {
		authenticate("nutritionist1");
		System.out.println("-------------------testDelete-------------------");
		Ingredient b = ingredientService.findOne(104);
		Collection<Ingredient> all1;
		all1 = ingredientService.findAll();
		System.out.println("Before deleting"+":"+" "+all1);

		ingredientService.delete(b);

		Collection<Ingredient> all2;
		all2 = ingredientService.findAll();
		System.out.println("After deleting"+":"+" "+all2);
		System.out.println("------------------------------------------------");
	}

	@Test
	public void testFindOne() {
		int ingredientId = 97;
		Ingredient result = ingredientService.findOne(ingredientId);
		System.out.println(result.getName());
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Ingredient> result;
		result = ingredientService.findAll();
		Assert.notNull(result);
	}
	
	//Other bussiness methods-----------------
	
	@Test
	public void testAverageIngredientsPerRecipe(){
		authenticate("admin1");
		Double res = ingredientService.averageIngredientsPerRecipe();
		System.out.println("La media es: ");
		System.out.println(res);
	}
	
	@Test
	public void testStandarDesviationIngredientsPerRecipe(){
		authenticate("admin1");
		Double res = ingredientService.standarDeviationIngredientsPerRecipe();
		System.out.println("La desviación es: ");
		System.out.println(res);
	}
	
}
