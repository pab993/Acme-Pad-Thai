package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
		//Devuelve el mínimo número de campañas que hay entre todos los sponsors
		@Query("select min(s.campaigns.size) from Sponsor s")
		Integer minCampaign();
		
		//Devuelve el máximo número de campañas que hay entre todos los sponsors
		@Query("select max(s.campaigns.size) from Sponsor s")
		Integer maxCampaign();
		
		//Devuelve la media de campañas que hay entre todos los sponsors
		@Query("select avg(s.campaigns.size) from Sponsor s")
		Double avgCampaign();
		
		//Devuelve el mínimo de campañas activas
		@Query("select min(s.campaigns.size) from Sponsor s where s.id in (select c.sponsor from Campaign c where current_date() between startDate and endDate)")
		Integer minActiveCampaign();
		
		//Devuelve el máximo de campañas activas
		@Query("select avg(s.campaigns.size) from Sponsor s where s.id in (select c.sponsor from Campaign c where current_date() between startDate and endDate)")
		Double avgActiveCampaign();
		
		//Devuelve la media de campañas activas
		@Query("select max(s.campaigns.size) from Sponsor s where s.id in (select c.sponsor from Campaign c where current_date() between startDate and endDate)")
		Integer maxActiveCampaign();

}
