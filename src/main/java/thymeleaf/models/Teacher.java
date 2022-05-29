package thymeleaf.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String email;
    private String lastName;

    @OneToOne
    @ToString.Exclude
    private Course course;
}
