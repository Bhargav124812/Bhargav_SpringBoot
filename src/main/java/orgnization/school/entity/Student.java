package orgnization.school.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    private List<Professor> professor;

    @ManyToMany
    private List<Subject> subjects;

    @OneToOne(mappedBy = "student",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval = true,fetch =  FetchType.LAZY)
    private AdmissonReport admissonReport;
}
