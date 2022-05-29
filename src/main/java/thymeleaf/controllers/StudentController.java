package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Student;
import thymeleaf.services.StudentService;

import java.util.List;

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
}
