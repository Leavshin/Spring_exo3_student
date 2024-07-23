package org.example.exercice3.controller;

import org.example.exercice3.model.Student;
import org.example.exercice3.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
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
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
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
    public String searchStudents(@RequestParam(value = "lastname", defaultValue = "") String lastname, Model model) {
        List<Student> students = studentService.searchStudentsByLastname(lastname);
        model.addAttribute("students", students);
        model.addAttribute("lastname", lastname);
        model.addAttribute("searchPerformed", !lastname.isEmpty());
        return "search";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/add")
    public String submitStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        studentService.addStudent(student);
        return "redirect:/list";
    }

    @GetMapping("/update/{studentId}")
    public String formUpdate(@PathVariable("studentId") UUID studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        model.addAttribute("student", student);
        return "register";
    }

    @PostMapping("/update/{studentId}")
    public String updateStudent(@PathVariable("studentId") UUID studentId, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        Student existingStudent = studentService.getStudentById(studentId);
        if (existingStudent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        student.setId(studentId);
        studentService.updateStudent(student);
        return "redirect:/list";
    }

    @GetMapping("/delete/{studentId}")
    public String delete(@PathVariable("studentId") UUID studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/list";
    }

    @GetMapping("/pb")
    public String pb() {
        throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
    }
}
