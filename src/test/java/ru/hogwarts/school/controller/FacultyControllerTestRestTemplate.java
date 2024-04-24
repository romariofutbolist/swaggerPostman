package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTestRestTemplate {

    @LocalServerPort
    int port;

    @Autowired
    FacultyController facultyController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testGetFaculty() throws Exception {
        Faculty faculty = new Faculty(null,"test_faculty", "test_color");
        ResponseEntity<Faculty> postResponse = testRestTemplate.postForEntity("/faculty", faculty, Faculty.class);
        Faculty addedFaculty = postResponse.getBody();

        var result = testRestTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + addedFaculty.getId(), Faculty.class);
        assertThat(result.getColor()).isEqualTo("test_color");
        assertThat(result.getName()).isEqualTo("test_faculty");

        ResponseEntity<Faculty> resultAfterDelete = testRestTemplate.exchange("/faculty?id=-1", HttpMethod.GET, null, Faculty.class);
        assertThat(resultAfterDelete.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void testDelete() {
        Faculty faculty = new Faculty(null,"test_faculty", "test_color");
        ResponseEntity<Faculty> postResponse = testRestTemplate.postForEntity("/faculty", faculty, Faculty.class);
        Faculty addedFaculty = postResponse.getBody();

        var result = testRestTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + addedFaculty.getId(), Faculty.class);
        assertThat(result.getColor()).isEqualTo("test_color");
        assertThat(result.getName()).isEqualTo("test_faculty");

        testRestTemplate.delete("/faculty?id=" + addedFaculty.getId());

        ResponseEntity<Faculty> resultAfterDelete = testRestTemplate.exchange("/faculty?id=-1", HttpMethod.GET, null, Faculty.class);
        assertThat(resultAfterDelete.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void testUpdate() {
        Faculty faculty = new Faculty(null,"test_faculty", "test_color");
        ResponseEntity<Faculty> postResponse = testRestTemplate.postForEntity("/faculty", faculty, Faculty.class);
        Faculty addedFaculty = postResponse.getBody();

        addedFaculty.setName("changed_name");
        addedFaculty.setColor("changed_color");
        testRestTemplate.put("/faculty?id=" + addedFaculty.getId(), addedFaculty);

        var result = testRestTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + addedFaculty.getId(), Faculty.class);
        assertThat(result.getColor()).isEqualTo("changed_color");
        assertThat(result.getName()).isEqualTo("changed_name");
    }

    @Test
    void testFilter() {
        var f1 = testRestTemplate.postForEntity("/faculty", new Faculty(null, "test_name1", "test_color1"), Faculty.class).getBody();
        var f2 = testRestTemplate.postForEntity("/faculty", new Faculty(null, "test_name2", "test_color2"), Faculty.class).getBody();
        var f3 = testRestTemplate.postForEntity("/faculty", new Faculty(null, "test_name3", "test_color3"), Faculty.class).getBody();
        var f4 = testRestTemplate.postForEntity("/faculty", new Faculty(null, "test_name4", "test_color4"), Faculty.class).getBody();

        var faculties = testRestTemplate.getForObject("/faculty/byColorAndName?name=test_name1&color=test_color2", Faculty[].class);
        assertThat(faculties.length).isEqualTo(2);
        assertThat(faculties).containsExactlyInAnyOrder(f1,f2);
    }
}