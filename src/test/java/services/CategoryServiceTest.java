package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Category;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CategoryServiceTest extends AbstractTest{

	//Service under test-------------------------------
		@Autowired
		private CategoryService categoryService;

		//Test---------------------------------------------
		
		@Test
		public void testCreate(){
			authenticate("admin1");
			Category c = categoryService.create();
			Assert.notNull(c);
			
		}
		
		@Test
		public void testSave() {
			authenticate("admin1");
			System.out.println("-------------------testSave-------------------");
			int CategoryId = 72;
			String name = "NombreNuevo";

			Category a = categoryService.findOne(CategoryId);
			System.out.println("Before saving" + ":" + " " + a.getName());
			a.setName(name);
			categoryService.save(a);

			Category newac = categoryService.findOne(CategoryId);
			System.out.println("After saving" + ":" + " " + newac.getName());
			Assert.isTrue(newac.getName() == name);
			System.out.println("------------------------------------------------");

		}

		@Test
		public void testDelete() {
			authenticate("admin1");
			System.out.println("-------------------testDelete-------------------");
			Category b = categoryService.findOne(75);
			Collection<Category> all1;
			all1 = categoryService.findAll();
			System.out.println("Before deleting"+":"+" "+all1);

			categoryService.delete(b);

			Collection<Category> all2;
			all2 = categoryService.findAll();
			System.out.println("After deleting"+":"+" "+all2);
			System.out.println("------------------------------------------------");
		}

		@Test
		public void testFindOne() {
			int categoryId = 72;
			Category result = categoryService.findOne(categoryId);
			System.out.println(result.getName());
			Assert.notNull(result);
		}

		@Test
		public void testFindAll() {
			Collection<Category> result;
			result = categoryService.findAll();
			Assert.notNull(result);
		}
	
}
