package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer>{

}
