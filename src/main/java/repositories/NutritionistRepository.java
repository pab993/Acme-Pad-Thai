package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.Nutritionist;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer>{

	//Encuentra un nutricionista por su id.
	@Query("select n from Nutritionist n where n.userAccount.id = ?1")
	Nutritionist findByUserAccountId(int id);
	
	//Devuelve nutritionist del Principal
	@Query("select n from Nutritionist n where n.userAccount = ?1")
	Nutritionist findOneByUserAccount(UserAccount principal);
	
	//Encuentra un nutricionista por su id.
	@Query("select n from Nutritionist n where n.userAccount.id = ?1")
	Nutritionist findOneByUserAccountId(int id);
}
