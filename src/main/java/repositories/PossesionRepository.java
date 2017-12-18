package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Possesion;
import domain.UnitSystem;

@Repository
public interface PossesionRepository extends JpaRepository<Possesion, Integer>{

	@Query("select p from Possesion p where p.ingredient.id = ?1 and p.property.id = ?2")
	Possesion possesionByIngredientAndProperty(int ingredientId, int recipeId);
}
