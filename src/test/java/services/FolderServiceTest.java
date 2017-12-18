package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Folder;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class FolderServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;

	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		actorService.check();
		Folder folder = folderService.create();
		Assert.notNull(folder);
		
	}
	
	@Test
	public void testSave(){
		authenticate("user1");
		int folderId = 34;
		String name = "Titulo de pruebas de un folder";
		
		Folder result = folderService.findOne(folderId);
		Assert.notNull(result);
		System.out.println(result.getName());
		result.setName(name);
		folderService.save(result);
		
		Folder result2 = folderService.findOne(folderId);
		Assert.notNull(result2);
		System.out.println(result2.getName());
		Assert.isTrue(result2.getName()== name);
		
	}
	
	@Test
	public void testDelete(){
		authenticate("user1");
		int folderId = 34;
		
		Folder result = folderService.findOne(folderId);
		Assert.notNull(result);
		System.out.println(result.getName());
		folderService.delete(result);

	}
	
	@Test
	public void testFindOne() {
		int folderId = 34;
		Folder result = folderService.findOne(folderId);
		Assert.notNull(result);
		System.out.println(result.getName());
	}
	
	@Test
	public void testFindAll() {
		Collection<Folder> result = folderService.findAll();
		Assert.notNull(result);
		for(Folder e : result){
			System.out.println(e.getName());
		}
	}

}
