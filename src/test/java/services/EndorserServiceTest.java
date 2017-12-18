package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Endorser;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class EndorserServiceTest extends AbstractTest{

	//Service under test-------------------------------
			@Autowired
			private EndorserService endorserService;
			

	//Test---------------------------------------------
			
			@Test
			public void testCreate(){
				authenticate("nutritionist1");
				Endorser endorser = endorserService.create();
				Assert.notNull(endorser);
				
			}
			
			@Test
			public void testSave(){
				authenticate("nutritionist1");
				int endorserId = 21;
				String name = "Titulo de pruebas de endorser";
				
				Endorser result = endorserService.findOne(endorserId);
				Assert.notNull(result);
				System.out.println(result.getName());
				result.setName(name);
				endorserService.save(result);
				
				Endorser result2 = endorserService.findOne(endorserId);
				Assert.notNull(result2);
				System.out.println(result2.getName());
				Assert.isTrue(result2.getName()== name);
				
			}
			
			@Test
			public void testDelete(){
				authenticate("nutritionist1");
				int endorserId = 21;
				
				Endorser result = endorserService.findOne(endorserId);
				Assert.notNull(result);
				System.out.println(result.getName());
				endorserService.delete(result);

			}
			
			@Test
			public void testFindOne() {
				int endorserId = 21;
				Endorser result = endorserService.findOne(endorserId);
				Assert.notNull(result);
				System.out.println(result.getName());
			}
			
			@Test
			public void testFindAll() {
				Collection<Endorser> result = endorserService.findAll();
				Assert.notNull(result);
				for(Endorser e : result){
					System.out.println(e.getName());
				}
			}
	
}
