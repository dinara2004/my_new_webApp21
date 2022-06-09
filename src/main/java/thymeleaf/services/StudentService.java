package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Group;
import thymeleaf.models.Student;
import thymeleaf.repositories.GroupRepository;
import thymeleaf.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public void save(Student student, UUID id) {
        Group group = groupRepository.findById(id);
        group.setStudent(student);
        student.setGroups(group);
        studentRepository.save(student);
    }

    public Student findById(UUID id){
        return studentRepository.findById(id);
    }

    public List<Student> findAllStudentsById(UUID id) {
        return studentRepository.findAllStudentsById(id);
    }

    public void update(Student student, UUID id) {
        studentRepository.update(student, id);
    }

    public void deleteById(UUID studentId) {
        studentRepository.deleteById(studentId);
    }
}
