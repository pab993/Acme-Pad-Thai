package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Possesion;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class PossesionServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	@Autowired
	private PossesionService possesionService;

	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		Possesion c = possesionService.create();
		Assert.notNull(c);
		
	}
	
	@Test
	public void testSave() {
		System.out.println("-------------------testSave-------------------");
		int possesionId = 119;
		Integer quantity = 1;

		Possesion a = possesionService.findOne(possesionId);
		System.out.println("Before saving" + ":" + " " + a.getQuantity());
		a.setQuantity(quantity);
		possesionService.save(a);

		Possesion newac = possesionService.findOne(possesionId);
		System.out.println("After saving" + ":" + " " + newac.getQuantity());
		Assert.isTrue(newac.getQuantity() == quantity);
		System.out.println("------------------------------------------------");

	}

	@Test
	public void testDelete() {
		System.out.println("-------------------testDelete-------------------");
		Possesion b = possesionService.findOne(119);
		Collection<Possesion> all1;
		all1 = possesionService.findAll();
		System.out.println("Before deleting"+":"+" "+all1);

		possesionService.delete(b);

		Collection<Possesion> all2;
		all2 = possesionService.findAll();
		System.out.println("After deleting"+":"+" "+all2);
		System.out.println("------------------------------------------------");
	}

	@Test
	public void testFindOne() {
		int possesionId = 119;
		Possesion result = possesionService.findOne(possesionId);
		System.out.println(result.getQuantity());
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Possesion> result;
		result = possesionService.findAll();
		Assert.notNull(result);
	}
}
