package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Message;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class MessageServiceTest extends AbstractTest{

			//Service under test-------------------------------
			@Autowired
			private MessageService messageService;

			//Test---------------------------------------------
			
			@Test
			public void testCreate(){
				authenticate("admin1");
				Message m = messageService.create();
				Assert.notNull(m);
				
			}
			
			@Test
			public void testSave() {
				authenticate("user1");
				System.out.println("-------------------testSave-------------------");
				int messageId = 65;
				String subject = "SubjectNuevo";

				Message m = messageService.findOne(messageId);
				System.out.println("Before saving" + ":" + " " + m.getSubject());
				m.setSubject(subject);
				messageService.save(m);

				Message newac = messageService.findOne(messageId);
				System.out.println("After saving" + ":" + " " + newac.getSubject());
				Assert.isTrue(newac.getSubject() == subject);
				System.out.println("------------------------------------------------");

			}

			@Test
			public void testDelete() {
				authenticate("user1");
				System.out.println("-------------------testDelete-------------------");
				Message b = messageService.findOne(65);
				Collection<Message> all1;
				all1 = messageService.findAll();
				System.out.println("Before deleting"+":"+" "+all1);

				messageService.delete(b);

				Collection<Message> all2;
				all2 = messageService.findAll();
				System.out.println("After deleting"+":"+" "+all2);
				System.out.println("------------------------------------------------");
			}

			@Test
			public void testFindOne() {
				int messageId = 65;
				Message result = messageService.findOne(messageId);
				System.out.println(result.getSubject());
				Assert.notNull(result);
			}

			@Test
			public void testFindAll() {
				Collection<Message> result;
				result = messageService.findAll();
				Assert.notNull(result);
			}
}
