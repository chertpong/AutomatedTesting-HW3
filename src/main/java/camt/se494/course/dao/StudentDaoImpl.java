package camt.se494.course.dao;

import camt.se494.course.entity.Course;
import camt.se494.course.entity.CourseEnrolment;
import camt.se494.course.entity.Student;
import camt.se494.course.entity.StudentReport;
import camt.se494.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudent(String partial) {
        return studentRepository.findByStudentIdContainingIgnoreCaseOrNameContainingIgnoreCase(partial,partial);
    }


}
