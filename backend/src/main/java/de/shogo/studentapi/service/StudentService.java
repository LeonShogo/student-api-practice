package de.shogo.studentapi.service;

import de.shogo.studentapi.model.Student;
import de.shogo.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import de.shogo.studentapi.exception.StudentNotFoundException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student getStudent(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        existingStudent.setName(updatedStudent.getName());

        existingStudent.setAge(updatedStudent.getAge());

        return studentRepository.save(existingStudent);
    }



}
