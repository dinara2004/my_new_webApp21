package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.models.Teacher;
import thymeleaf.repositories.CourseRepository;
import thymeleaf.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Transactional
    public void save(Teacher teacher, UUID courseId) {
        final Course byId = courseRepository.findById(courseId);
        teacher.setCourse(byId);
        byId.setTeacher(teacher);
        teacherRepository.save(teacher);
    }

    public Teacher findById(UUID id){
        return teacherRepository.findById(id);
    }

    @Transactional
    public void update(Teacher teacher, UUID id) {
        teacherRepository.update(teacher, id);
    }

    public List<Teacher> findAllTeachersById(UUID id) {
        return teacherRepository.findAllTeachersById(id);
    }
}
