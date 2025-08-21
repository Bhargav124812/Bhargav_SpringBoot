package orgnization.school.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     private String title;

     @OneToMany(mappedBy ="professor")
     private List<Subject> subjects;

     @ManyToMany(mappedBy = "professor")
     private List<Student> students;

}
