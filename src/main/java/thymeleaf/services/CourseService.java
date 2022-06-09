package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.repositories.CompanyRepository;
import thymeleaf.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public void save(Course course, UUID id) {
        companyRepository.save(course, id);
//        courseRepository.save(course);
    }

    public Course findById(UUID id) {
        return courseRepository.findById(id);
    }

    public void update(Course course, UUID id) {
        courseRepository.update(course, id);
    }

    public void deleteById(UUID courseId){
        courseRepository.deleteById(courseId);
    }

    public List<Course> findAllCoursesById(UUID id) {
        return courseRepository.findAllCoursesById(id);
    }
}
