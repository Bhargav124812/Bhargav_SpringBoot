package orgnization.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orgnization.school.entity.AdmissonReport;
import orgnization.school.entity.Student;

@Repository
public interface AdmissonRepo extends JpaRepository<AdmissonReport,Long> {
    AdmissonReport findByStudent_Name(String name);

    @Query("SELECT SUM(a.fees) FROM AdmissonReport a")
    int getTotalfees();


}
