package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

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
    public boolean deleteStudentInfo(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/byAge")
    public Collection<Student> findByAgeBetween(@RequestParam(required = false) Integer min,
                                                @RequestParam(required = false) Integer max) {
        if (min != null && max != null) {
            return studentService.getByAgeBetween(min, max);
        }
        return studentService.getAll();
    }

    @GetMapping("faculty")
    public Faculty getStudentFaculty(@RequestParam long studentId) {
        return studentService.getStudent(studentId).getFaculty();
    }
}
