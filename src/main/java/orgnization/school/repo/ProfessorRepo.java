package orgnization.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orgnization.school.entity.Professor;

import java.util.List;

public interface ProfessorRepo extends JpaRepository<Professor,Long> {
      List<Professor> findByStudents_name(String name);
}
