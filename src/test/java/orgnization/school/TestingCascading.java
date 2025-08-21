package orgnization.school;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import orgnization.school.entity.AdmissonReport;
import orgnization.school.entity.Student;
import orgnization.school.entity.Subject;
import orgnization.school.repo.StudentRepo;
import orgnization.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TestingCascading {


    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepo studentRepo;
    @Test
    void testCascading(){
        AdmissonReport admissonReport=AdmissonReport.builder()
                .fees(3000).build();
        System.out.println(studentService.giveAdmissionReport(admissonReport,1L).getName());

        Student student = Student.builder()
                .name("Bhargav")
                .build();

        AdmissonReport admission = AdmissonReport.builder()
                .fees(25000)
                .student(student)
                .build();

        student.setAdmissonReport(admission);
        studentRepo.save(student); // should save both Student & Admission


    }

    @Test
    void delete(){
        studentService.deleteAStudent(1L);
    }
    @Test
    void orphanRemoval(){
        studentService.updateTheStudent(1L);
    }

}
