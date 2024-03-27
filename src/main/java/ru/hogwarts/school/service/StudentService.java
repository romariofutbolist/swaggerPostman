package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private long COUNT = 0;

    public Student addStudent(Student student) {
        if (student == null) {
            throw new NullPointerException();
        }
        student.setId(++COUNT);
        students.put(COUNT, student);
        return student;
    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public Student deleteStudent(long id) {
        if (!students.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return students.remove(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Collection<Student> findStudentsByAge(int age) {
        return students.values().stream().
                filter(student -> student.getAge()==age)
                .toList();
    }
}
