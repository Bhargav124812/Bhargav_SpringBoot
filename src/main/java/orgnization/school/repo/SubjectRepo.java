package orgnization.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orgnization.school.entity.Subject;


@Repository
public interface SubjectRepo extends JpaRepository<Subject,Long> {
}
