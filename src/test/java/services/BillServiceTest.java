package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Bill;

import utilities.AbstractTest;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})

@Transactional
public class BillServiceTest extends AbstractTest {

	//service underTest
	@Autowired
	private BillService billService;
	
	//Test
	@Test
	public void testCreateBill(){
		Bill b = billService.create();
		Assert.notNull(b, "Bill no creado");
	}
	
	@Test
	public void testFindAll(){
		
		Collection<Bill> bills = billService.findAll();
		Assert.notEmpty(bills);
	}
	
	@Test
	public void testFindOne(){
		
		Bill b = billService.findOne(60);
		Assert.notNull(b);
	}
	
	@Test
	public void testSaveBill(){
		 
		Bill bill, saved;
		Collection<Bill> bills;
		bill = billService.findOne(60);
		//almaceno
		saved = billService.save(bill);
		//compruebo OK
		bills = billService.findAll();
		Assert.isTrue(bills.contains(saved));
	}
	
	//Other business methods----------------------
	
	public void testAvgPaidBills(){
		authenticate("admin1");
		Double res;
		res = billService.avgPaidBills();
		Assert.notNull(res);
		
	}
	
	public void testStddevPaidBills(){
		authenticate("admin1");
		Double res;
		res = billService.stddevPaidBills();
		Assert.notNull(res);
		
	}
	
	public void testAvgUnPaidBills(){
		authenticate("admin1");
		Double res;
		res = billService.avgUnPaidBills();
		Assert.notNull(res);
		
	}
	
	public void testStddevUnPaidBills(){
		authenticate("admin1");
		Double res;
		res = billService.stddevUnPaidBills();
		Assert.notNull(res);
		
	}
}
