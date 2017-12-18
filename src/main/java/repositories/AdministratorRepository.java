package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{

		//Devuelve un Admin buscado por su id.
		@Query("select a.userAccount from Administrator a where a.userAccount.id = ?1")
		UserAccount findByAdminAccountId(int id);
		
		//Busca a un usuario desde el id del userAccount
		@Query("select a from Administrator a where a.userAccount.id=?1")
		Administrator findOneByuserAccountId(int id);
}
