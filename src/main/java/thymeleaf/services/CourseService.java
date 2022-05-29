package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;

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
}
