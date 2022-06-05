package thymeleaf.services;

import org.springframework.stereotype.Service;
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
    public void save(Student student, UUID id) {
        studentRepository.save(student, id);
    }

    @Transactional
    public Student findById(UUID id){
        return studentRepository.findById(id);
    }

    public List<Student> findAllStudentsById(UUID id) {
        return studentRepository.findAllStudentsById(id);
    }

    @Transactional
    public void update(Student student, UUID id) {
        studentRepository.update(student, id);
    }
}
