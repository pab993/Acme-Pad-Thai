package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CurriculumServiceTest extends AbstractTest {

	//service under test
	@Autowired
	private CurriculumService curriculumService;
	
	//TEST -----
	
	@Test
	public void testCreateCurriculum(){
		authenticate("nutritionist1");
		Curriculum curriculum = curriculumService.create();
		Assert.notNull(curriculum);
	}
	
	@Test
	public void testFindOne(){
		Curriculum curriculum = curriculumService.findOne(20);
		Assert.notNull(curriculum);
	}
	
	@Test
	public void testFindAll(){		
		Collection<Curriculum> curriculums = curriculumService.findAll();
		Assert.notNull(curriculums);
		
	}
	
	@Test
	public void testSaveCurriculum(){
		authenticate("nutritionist2");
		
		Curriculum curriculum, saved;
		
		curriculum = curriculumService.findOne(20);
		saved = curriculumService.save(curriculum);
		
		Collection<Curriculum> curriculums = curriculumService.findAll();
		Assert.isTrue(curriculums.contains(saved));
	
	}
	
	@Test
	public void testDeleteCurriculum(){
		
		authenticate("nutritionist2");
		
		Curriculum curriculum = curriculumService.findOne(20);;
		curriculumService.delete(curriculum);

	}
}
