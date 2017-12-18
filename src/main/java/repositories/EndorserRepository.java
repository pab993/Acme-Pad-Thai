package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorser;

@Repository
public interface EndorserRepository extends JpaRepository<Endorser, Integer>{

	@Query("select e from Endorser e where e.curriculum.id =?1")
	Collection<Endorser> findByCurriculum(int curriculumId);

	@Query("select e from Endorser e where e.curriculum.id = ?1")
	Collection<Endorser> findAllByCurriculum(int curriculumId);

}
