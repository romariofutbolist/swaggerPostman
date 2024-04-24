package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FacultyControllerTestWevMvc {

    @Autowired
    MockMvc mvc;

    @MockBean
    FacultyRepository facultyRepository;

    @SpyBean
    FacultyService facultyService;

    @MockBean
    AvatarService avatarService;

    @MockBean
    StudentService studentService;

    @InjectMocks
    FacultyController facultyController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGet() throws Exception {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(new Faculty(1L, "test_faculty_mvc", "test_color_mvc")));
        mvc.perform(MockMvcRequestBuilders.get("/faculty?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test_faculty_mvc"))
                .andExpect(jsonPath("$.color").value("test_color_mvc"));

    }

    @Test
    void testUpdate() throws Exception {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(new Faculty(1L, "test_faculty_mvc", "test_color_mvc")));
        Faculty faculty = new Faculty(1L, "updated_name", "updated_color");
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        ObjectMapper objectMapper = new ObjectMapper();
        mvc.perform(MockMvcRequestBuilders.put("/faculty?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("updated_name"))
                .andExpect(jsonPath("$.color").value("updated_color"));

    }

}