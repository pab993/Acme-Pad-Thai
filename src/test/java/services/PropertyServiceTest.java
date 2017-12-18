package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Property;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class PropertyServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
		@Autowired
		private PropertyService propertyService;
		
		//Other services----------------------------------

		//Test---------------------------------------------
		
		@Test
		public void testCreate(){
			authenticate("nutritionist1");
			Property p = propertyService.create();
			Assert.notNull(p);
			
		}
		
		@Test
		public void testSave() {
			authenticate("nutritionist1");
			System.out.println("-------------------testSave-------------------");
			int propertyId = 118;
			String name = "NombreNuevo";

			Property p = propertyService.findOne(propertyId);
			System.out.println("Before saving" + ":" + " " + p.getName());
			p.setName(name);
			propertyService.save(p);

			Property newac = propertyService.findOne(propertyId);
			System.out.println("After saving" + ":" + " " + newac.getName());
			Assert.isTrue(newac.getName() == name);
			System.out.println("------------------------------------------------");

		}

		@Test
		public void testDelete() {
			authenticate("nutritionist1");
			System.out.println("-------------------testDelete-------------------");
			Property b = propertyService.findOne(118);
			Collection<Property> all1;
			all1 = propertyService.findAll();
			System.out.println("Before deleting"+":"+" "+all1);

			propertyService.delete(b);

			Collection<Property> all2;
			all2 = propertyService.findAll();
			System.out.println("After deleting"+":"+" "+all2);
			System.out.println("------------------------------------------------");
		}

		@Test
		public void testFindOne() {
			int propertyId = 118;
			Property result = propertyService.findOne(propertyId);
			System.out.println(result.getName());
			Assert.notNull(result);
		}

		@Test
		public void testFindAll() {
			Collection<Property> result;
			result = propertyService.findAll();
			Assert.notNull(result);
		}
		
		//Other bussiness methods-----------------
		
}
