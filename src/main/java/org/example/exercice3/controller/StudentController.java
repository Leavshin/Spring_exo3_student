package org.example.exercice3.controller;

import org.example.exercice3.model.Student;
import org.example.exercice3.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String homePage() {
        return "welcome";
    }

    @GetMapping("/detail/{studentId}")
    public String detailPage(@PathVariable("studentId") UUID studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "details";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list";
    }

    @GetMapping("/search")
    public String searchPage(Model model) {
        model.addAttribute("students", null);
        return "search";
    }

    @PostMapping("/search")
    public String showStudents(@RequestParam("lastname") String lastname, Model model) {
        List<Student> students = studentService.getStudentsByLastname(lastname);
        model.addAttribute("students", students);
        return "search";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("action", "/add");
        return "register";
    }

    @PostMapping("/add")
    public String submitStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/list";
    }

    @GetMapping("/update/{studentId}")
    public String formUpdate(@PathVariable("studentId") UUID id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("action", "/update");
        return "register";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/list";
    }

    @GetMapping("/delete/{studentId}")
    public String delete(@PathVariable("studentId") UUID id) {
        studentService.deleteStudent(id);
        return "redirect:/list";
    }
}
