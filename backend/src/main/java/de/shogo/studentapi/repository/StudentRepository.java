package de.shogo.studentapi.repository;

import de.shogo.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
            extends JpaRepository<Student, Long> {
}

