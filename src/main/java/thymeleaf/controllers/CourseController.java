package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Company;
import thymeleaf.models.Course;
import thymeleaf.services.CourseService;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/update/{id}")
    public String editCourse(Model model, @PathVariable("id") UUID id){
        model.addAttribute("course", courseService.findById(id));
        return "courses/updateCourse";
    }


    @PostMapping("/{id}")
    public String updateCourse(@ModelAttribute("course") Course course,
                                @PathVariable("id") UUID id){
        courseService.update(course, id);
        return "redirect:/api/courses";
    }


}
