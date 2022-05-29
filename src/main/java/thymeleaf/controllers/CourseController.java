package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Course;
import thymeleaf.services.CourseService;

import java.util.List;

@Controller
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ModelAttribute("courseList")
    public List<Course> findAllCourses() {

        return courseService.findAllCourses();
    }

    @GetMapping
    public String findAll(Model model) {

        model.addAttribute("course", courseService.findAllCourses());

        return "/courses/allCourses";
    }

    @GetMapping("/save")
    public String saveCoursePage(Model model) {

        model.addAttribute("emptyCourse", new Course());

        return "/courses/saveCoursePage";
    }

    @PostMapping("/save")
    public String saveCourse(Course course) {

        System.out.println(course);

        courseService.save(course);

        return "redirect:/api/courses";
    }
}
