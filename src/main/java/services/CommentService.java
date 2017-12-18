	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

	import repositories.CommentRepository;

import domain.Comment;

	@Service
	@Transactional
	public class CommentService {

		//Managed repository ------------------
		
		@Autowired
		private CommentRepository commentRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private ActorService actorService;
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Comment create() {
			
			actorService.check();
			Comment res;
			res = new Comment();
			return res;

		}
		
		public Collection<Comment> findAll(){
			Collection<Comment>res;
			actorService.findAll(); //Esta llamada hace falta ya que si no hibernate da problemas, es un WorkAround
			res=commentRepository.findAll(); 
			Assert.notNull(res);
			return res;
		}
		
		

		public Comment save(Comment comment) {
			actorService.check();
			Assert.notNull(comment);
			Comment commentRes = commentRepository.save(comment);
			return commentRes;

		}


		public Comment findOne(int commentId){
			Comment result;
			Assert.notNull(commentId);
			result = commentRepository.findOne(commentId);
			Assert.notNull(result);
			return result;
			
		}
		
		//Other business methods-----------------------------------
		
		public Collection<Comment> findCommentsByRecipe(int recipeId){
			Collection<Comment> result;
			Assert.notNull(recipeId);
			result = commentRepository.findCommentsByRecipe(recipeId);
			Assert.notNull(result);
			return result;
		}
}
