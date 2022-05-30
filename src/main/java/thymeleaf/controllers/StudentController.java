package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Group;
import thymeleaf.models.Student;
import thymeleaf.services.StudentService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("studentList")
    public List<Student> findAllStudents() {

        return studentService.findAllStudents();
    }

    @GetMapping
    public String findAll(Model model) {

        model.addAttribute("student", studentService.findAllStudents());

        return "/students/allStudents";
    }

    @GetMapping("/save")
    public String saveStudentPage(Model model) {

        model.addAttribute("emptyStudent", new Student());

        return "/students/saveStudentPage";
    }

    @PostMapping("/save")
    public String saveStudent(Student student) {

        System.out.println(student);

        studentService.save(student);

        return "redirect:/api/students";
    }

    @GetMapping("/update/{id}")
    public String editStudent(Model model, @PathVariable("id") UUID id){
        model.addAttribute("student", studentService.findById(id));
        return "students/updateStudent";
    }

    @GetMapping("/find/by/{courseId}") // /find/by/34895703985702938450
    public String findAllStudentsByCourseId(@PathVariable UUID courseId, Model model) {

        List<Student> students = studentService.findByCourseId(courseId);

        model.addAttribute("students", students);
        model.addAttribute("courseId", courseId);

        return "students/allStudents";
    }



    @PostMapping("/{id}")
    public String updateStudent(@ModelAttribute("student") Student student,
                              @PathVariable("id") UUID id){
        studentService.update(student, id);
        return "redirect:/api/students";
    }
}
