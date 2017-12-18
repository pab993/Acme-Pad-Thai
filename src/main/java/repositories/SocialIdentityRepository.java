package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer>{
	
	//Devuelve las identidades sociales del principal
	@Query("select i from SocialIdentity i where i.actor.userAccount=?1")
	Collection<SocialIdentity> findAllByPrincipal(UserAccount principal);

}
