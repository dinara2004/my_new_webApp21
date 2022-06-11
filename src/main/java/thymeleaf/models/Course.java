package thymeleaf.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.REMOVE;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH},mappedBy = "courses")
    @ToString.Exclude
    private List<Group> groups = new ArrayList<>();

//    @ManyToMany(mappedBy = "courses")
//    @ToString.Exclude
//    private List<Student> students = new ArrayList<>();

    @OneToOne(mappedBy = "course",
            cascade = {REMOVE},
            orphanRemoval = true)
    private Teacher teacher;

    public void setGroup(Group group){
        this.groups.add(group);
    }

}
