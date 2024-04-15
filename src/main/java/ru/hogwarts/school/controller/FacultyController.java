package ru.hogwarts.school.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty getFacultyInfo(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PostMapping
    public Faculty addFacultyInfo(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public Faculty updateFacultyInfo(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public boolean deleteFacultyInfo(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("byColorAndName")
    public Collection<Faculty> findFacultiesByColor(@RequestParam(required = false)String color,
                                                    @RequestParam(required = false)String name) {

        if (!StringUtils.isEmpty(color) && StringUtils.isEmpty(name)) {
            return facultyService.findFacultiesByColorAndName(color,name);
        }
        return facultyService.getAll();
    }

    @GetMapping("students")
    public List<Student> getStudentFaculty(@RequestParam long facultyId) {
        return facultyService.getFaculty(facultyId).getStudents();
    }
}
