package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	//Busca a un customer desde el id del userAccount
	@Query("select c from Customer c where c.userAccount.id=?1")
	Customer findOneByuserAccountId(int id);
	
	//Busca los customers que sigue un customer
	@Query("select f.followed from Customer c join c.followers f where c.id=?1")
	Collection<Customer> findFollowedsByCustomer(int id);
	
}
