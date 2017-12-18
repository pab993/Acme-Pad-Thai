package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
	
	//Devuelve la media de todos los ingredientes por receta.
	@Query("select avg(r.unitSystems.size) from Recipe r")
	Double averageIngredientsPerRecipe();
	
	//Devuelve la desviación estándar de todos los ingredientes por receta.
	@Query("select stddev(r.unitSystems.size) from Recipe r")
	Double standarDeviationIngredientsPerRecipe();
	
	

}
