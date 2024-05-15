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

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    class FacultyServiceTest {

        @Mock
        private FacultyRepository facultyRepository;
        private FacultyService facultyService;

        @BeforeEach
        void setUp() {
            facultyService = new FacultyService(facultyRepository);
        }

        /*
        @Test
        void shouldAddFaculty() {
            // given
            Faculty faculty = new Faculty(1L,"ss","ww");

            // when
            Faculty savedFaculty = facultyService.addFaculty(faculty);

            // then
            verify(facultyRepository).save(faculty);
            assertThat(savedFaculty).isEqualTo(faculty);
        }

         */

        @Test
        void shouldGetFaculty() {
            // given
            long id = 1L;
            Faculty faculty = new Faculty();
            when(facultyRepository.findById(id)).thenReturn(java.util.Optional.of(faculty));

            // when
            Faculty foundFaculty = facultyService.getFaculty(id);

            // then
            verify(facultyRepository).findById(id);
            assertThat(foundFaculty).isEqualTo(faculty);
        }

        @Test
        void shouldThrowExceptionWhenFacultyNotFound() {
            // given
            long id = 1L;
            when(facultyRepository.findById(id)).thenReturn(java.util.Optional.empty());

            // when & then
            assertThatThrownBy(() -> facultyService.getFaculty(id)).isInstanceOf(RecordNotFoundException.class);
        }

        @Test
        void shouldDeleteFaculty() {

            Faculty actual = new Faculty(1L,"Grif","orange");
            actual.setId(1L);

            when(facultyRepository.findById(1L)).thenReturn(Optional.of(new Faculty()));

            boolean result = facultyService.deleteFaculty(1L);

            assertThat(result).isTrue();
        }

        @Test
        void shouldEditFaculty() {
            // given
            Faculty faculty = new Faculty();
            faculty.setId(1L);
            when(facultyRepository.findById(1L)).thenReturn(Optional.of(new Faculty()));
            when(facultyRepository.save(faculty)).thenReturn(faculty);

            // when
            Faculty result = facultyService.editFaculty(faculty);

            // then
            assertThat(result).isEqualTo(faculty);
        }

        @Test
        void shouldNotEditFaculty() {
            // given
            Faculty faculty = new Faculty();
            faculty.setId(1L);
            when(facultyRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Faculty result = facultyService.editFaculty(faculty);
        }
    }


    /*

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

     */
