package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {

    FacultyService service = new FacultyService();

    @Test
    void addFaculty() {
        Faculty actual = new Faculty(1L,"Grif","orange");
        service.addFaculty(actual);

        assertThrows(NullPointerException.class, () -> service.addFaculty(null));

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo("Grif");
        assertThat(actual.getColor()).isEqualTo("orange");
        assertThat(actual.getId()).isEqualTo(1L);
    }

    @Test
    void getFaculty() {
        Faculty actual = new Faculty(1L,"Grif","orange");
        service.addFaculty(actual);
        assertThat(service.getFaculty(1L)).isEqualTo(actual);
    }

    @Test
    void deleteFaculty() {
        Faculty actual = new Faculty(1L,"Grif","orange");
        service.addFaculty(actual);

        assertThrows(IllegalArgumentException.class, () -> service.deleteFaculty(2));

        assertThat(actual).isNotNull();
        assertThat(service.deleteFaculty(1L)).isEqualTo(actual);
    }

    @Test
    void editFaculty() {
        Faculty actual = new Faculty(1L,"Grif","orange");
        service.addFaculty(actual);

        assertThat(actual).isNotNull();
        assertEquals("Grif", service.editFaculty(actual).getName());

        actual.setId(100L);
        assertNull(service.editFaculty(actual));
    }

    @Test
    void findFacultiesByColor() {
        Faculty actual = new Faculty(1L,"Grif","orange");
        Faculty actual2 = new Faculty(2L,"Sliz","green");
        service.addFaculty(actual);
        service.addFaculty(actual2);
        Collection<Faculty> result = service.findFacultiesByColor("orange");

        assertEquals(1, result.size());
        for (Faculty faculty : result) {
            assertEquals("orange", faculty.getColor());
        }
    }
}