package camt.se494.course.dao;

import camt.se494.course.entity.Course;
import camt.se494.course.entity.CourseEnrolment;
import camt.se494.course.entity.Student;

import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */
public interface StudentDao {
    List<Student> getStudent();
    List<Student> getStudent(String partial);

}
