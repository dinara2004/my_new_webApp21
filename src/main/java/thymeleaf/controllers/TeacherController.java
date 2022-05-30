package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Student;
import thymeleaf.models.Teacher;
import thymeleaf.services.TeacherService;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/update/{id}")
    public String editTeacher(Model model, @PathVariable("id") UUID id){
        model.addAttribute("teacher", teacherService.findById(id));
        return "teachers/updateTeacher";
    }


    @PostMapping("/{id}")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher,
                                @PathVariable("id") UUID id){
        teacherService.update(teacher, id);
        return "redirect:/api/teachers";
    }
}
