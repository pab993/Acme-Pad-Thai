package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.UnitSystem;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class UnitSystemServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	
			@Autowired
			private UnitSystemService unitSystemService;
				
	//Test---------------------------------------------
			
			@Test
			public void testCreate(){
				UnitSystem unitSystem = unitSystemService.create();
				Assert.notNull(unitSystem);

			}
			
			
			@Test
			public void testSave(){
				int unitSystemId = 105;
				String unit = "pounds";
				UnitSystem u = unitSystemService.findOne(unitSystemId);
				Assert.notNull(u);
				System.out.println(u.getUnit());
				u.setUnit(unit);
				unitSystemService.save(u);
				UnitSystem u2 = unitSystemService.findOne(unitSystemId);
				Assert.notNull(u2);
				System.out.println(u.getUnit());
				u2.setUnit("Pieces");
				unitSystemService.save(u2);
			}
			
			@Test
			public void testFindOne(){
				UnitSystem result;
				int UnitSystemid = 105;
				
				result = unitSystemService.findOne(UnitSystemid);
				Assert.notNull(result);
				System.out.println(result.getUnit());
				
			}
			
			@Test
			public void testDelete(){
				int unitId = 105;
				
				UnitSystem result = unitSystemService.findOne(unitId);
				Assert.notNull(result);
				System.out.println(result.getUnit());
				unitSystemService.delete(result);
				System.out.println("Deleted");
				
				
			}
			
}
