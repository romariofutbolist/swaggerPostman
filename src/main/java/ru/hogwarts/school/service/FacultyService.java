package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.RecordNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public boolean deleteFaculty(long id) {
        return facultyRepository.findById(id)
                .map(entity -> {
                    facultyRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.findById(faculty.getId())
                .map(entity -> facultyRepository.save(faculty))
                .orElse(null);
    }

    public Collection<Faculty> findFacultiesByColorAndName(String color, String name) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }
}


