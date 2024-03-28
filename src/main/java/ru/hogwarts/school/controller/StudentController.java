package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student getStudentInfo(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student addStudentInfo(@RequestBody Student Student) {
        return studentService.addStudent(Student);
    }

    @PutMapping
    public Student updateStudentInfo(@RequestBody Student Student) {
        return studentService.editStudent(Student);
    }

    @DeleteMapping("{id}")
    public Student deleteStudentInfo(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }


    @GetMapping("/age")
    public Collection<Student> findStudentsByAge(@RequestParam ("age") int age) {
        return studentService.findStudentsByAge(age);
    }

}
