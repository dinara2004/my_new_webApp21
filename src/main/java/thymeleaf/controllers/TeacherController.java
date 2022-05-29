package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Student;
import thymeleaf.models.Teacher;
import thymeleaf.services.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ModelAttribute("teacherList")
    public List<Teacher> findAllTeachers() {

        return teacherService.findAllTeachers();
    }

    @GetMapping
    public String findAll(Model model) {

        model.addAttribute("teacher", teacherService.findAllTeachers());

        return "/teachers/allTeachers";
    }

    @GetMapping("/save")
    public String saveTeacherPage(Model model) {

        model.addAttribute("emptyTeacher", new Teacher());

        return "/teachers/saveTeacherPage";
    }

    @PostMapping("/save")
    public String saveTeacher(Teacher teacher) {

        System.out.println(teacher);

        teacherService.save(teacher);

        return "redirect:/api/teachers";
    }
}
