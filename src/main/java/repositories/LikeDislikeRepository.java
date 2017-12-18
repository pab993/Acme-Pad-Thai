package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.LikeDislike;

@Repository
public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Integer>{

}
