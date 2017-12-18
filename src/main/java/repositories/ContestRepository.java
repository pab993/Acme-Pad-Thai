package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contest;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer>{
	
	//Esta query devuelve el número mínimo de recetas registradas en un concurso de entre todos los concursos existentes.
	@Query ("select min(c.qualifications.size) from Contest c")
	Integer minimumNumberRecipesQualified();

	//Esta query devuelve el número máximo de recetas registradas en un concurso de entre todos los consursos existentes.
	@Query("select max(c.qualifications.size) from Contest c")
	Integer maximumNumberRecipesQualified();
	
	//Esta query devuelve la media de recetas registradas en los concursos.
	@Query("select avg(c.qualifications.size) from Contest c")
	Double averageNumberRecipesQualified();
	
	//Devuelve el concurso que tiene más recetas registradas.
	@Query("select c from Contest c where c.qualifications.size = (Select max(ct.qualifications.size) from Contest ct)")
	Collection<Contest> contestWithMoreRecipesQualified();
	
	//Devuelve los concursos terminados
	@Query("select c from Contest c where c.closingTime < current_timestamp()")
	Collection<Contest> findClosedContests();
	
}

