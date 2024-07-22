package org.example.exercice3.service;

import org.example.exercice3.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<UUID, Student> students;

    public StudentService(){
        students = new HashMap<>();

        Student studentA = Student.builder()
                .id(UUID.randomUUID())
                .lastname("Toto")
                .firstname("Tata")
                .age(18)
                .email("toto.tata@gmail.com")
                .build();
        Student studentB = Student.builder()
                .id(UUID.randomUUID())
                .lastname("Tutu")
                .firstname("Titi")
                .age(18)
                .email("tutu.titi@gmail.com")
                .build();
        Student studentC = Student.builder()
                .id(UUID.randomUUID())
                .lastname("Tete")
                .firstname("Tyty")
                .age(18)
                .email("tete.tyty@gmail.com")
                .build();

        students.put(studentA.getId(), studentA);
        students.put(studentB.getId(), studentB);
        students.put(studentC.getId(), studentC);
    }

    public List<Student> getAllStudents() {
        return students.values().stream().toList();
    }

    public Student getStudentById(UUID id) {
        return students.get(id);
    }

    public List<Student> getStudentsByLastname(String lastname) {
        return students.values().stream()
                .filter(c -> c.getLastname().equalsIgnoreCase(lastname))
                .collect(Collectors.toList());
    }

    public void addStudent(Student student) {
        student.setId(UUID.randomUUID());
        students.put(student.getId(), student);
    }

    public void updateStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void deleteStudent(UUID id) {
        students.remove(id);
    }
}
