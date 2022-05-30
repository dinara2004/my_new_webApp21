package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Student;
import thymeleaf.models.Teacher;
import thymeleaf.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Transactional
    public void save(Teacher teacher) {
        System.out.println(teacher.getFirstName());
        teacherRepository.save(teacher);
        System.out.println("teacher successfully saved!");
    }

    @Transactional
    public Teacher findById(UUID id){
        return teacherRepository.findById(id);
    }

    @Transactional
    public void update(Teacher teacher, UUID id) {
        teacherRepository.update(teacher, id);
    }
}
