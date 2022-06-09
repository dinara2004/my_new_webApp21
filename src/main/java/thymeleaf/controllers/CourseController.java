package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/findBy/{id}") // /find/by/34895703985702938450
    public String findByCompanyId(@PathVariable UUID id, Model model) {

        List<Course> courses = courseService.findAllCoursesById(id);

        model.addAttribute( "courses", courses);
        model.addAttribute("companyId", id);

        return "/courses/allCourses";
    }


    @GetMapping("/save/{id}")
    public String saveCoursePage(Model model, @PathVariable UUID id) {

        model.addAttribute("emptyCourse", new Course());

        model.addAttribute("id", id);

        return "/courses/saveCoursePage";
    }

    @PostMapping("/save/{id}")
    public String saveCourse(Course course,
                             @PathVariable("id") UUID id) {

        courseService.save(course, id);

        return "redirect:/api/courses/findBy/" + id;
    }

    @GetMapping("/update/{id}")
    public String editCourse(Model model, @PathVariable UUID id){

        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        return "courses/updateCourse";
    }


    @PostMapping("/update/{id}")
    public String updateCourse(Course course,
                                @PathVariable UUID id){
        Course byId = courseService.findById(id);
        UUID id1 = byId.getCompany().getId();
        courseService.update(course, id);
        return "redirect:/api/courses/findBy/" + id1;
    }


    @GetMapping("/delete/{courseId}")
    public String deleteById(@PathVariable("courseId") UUID courseId){
        Course course = courseService.findById(courseId);
        UUID id = course.getCompany().getId();
        courseService.deleteById(courseId);
        return "redirect:/api/courses/findBy/" + id;
    }

//    @GetMapping("/delete/{courseId}")
//    public String deleteById(@PathVariable("courseId") UUID courseId) {
//        courseService.deleteById(courseId);
//        return "redirect:/api/course/findBy/";
//    }

}
