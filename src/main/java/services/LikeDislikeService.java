	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

import repositories.LikeDislikeRepository;
import domain.Customer;
import domain.LikeDislike;
import domain.Recipe;

	@Service
	@Transactional
	public class LikeDislikeService {

		//Managed repository ------------------
		
		@Autowired
		private LikeDislikeRepository likeDislikeRepository;
		
		@Autowired
		private RecipeService recipeService;
		
		@Autowired
		private CustomerService customerService;
		
		//Supporting services -----------------
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public LikeDislike create() {
			LikeDislike res;
			res = new LikeDislike();
			return res;

		}
		
		public Collection<LikeDislike> findAll(){
			Collection<LikeDislike>res;
			res=likeDislikeRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public LikeDislike findOne(int id){
			
			Assert.notNull(id);
			LikeDislike likeDislike = likeDislikeRepository.findOne(id);
			Assert.notNull(likeDislike);
			return likeDislike;
		}

		public LikeDislike save(LikeDislike likeDislike) {
			Assert.notNull(likeDislike);
			LikeDislike likeDislikeRes = likeDislikeRepository.save(likeDislike);
			return likeDislikeRes;

		}

		public void delete(LikeDislike likeDislike) {
			Assert.notNull(likeDislike);
			Assert.isTrue(likeDislike.getId() != 0);
			Assert.isTrue(likeDislikeRepository.exists(likeDislike.getId()));
			likeDislikeRepository.delete(likeDislike);

		}
}



