package orgnization.school.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orgnization.school.entity.AdmissonReport;
import orgnization.school.entity.Student;
import orgnization.school.entity.Subject;
import orgnization.school.repo.StudentRepo;

import java.util.List;


@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Transactional
    public  Student  giveAdmissionReport(AdmissonReport admissonReport, Long id){
        Student student=studentRepo.findById(id).orElseThrow();
        student.setAdmissonReport(admissonReport);
        admissonReport.setStudent(student);
        return student;
    }

    @Transactional
    public void deleteAStudent(Long id){
        studentRepo.deleteById(id);
    }

    @Transactional
    public void updateTheStudent(Long id){
        Student student=studentRepo.findById(id).orElseThrow();
        student.setAdmissonReport(null);
    }
}
