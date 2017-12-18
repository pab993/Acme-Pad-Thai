package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer>{
	
	//Devuelve un Sponsor buscado por su id.
	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findBySponsorAccountId(int id);
	
	//Devuelve las compa��as que han gastado menos que la media de todas las compa��as.
	@Query("select s.company from Sponsor s where (select sum(c1.bannersNumber*cs1.fee) from ConfigurationSystem cs1, Campaign c1 where c1.sponsor = s) < (select avg(cs.fee*c.bannersNumber) from ConfigurationSystem cs, Campaign c)")
	Collection<String> companiesThatHaveSpentLessThanTheAverage();
	
	//Devuelve los sponsors que llevan inactivos m�s de tres meses.
	@Query("select c.sponsor from Campaign c where c.sponsor not in (select c.sponsor from Campaign c where (sysdate() - c.endDate)<=300000000) group by c.sponsor")
	Collection<Sponsor> inactiveSponsors();
	
	//Devuelve las compa�ias que han gastado m�s que el 90% de la compa�ia cuya campa�a es la m�s cara.
	@Query("select s.company from Sponsor s where (select sum(c1.bannersNumber*cs1.fee) from ConfigurationSystem cs1, Campaign c1 where c1.sponsor = s) < (select avg(0.9*cs.fee*c.bannersNumber) from ConfigurationSystem cs, Campaign c where cs.fee*c.bannersNumber = (select max(cs.fee*c.bannersNumber) from ConfigurationSystem cs, Campaign c))")
	Collection<String> companiesNinetyPerCent();
	
	//Devuelve las compa�ias ordenadas por su n�mero de campa�as.
	@Query("select s.company from Sponsor s order by s.campaigns.size")
	Collection<String> rankingOfCompaniesByCampaigns();
	
	//Devuelve las compa�ias ordenadas por su n�mero de facturas.
	@Query("Select s.company from Sponsor s order by s.bills.size")
	Collection<String> rankingOfCompaniesByBills();
	
}
