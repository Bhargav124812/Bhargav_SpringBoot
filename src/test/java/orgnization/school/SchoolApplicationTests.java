package orgnization.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import orgnization.school.entity.AdmissonReport;
import orgnization.school.entity.Professor;
import orgnization.school.entity.Student;
import orgnization.school.repo.AdmissonRepo;
import orgnization.school.repo.ProfessorRepo;
import orgnization.school.repo.StudentRepo;
import orgnization.school.repo.SubjectRepo;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class SchoolApplicationTests {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    SubjectRepo subjectRepo;

    @Autowired
    ProfessorRepo professorRepo;
    @Autowired
    AdmissonRepo admissonRepo;

	@Test
	void contextLoads() {
        List<Student> studentList=studentRepo.findBySubjects_Title("Physics");
        for (Student s: studentList){
            System.out.println(s.getName());
        }
         List <Professor> professorList=professorRepo.findByStudents_name("Bob");
         for(Professor p: professorList){
             System.out.println(p.getTitle());
         }

        AdmissonReport admissonReports=admissonRepo.findByStudent_Name("Charlie");
        System.out.println(admissonReports.getStudent());


        System.out.println(admissonRepo.getTotalfees());

        System.out.println(studentRepo.countSubjects("Physics"));


	}

}
