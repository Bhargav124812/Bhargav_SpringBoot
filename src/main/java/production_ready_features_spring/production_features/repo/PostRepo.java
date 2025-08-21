package production_ready_features_spring.production_features.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import production_ready_features_spring.production_features.entity.PostEntity;


@Repository
public interface PostRepo extends JpaRepository<PostEntity,Long> {

}
