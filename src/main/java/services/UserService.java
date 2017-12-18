	package services;

	import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.UserAccount;
import domain.Folder;
import domain.Recipe;
import domain.User;

	@Service
	@Transactional
	public class UserService {

		//Managed repository ------------------
		
		@Autowired
		private UserRepository userRepository;
		
		
		//Supporting services -----------------
		
		@Autowired
		private AdministratorService administratorService;

		@Autowired
		private FolderService folderService;
		
		//Constructors ------------------------

		// Simple CRUD methods -----------------

		public User create() {
			UserAccount account = new UserAccount();
			Collection<Authority> authorities = new ArrayList<Authority>();
			Authority authority = new Authority();
			authority.setAuthority("USER");
			authorities.add(authority);
			account.setAuthorities(authorities);
			User res;
			res = new User();
			res.setUserAccount(account);
			List<Recipe>r= new ArrayList<>();
			res.setRecipes(r);
			return res;

		}
		
		public Collection<User> findAll(){
			Collection<User>res;
			res=userRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		public User findOne(int id){
			
			Assert.notNull(id);
			User user = userRepository.findOne(id);
			Assert.notNull(user);
			return user;
		}

		public User save(User user) {

			Assert.notNull(user);
			String password = user.getUserAccount().getPassword();
			Md5PasswordEncoder encoder= new Md5PasswordEncoder();
			String md5 =encoder.encodePassword(password, null);
			user.getUserAccount().setPassword(md5);
			
			Folder inbox = folderService.createWithoutUserAccount();
				inbox.setName("inbox");
				user.getFolders().add(inbox);
				//folderService.save(inbox);
				Folder outbox = folderService.createWithoutUserAccount();
				outbox.setName("outbox");
				user.getFolders().add(outbox);
				//folderService.save(outbox);
				Folder spambox = folderService.createWithoutUserAccount();
				spambox.setName("spambox");

				user.getFolders().add(spambox);
				//folderService.save(spambox);
				Folder trashbox = folderService.createWithoutUserAccount();
				trashbox.setName("trashbox");
				user.getFolders().add(trashbox);
				

				//folderService.save(trashbox);
			
			User user1 = userRepository.save(user);
			inbox.setActor(user1);
			outbox.setActor(user1);
			trashbox.setActor(user1);
			spambox.setActor(user1);
			folderService.InitialSave(inbox);
			folderService.InitialSave(outbox);
			folderService.InitialSave(spambox);
			folderService.InitialSave(trashbox);
			return user1;
			
		}
		
		public User saveAgain(User user) {

			Assert.notNull(user);
			User user1 = userRepository.save(user);
			
			return user1;
			
		}
		

		public void delete(User user) {
			Assert.notNull(user);
			Assert.isTrue(user.getId() != 0);
			Assert.isTrue(userRepository.exists(user.getId()));
			userRepository.delete(user);

		}
		
		//-------
		
		public Collection<User> userWhoHaveAuthoredMoreRecipes(){
			administratorService.checkPrincipal();
			Collection<User> res;
			res=userRepository.usersWhoHaveAuthoredMoreRecipes();
			Assert.notNull(res);
			return res;
			
		}
		public Collection<User> UsersMorePopulars(){
			administratorService.checkPrincipal();
			Collection<User> res;
			res=userRepository.mostPopularUsers();
			Assert.notNull(res);
			return res;
			
		}
		public Collection<User> UsersRegardingAverageLikesAndDislikes(){
			administratorService.checkPrincipal();
			Collection<User> res;
			res=userRepository.usersRegardingAverageLikesAndDislikes();
			Assert.notNull(res);
			return res;
			
		}
		public Collection<User> UserSearch(String s){
			Collection<User> res;
			res=userRepository.UserSearch(s);
			Assert.notNull(res);
			return res;
			
		}
		
		public User searchAuthor(int id){
			
			Assert.notNull(id);
			
			User result = userRepository.searchAuthor(id);
			
			Assert.notNull(result);
			
			return result;
		}
		
		public User findOneByuserAccountId(int id){
			Assert.notNull(id);
			User result = userRepository.findOneByuserAccountId(id);
			Assert.notNull(result);
			return result;
		}
		
		public void assignFolder(Folder f, UserAccount userAccount){
			User admin = findOneByuserAccountId(userAccount.getId());
			f.setActor(admin);
			admin.getFolders().add(f);
		}
		
		
		
}



