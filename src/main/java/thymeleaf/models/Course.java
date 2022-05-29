package thymeleaf.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
    mappedBy = "courses")
    @ToString.Exclude
    private List<Group> group;

//    @ManyToMany(mappedBy = "courses")
//    @ToString.Exclude
//    private List<Student> students = new ArrayList<>();

    @OneToOne
    private Teacher teacher;

}
