package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/findBy/{id}") // /find/by/34895703985702938450
    public String findByCourseId(@PathVariable UUID id, Model model) {

        List<Teacher> teachers = teacherService.findAllTeachersById(id);

        model.addAttribute("teachers", teachers);
        model.addAttribute("courseId", id);

        return "/teachers/allTeachers";
    }
    @GetMapping("/save/{id}")
    public String saveTeacherPage(Model model, @PathVariable UUID id) {

        model.addAttribute("emptyTeacher", new Teacher());

        model.addAttribute("id", id);

        return "/teachers/saveTeacherPage";
    }

    @PostMapping("/save/{id}")
    public String saveTeacher(Teacher teacher,
                              @PathVariable("id") UUID id) {

        teacherService.save(teacher, id);

        return "redirect:/api/teachers/findBy/" + id;
    }

    @GetMapping("/update/{id}")
    public String editTeacher(Model model, @PathVariable UUID id){
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/updateTeacher";
    }


    @PostMapping("/update/{id}")
    public String updateTeacher(Teacher teacher,
                                @PathVariable UUID id){
        Teacher byId = teacherService.findById(id);
        UUID id1 = byId.getCourse().getId();
        teacherService.update(teacher, id);
        return "redirect:/api/teachers/findBy/" + id1;
    }

    @GetMapping("/delete/{teacherId}")
    public String deleteById(@PathVariable("teacherId") UUID teacherId, Model model){
        System.out.println("teacherId = " + teacherId);

        Teacher teacher = teacherService.findById(teacherId);

        UUID id1 = teacher.getCourse().getId();

        teacherService.deleteById(teacherId);


        model.addAttribute("courseId", id1);
        model.addAttribute("teachers", teacherService.findAllTeachers());

        return "/teachers/allTeachers";
    }
}
