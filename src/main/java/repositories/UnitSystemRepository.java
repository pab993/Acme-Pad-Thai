package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.UnitSystem;

@Repository
public interface UnitSystemRepository extends JpaRepository<UnitSystem, Integer>{

	@Query("select us from UnitSystem us where us.recipe.id = ?1 and us.ingredient.id = ?2")
	UnitSystem unitSystemByRecipeAndIngredient(int recipeId, int ingredientId);
}
