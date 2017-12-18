package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Comment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CommentServiceTest extends AbstractTest{
	
	//Service under test-------------------------------
	@Autowired
	private CommentService commentService;

	//Test---------------------------------------------
	
	@Test
	public void testCreate(){
		authenticate("user1");
		Comment c = commentService.create();
		Assert.notNull(c);
		
	}
	
	@Test
	public void testSave() {
		authenticate("user1");
		System.out.println("-------------------testSave-------------------");
		int CommentId = 77;
		String title = "TituloNuevo";

		Comment a = commentService.findOne(CommentId);
		System.out.println("Before saving" + ":" + " " + a.getTitle());
		a.setTitle(title);
		commentService.save(a);

		Comment newac = commentService.findOne(CommentId);
		System.out.println("After saving" + ":" + " " + newac.getTitle());
		Assert.isTrue(newac.getTitle() == title);
		System.out.println("------------------------------------------------");

	}


	@Test
	public void testFindOne() {
		int commentId = 77;
		Comment result = commentService.findOne(commentId);
		System.out.println(result.getTitle());
		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Comment> result;
		result = commentService.findAll();
		Assert.notNull(result);
	}
}
