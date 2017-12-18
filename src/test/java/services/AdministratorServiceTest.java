package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})

@Transactional
public class AdministratorServiceTest extends AbstractTest {

	//Service under test
	@Autowired
	private AdministratorService administratorService;
	
	@Test 
	public void testCreateAdministrator(){
		Administrator a = administratorService.create();
		Assert.notNull(a);
	}
	
	@Test
	public void testSaveAdministrator(){
		
		Administrator administrator, saved;
		Collection<Administrator> administrators;
		
		// almaceno administrator
		administrator = administratorService.findOne(13);
		saved = administratorService.save(administrator);
		//compruebo todo OK
		administrators = administratorService.findAll();
		Assert.isTrue(administrators.contains(saved));
	}
	
	@Test
	public void testFindAll(){
		
		Collection<Administrator> administrators = administratorService.findAll();
		Assert.notNull(administrators, "no se encontraron administradores");
	}
	
	@Test 
	public void testFindOne(){
		
		Administrator administrator = administratorService.findOne(13);
		Assert.notNull(administrator, "no se ha encontrado administrador");
	}
	
}
