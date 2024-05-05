package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.exceptions.RecordNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    /*
    @Test
    void shouldAddStudent() {
        // given
        Student student = new Student();

        // when
        Student savedStudent = studentService.addStudent(student);

        // then
        verify(studentRepository).save(student);
        assertThat(savedStudent).isEqualTo(student);
    }

     */

    @Test
    void shouldGetStudent() {
        // given
        long id = 1L;
        Student student = new Student();
        when(studentRepository.findById(id)).thenReturn(java.util.Optional.of(student));

        // when
        Student foundStudent = studentService.getStudent(id);

        // then
        verify(studentRepository).findById(id);
        assertThat(foundStudent).isEqualTo(student);
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFound() {
        // given
        long id = 1L;
        when(studentRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // when & then
        assertThatThrownBy(() -> studentService.getStudent(id)).isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void shouldDeleteStudent() {
        // given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(new Student()));

        // when
        boolean result = studentService.deleteStudent(1L);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotDeleteStudent() {
        // given
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        boolean result = studentService.deleteStudent(1L);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldEditStudent() {
        // given
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(new Student()));
        when(studentRepository.save(student)).thenReturn(student);

        // when
        Student result = studentService.editStudent(student);

        // then
        assertThat(result).isEqualTo(student);
    }

    @Test
    void shouldNotEditFaculty() {
        // given
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        Student result = studentService.editStudent(student);
    }
}


    /*
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

     */