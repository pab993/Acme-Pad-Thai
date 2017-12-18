package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import domain.ConfigurationSystem;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ConfigurationSystemTest extends AbstractTest{
	
	//Service under test-------------------------------
	@Autowired
	private ConfigurationSystemService configurationSystemService;

	//Test---------------------------------------------

	@Test
	public void testCreate(){
		authenticate("admin1");
		ConfigurationSystem cs = configurationSystemService.create();
		Assert.notNull(cs);
	}

	@Test
	public void testSave() {
		authenticate("admin1");
		System.out.println("-------------------testSave-------------------");
		int CommentId = 135;
		Double fee = 2.1;

		ConfigurationSystem a = configurationSystemService.findOne(CommentId);
		System.out.println("Before saving" + ":" + " " + a.getFee());
		a.setFee(fee);
		configurationSystemService.save(a);

		ConfigurationSystem newac = configurationSystemService.findOne(CommentId);
		System.out.println("After saving" + ":" + " " + newac.getFee());
		Assert.isTrue(newac.getFee() == fee);
		System.out.println("------------------------------------------------");

	}
	@Test
	public void testDelete() {
		authenticate("admin1");
		System.out.println("-------------------testDelete-------------------");
		ConfigurationSystem b = configurationSystemService.findOne(135);
		Collection<ConfigurationSystem> all1;
		all1 = configurationSystemService.findAll();
		System.out.println("Before deleting"+":"+" "+all1);

		configurationSystemService.delete(b);

		Collection<ConfigurationSystem> all2;
		all2 = configurationSystemService.findAll();
		System.out.println("After deleting"+":"+" "+all2);
		System.out.println("------------------------------------------------");
	}

	@Test
	public void testFindOne() {
		int commentId = 135;
		ConfigurationSystem result = configurationSystemService.findOne(commentId);
		System.out.println(result.getFee());
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<ConfigurationSystem> result;
		result = configurationSystemService.findAll();
		Assert.notNull(result);
	}

}