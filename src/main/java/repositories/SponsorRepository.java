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
	
	//Devuelve las compañías que han gastado menos que la media de todas las compañías.
	@Query("select s.company from Sponsor s where (select sum(c1.bannersNumber*cs1.fee) from ConfigurationSystem cs1, Campaign c1 where c1.sponsor = s) < (select avg(cs.fee*c.bannersNumber) from ConfigurationSystem cs, Campaign c)")
	Collection<String> companiesThatHaveSpentLessThanTheAverage();
	
	//Devuelve los sponsors que llevan inactivos más de tres meses.
	@Query("select c.sponsor from Campaign c where c.sponsor not in (select c.sponsor from Campaign c where (sysdate() - c.endDate)<=300000000) group by c.sponsor")
	Collection<Sponsor> inactiveSponsors();
	
	//Devuelve las compañias que han gastado más que el 90% de la compañia cuya campaña es la más cara.
	@Query("select s.company from Sponsor s where (select sum(c1.bannersNumber*cs1.fee) from ConfigurationSystem cs1, Campaign c1 where c1.sponsor = s) < (select avg(0.9*cs.fee*c.bannersNumber) from ConfigurationSystem cs, Campaign c where cs.fee*c.bannersNumber = (select max(cs.fee*c.bannersNumber) from ConfigurationSystem cs, Campaign c))")
	Collection<String> companiesNinetyPerCent();
	
	//Devuelve las compañias ordenadas por su número de campañas.
	@Query("select s.company from Sponsor s order by s.campaigns.size")
	Collection<String> rankingOfCompaniesByCampaigns();
	
	//Devuelve las compañias ordenadas por su número de facturas.
	@Query("Select s.company from Sponsor s order by s.bills.size")
	Collection<String> rankingOfCompaniesByBills();
	
}
