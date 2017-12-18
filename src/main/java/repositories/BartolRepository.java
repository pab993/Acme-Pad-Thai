package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bartol;
import domain.User;



@Repository
public interface BartolRepository extends JpaRepository<Bartol, Integer>{
	
	//Query-------

	@Query("select (select 1.0*count(r1) from Recipe r1 where r1.bartols is not empty)/count(*) from Recipe r")
	Double ratioBartols();
	
	@Query("select r.user from Recipe r where r.bartols.size = (select max(r1.bartols.size) from Recipe r1)")
	Collection<User> usersWithMoreBartols();
}
