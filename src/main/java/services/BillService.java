package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BillRepository;
import domain.Bill;

@Service
@Transactional
public class BillService {

	public BillService(){
		super();
	}
	
	//Managed repository --------------
	
	@Autowired
	private BillRepository billRepository;
		
	//Supporting services --------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	//Methods CRUDs ------------------
	
	public Bill create(){
	
		Bill b = new Bill();
		b.setMomentCreate(new Date(System.currentTimeMillis() - 100));
		return b;
	}
	
	public Collection<Bill> findAll(){
		
		Collection<Bill> bills = billRepository.findAll();
		Assert.notNull(bills);
		return bills;
	}
	
	public Bill findOne(int id){
		
		Assert.notNull(id);
		Bill b = billRepository.findOne(id);
		Assert.notNull(b, "Bill con id: "+id+" no encontrado");
		return b;
	}
	
	public Bill save(Bill b){
		
		Assert.notNull(b);
		b.setMomentCreate(new Date(System.currentTimeMillis() - 100));
		Bill billRes = billRepository.save(b);
		return billRes;
	}
	
	/*
	 * NO PODEMOS BORRAR BILLS 
	 */
	
	//Other business methods-------------------------
	
	public Double avgPaidBills(){
		administratorService.checkPrincipal();
		Double res;
		res = billRepository.avgPaidBills();
		Assert.notNull(res);
		return res;
		
	}
	
	public Double stddevPaidBills(){
		administratorService.checkPrincipal();
		Double res;
		res = billRepository.stddevPaidBills();
		Assert.notNull(res);
		return res;
		
	}
	
	public Double avgUnPaidBills(){
		administratorService.checkPrincipal();
		Double res;
		res = billRepository.avgUnPaidBills();
		Assert.notNull(res);
		return res;
		
	}
	
	public Double stddevUnPaidBills(){
		administratorService.checkPrincipal();
		Double res;
		res = billRepository.stddevUnPaidBills();
		Assert.notNull(res);
		return res;
		
	}
	
}
