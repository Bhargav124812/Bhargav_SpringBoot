package securityfeatures.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securityfeatures.entity.PostEntity;


@Repository
public interface PostRepo extends JpaRepository<PostEntity,Long> {

}
