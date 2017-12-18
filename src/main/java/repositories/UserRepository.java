package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//Devuelve el/los usuario/s que han publicado más recetas.
	@Query("select u from User u where u.recipes.size = (select max(us.recipes.size) from User us)")
	Collection<User> usersWhoHaveAuthoredMoreRecipes ();
	
	//Devuelve una lista de usuarios ordenada por el numero de seguidores que tienen.
	@Query("select u from User u order by u.followeds.size DESC")
	Collection<User> mostPopularUsers ();
	
	//Devuelve los usuarios ordenados por la media de los likes y dislikes de sus recetas.
	@Query("select u from User u join u.recipes ur group by u order by avg(ur.likes.size) DESC")
	Collection<User> usersRegardingAverageLikesAndDislikes ();
	
	//Busca un usuario por su nombre.
	@Query("select u from User u where u.name like %?1% or u.surname like %?1% or u.userAccount.username like %?1%")
	Collection<User> UserSearch(String s);

		//Busca el autor de una receta
		@Query("select u from User u join u.recipes ur where ur.id = ?1")
		User searchAuthor(int id);
		
		//Busca a un usuario desde el id del userAccount
		@Query("select u from User u where u.userAccount.id=?1")
		User findOneByuserAccountId(int id);
		
		
		
}
