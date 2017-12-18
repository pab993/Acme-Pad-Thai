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
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class UserServiceTest extends AbstractTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testCreate(){
		User user = userService.create();
		Assert.notNull(user);
	}
	
	@Test
	public void testFindAll(){
		
		Collection<User> users = userService.findAll();
		Assert.notEmpty(users);
	}
	
	@Test
	public void testFindOne(){
		
		User user = userService.findOne(15);
		Assert.notNull(user);
	}
	
	@Test
	public void testSave(){
		
		User user, saved;
		
		user = userService.findOne(15);
		saved = userService.save(user);
		
		Collection<User> users = userService.findAll();
		Assert.isTrue(users.contains(saved));
	}
	
	@Test
	public void testDelete(){
		
		User user = userService.findOne(15);
		userService.delete(user);
	}
	
	//Other business methods----------------------
	
	public void testUserSearch(){
		String s = "User1";
		Collection<User> res = userService.UserSearch(s);
		Assert.notNull(res);
	}
	
	public void testUserWhoHaveAuthoredMoreRecipes(){
		authenticate("admin1");
		Collection<User> res = userService.userWhoHaveAuthoredMoreRecipes();
		Assert.notNull(res);
	}
	
	public void TestUsersMorePopulars(){
		authenticate("admin1");
		Collection<User> res = userService.UsersMorePopulars();
		Assert.notNull(res);
	}
	
	public void TestUsersRegardingAverageLikesAndDislikes(){
		authenticate("admin1");
		Collection<User> res = userService.UsersRegardingAverageLikesAndDislikes();
		Assert.notNull(res);
	}
	
	
}
