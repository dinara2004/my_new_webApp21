package thymeleaf.models;

import lombok.*;
import thymeleaf.enums.StudyFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    private StudyFormat studyFormat;

//    @ManyToMany
//    private List<Course> courses = new ArrayList<>();

    @ManyToOne
    private Group groups;

}
