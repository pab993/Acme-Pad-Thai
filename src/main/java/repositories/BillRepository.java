package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{

	@Query("select avg(m.cost) from Bill m where m.momentPay is not null")
	Double avgPaidBills();

	@Query("select stddev(m.cost) from Bill m where m.momentPay is not null")
	Double stddevPaidBills();
	
	@Query("select avg(m.cost) from Bill m where m.momentPay is null")
	Double avgUnPaidBills();
	
	@Query("select stddev(m.cost) from Bill m where m.momentPay is null")
	Double stddevUnPaidBills();
}
