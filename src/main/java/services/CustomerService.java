	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

import repositories.CustomerRepository;

import domain.Customer;


	@Service
	@Transactional
	public class CustomerService {

		//Managed repository ------------------
		
		@Autowired
		private CustomerRepository customerRepository;
		
		
		//Supporting services -----------------
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Customer create() {
			Customer res;
			res = new Customer();
			return res;

		}
		
		public Collection<Customer> findAll(){
			Collection<Customer>res;
			res=customerRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public Customer findOne(int id){
			
			Assert.notNull(id);
			Customer customer = customerRepository.findOne(id);
			Assert.notNull(customer);
			return customer;
		}
		

		public Customer save(Customer customer) {
			Assert.notNull(customer);
			Customer customerRes = customerRepository.save(customer);
			return customerRes;

		}

		public void delete(Customer customer) {
			Assert.notNull(customer);
			Assert.isTrue(customer.getId() != 0);
			Assert.isTrue(customerRepository.exists(customer.getId()));
			customerRepository.delete(customer);

		}
		
		//Other bussiness methods -------------------------------------------
		
		public Customer findOneByuserAccountId(int customerId){
			
			Assert.notNull(customerId);
			Customer result = customerRepository.findOneByuserAccountId(customerId);
			Assert.notNull(result);
			return result;
			
		}
		
		public Collection<Customer> findFollowedsByCustomerId(int customerId){
			
			Assert.notNull(customerId);
			Collection<Customer> result = customerRepository.findFollowedsByCustomer(customerId);
			Assert.notNull(result);
			return result;
			
		}
		
}



