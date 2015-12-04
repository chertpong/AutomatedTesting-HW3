package camt.se494.course.repository;

import camt.se494.course.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByStudentIdContainingIgnoreCaseOrNameContainingIgnoreCase(String studentId, String name);
}
