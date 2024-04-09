package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

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

    @GetMapping("/color")
    public Collection<Faculty> findFacultiesByColor(@RequestParam ("color") String color) {
        return facultyService.findFacultiesByColor(color);
    }
}
