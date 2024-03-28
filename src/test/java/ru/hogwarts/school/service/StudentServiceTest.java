package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    StudentService service = new StudentService();

    @Test
    void addStudent() {
        Student actual = new Student(1L,"Valera", 18);
        service.addStudent(actual);

        assertThrows(NullPointerException.class, () -> service.addStudent(null));

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo("Valera");
        assertThat(actual.getAge()).isEqualTo(18);
        assertThat(actual.getId()).isEqualTo(1L);
    }

    @Test
    void getStudent() {
        Student actual = new Student(1L,"Valera", 18);
        service.addStudent(actual);
        assertThat(service.getStudent(1L)).isEqualTo(actual);
    }

    @Test
    void deleteStudent() {
        Student actual = new Student(1L,"Valera", 18);
        service.addStudent(actual);

        assertThrows(IllegalArgumentException.class, () -> service.deleteStudent(2));

        assertThat(actual).isNotNull();
        assertThat(service.deleteStudent(1L)).isEqualTo(actual);
    }

    @Test
    void editStudent() {
        Student actual = new Student(1L,"Valera", 18);
        service.addStudent(actual);

        assertThat(actual).isNotNull();
        assertEquals("Valera", service.editStudent(actual).getName());

        actual.setId(100L);
        assertNull(service.editStudent(actual));
    }

    @Test
    void findStudentsByAge() {
        Student actual = new Student(1L,"Valera", 18);
        Student actual2 = new Student(2L,"Anna", 20);
        Student actual3 = new Student(3L,"Elena", 20);
        service.addStudent(actual);
        service.addStudent(actual2);
        service.addStudent(actual3);
        Collection<Student> result = service.findStudentsByAge(20);

        assertEquals(2, result.size());
        for (Student student : result) {
            assertEquals(20, student.getAge());
        }
    }
}