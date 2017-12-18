package repositories;



import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	//Query que devuelve una lista de comentarios ordenados de una receta.
	@Query("select c from Recipe r join r.comments c where r.id = ?1 order by c.momentOfCreation DESC")
	Collection<Comment> findCommentsByRecipe(int recipeId);
}
