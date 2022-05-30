package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Company;
import thymeleaf.models.Course;
import thymeleaf.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    public void save(Course course) {
        System.out.println(course.getName());
        courseRepository.save(course);
        System.out.println("course successfully saved!");
    }

    @Transactional
    public Course findById(UUID id){
        return courseRepository.findById(id);
    }

    @Transactional
    public void update(Course course, UUID id) {
        courseRepository.update(course, id);
    }
}
