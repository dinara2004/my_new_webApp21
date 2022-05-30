package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Group;
import thymeleaf.models.Student;
import thymeleaf.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public void save(Student student) {
        System.out.println(student.getName());
        studentRepository.save(student);
        System.out.println("student successfully saved!");
    }

    @Transactional
    public Student findById(UUID id){
        return studentRepository.findById(id);
    }

    public List<Student> findByCourseId(UUID courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    @Transactional
    public void update(Student student, UUID id) {
        studentRepository.update(student, id);
    }
}
