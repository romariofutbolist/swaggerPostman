package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private Map<Long, Faculty> faculties = new HashMap<>();
    private long COUNT = 0;

    public Faculty addFaculty(Faculty faculty) {
        if (faculty == null) {
            throw new NullPointerException();
        }
        faculty.setId(++COUNT);
        faculties.put(COUNT, faculty);
        return faculty;
    }

    public Faculty getFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty deleteFaculty(long id) {
        if (!faculties.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return faculties.remove(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Collection<Faculty> findFacultiesByColor(String color) {
        return faculties.values().stream().
                filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }
}


