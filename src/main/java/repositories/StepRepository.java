package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer>{
	
	//Devuelve la media de todos los pasos de todas las recetas.
	@Query("select avg(r.steps.size) from Recipe r")
	Double averageStepsPerRecipe();
	
	//Devuelve la desviación estándar de todos los pasos de todas las recetas.
	@Query("select stddev(r.steps.size) from Recipe r")
	Double standarDeviationStepsPerRecipe();
	

}
