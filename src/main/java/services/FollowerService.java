	package services;

	import java.util.Collection;

import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import org.springframework.util.Assert;

import repositories.FollowerRepository;
import domain.Follower;

	@Service
	@Transactional
	public class FollowerService {

		//Managed repository ------------------
		
		@Autowired
		private FollowerRepository followerRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private CustomerService customerService;
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public Follower create() {
			Follower res;
			res = new Follower();
			return res;

		}
		
		public Collection<Follower> findAll(){
			Collection<Follower>res;
			customerService.findAll();
			res=followerRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public Follower findOne(int id){
			
			Assert.notNull(id);
			Follower follower = followerRepository.findOne(id);
			Assert.notNull(follower);
			return follower;
		}
		

		public Follower save(Follower follower) {
			Assert.notNull(follower);
			Follower followerRes = followerRepository.save(follower);
			return followerRes;
		}

		public void delete(Follower follower) {
			Assert.notNull(follower);
			Assert.isTrue(follower.getId() != 0);
			Assert.isTrue(followerRepository.exists(follower.getId()));
			followerRepository.delete(follower);

		}
}



