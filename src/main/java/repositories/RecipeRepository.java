package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	
	//Devuelve el mínimo número de recetas que tiene entre todos los usuarios.
	@Query("select min(u.recipes.size) from User u")
	Integer minimumNumberRecipesPerUser();
	
	//Devuelve la media de las recetas de todos los usuarios.
	@Query("select avg(u.recipes.size) from User u")
	Double averageNumberRecipesPerUser ();
	
	//Devuelve el máximo número de recetas que tiene entre todos los usuarios
	@Query ("select max(u.recipes.size) from User u")
	Integer maximumNumberRecipesPerUser ();
	
	//Devuelve una receta buscada por el nombre, descripción o ticker.
	//select r from Recipe r where r.title like %?1% or r.description like %?2% or r.ticker like %?3%
	@Query ("select r from Recipe r where r.title like %?1% or r.summary like %?1% or r.ticker like %?1%")
	Collection<Recipe> recipeSearch (String string);
	
	//Devuelve todas las recetas ordenadas por sus categorías.
	@Query ("select r from Recipe r order by r.category")
	Collection<Recipe> showRecipesByCategory();
	
	//Devuelve las recetas por concursos
	@Query("select r from Recipe r join r.qualifications rq where rq IN " +
			"(select q from Qualification q where q.contest = " +
			"(select c from Contest c where c.id = ?1))")
	Collection<Recipe> findByContest(int contestId);
	
	@Query("select r from Recipe r where r.user = (select u from User u where u.id = ?1)")
	Collection<Recipe> findByUser(int userId);
	
	//Busca las recetas de los que sigue
	@Query("select r from Recipe r where r.user in (select f.followed from Customer c join c.followers f where c.id=?1) order by r.momentLastUpdate DESC")
	Collection<Recipe> findFollowedsRecipes(int id);
	
	//Busca la receta más reciente de un customer
	@Query("select r from Recipe r where r.user.id = ?1 order by r.momentAuthored DESC")
	Collection<Recipe> findRecentRecipes(int id);
	
	//select r from Recipe r where r.momentAuthored = (select max(r1.momentAuthored) from Customer c1 join c1.recipes r1 where c1.id = ?1) and r.user.id = ?1
	
}
