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

    @GetMapping("/findBy/{id}") // /find/by/34895703985702938450
    public String findByGroupsId(@PathVariable UUID id, Model model) {

        List<Student> students = studentService.findAllStudentsById(id);

        model.addAttribute("students", students);
        model.addAttribute("groupId", id);

        return "/students/allStudents";
    }

    @GetMapping("/save/{id}")
    public String saveStudentPage(Model model, @PathVariable UUID id) {

        model.addAttribute("emptyStudent", new Student());

        model.addAttribute("groupId", id);

        return "/students/saveStudentPage";
    }

    @PostMapping("/save/{id}")
    public String saveStudent(Student student,
                              @PathVariable UUID id) {

        studentService.save(student, id);

        return "redirect:/api/students/findBy/" + id;
    }

    @GetMapping("/update/{id}")
    public String editStudent(Model model, @PathVariable UUID id){
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "students/updateStudent";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(Student student,
                              @PathVariable UUID id){
        Student byId = studentService.findById(id);
        UUID id1 = byId.getGroups().getId();
        studentService.update(student, id);
        return "redirect:/api/students/findBy/" + id1;
    }

    @GetMapping ("/delete/{studentId}")
    public String deleteById(@PathVariable UUID studentId){
        Student id1 = studentService.findById(studentId);
        UUID id = id1.getGroups().getId();
        studentService.deleteById(studentId);
        return "redirect:/api/students/findBy/" + id;
    }
}
