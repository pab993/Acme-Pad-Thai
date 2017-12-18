package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Qualification;
import domain.Recipe;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class QualificationServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	@Autowired
	private QualificationService qualificationService;

	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		Qualification c = qualificationService.create();
		Assert.notNull(c);
		
	}
	
	@Test
	public void testSave() {
		System.out.println("-------------------testSave-------------------");
		int QualificationId = 129;
		Recipe b=new Recipe();
		Qualification a = qualificationService.findOne(QualificationId);
		System.out.println("Before saving" + ":" + " " + a.getRecipe());
		a.setRecipe(b);
		qualificationService.save(a);

		Qualification newac = qualificationService.findOne(QualificationId);
		System.out.println("After saving" + ":" + " " + newac.getRecipe());
		Assert.isTrue(newac.getRecipe() == b);
		System.out.println("------------------------------------------------");

	}

	@Test
	public void testDelete() {
		System.out.println("-------------------testDelete-------------------");
		Qualification b = qualificationService.findOne(129);
		Collection<Qualification> all1;
		all1 = qualificationService.findAll();
		System.out.println("Before deleting"+":"+" "+all1);

		qualificationService.delete(b);

		Collection<Qualification> all2;
		all2 = qualificationService.findAll();
		System.out.println("After deleting"+":"+" "+all2);
		System.out.println("------------------------------------------------");
	}

	@Test
	public void testFindOne() {
		int QualificationId = 129;
		Qualification result = qualificationService.findOne(QualificationId);
		System.out.println(result.getRecipe());
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Qualification> result;
		result = qualificationService.findAll();
		Assert.notNull(result);
	}
}
