package orgnization.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orgnization.school.entity.Student;

import java.util.List;


@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    List<Student> findBySubjects_Title(String title);

    @Query("SELECT COUNT(s) FROM Student s JOIN s.subjects sub WHERE sub.title = :title")
    int countSubjects(String title);
}
