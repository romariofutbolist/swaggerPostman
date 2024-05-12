package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("The faculty has been added to the application");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        logger.info("Received the Faculty of ID");
        return facultyRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public boolean deleteFaculty(long id) {
        logger.info("The faculty of ID has been deleted");
        return facultyRepository.findById(id)
                .map(entity -> {
                    facultyRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("The faculty has been updated");
        return facultyRepository.findById(faculty.getId())
                .map(entity -> facultyRepository.save(faculty))
                .orElse(null);
    }

    public Collection<Faculty> findFacultiesByColorAndName(String color, String name) {
        logger.info("Faculty by name and color found");
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Collection<Faculty> getAll() {
        logger.info("All faculties have been received");
        return facultyRepository.findAll();
    }
}


