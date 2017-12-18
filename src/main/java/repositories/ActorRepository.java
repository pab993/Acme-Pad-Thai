package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{

	//Devuelve un userAccount buscado por su id.
		@Query("select a.userAccount from Actor a where a.userAccount.id = ?1")
		UserAccount findByUserAccountId(int id);
		
	//Devuelve un actor buscado por la id de su userAccount.
		@Query("select a from Actor a where a.userAccount.id = ?1")
		Actor findByActorAccountId(int id);
		
}
