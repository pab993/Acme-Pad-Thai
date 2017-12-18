package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Follower;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Integer>{

}
