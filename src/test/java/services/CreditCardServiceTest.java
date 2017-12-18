package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.CreditCard;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})

@Transactional
public class CreditCardServiceTest extends AbstractTest {

	@Autowired
	private CreditCardService creditCardService;
	
	
	@Test
	public void testCreateCreditCard(){
		authenticate("sponsor1");
		CreditCard card = creditCardService.create();
		Assert.notNull(card);
	}
	
	@Test
	public void testFindOne(){
		
		CreditCard creditCard = creditCardService.findOne(52);
		Assert.notNull(creditCard);
		
	}
	
	public Collection<CreditCard> findAll(){
		Collection<CreditCard>res;
		res=creditCardService.findAll();
		Assert.notNull(res);
		return res;
	}
	
	@Test
	public void testSave(){
		//se pueden mostrar todas las tarjetas asociadas al principal
		authenticate("sponsor1");
		
		CreditCard creditCard, saved;
		creditCard = creditCardService.findOne(52);
		saved = creditCardService.save(creditCard);
		
		Assert.isTrue(saved.equals(creditCard));
		
	}
	
	@Test
	public void testDelete(){
		authenticate("sponsor1");
		
		CreditCard creditCard;
		creditCard = creditCardService.findOne(52);
		creditCardService.delete(creditCard);
		
		Collection<CreditCard> creditCards = creditCardService.findAll();
		Assert.isTrue(!creditCards.contains(creditCard));
	}
}
