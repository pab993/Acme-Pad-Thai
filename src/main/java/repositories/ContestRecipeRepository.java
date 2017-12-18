package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ContestRecipe;

@Repository
public interface ContestRecipeRepository extends JpaRepository<ContestRecipe, Integer>{

	//Devuelve el valor total entre likes y dislikes
	@Query("select cr.rest from ContestRecipe cr where cr.id=?1")
	int findRestByContestRecipeId(int contestRecipeId);
	
	//Devuelve los contestRecipes de un concurso por su id
	@Query("select cr from ContestRecipe cr where cr.contest.id=?1")
	Collection<ContestRecipe> findAllByContest(int id);
}
