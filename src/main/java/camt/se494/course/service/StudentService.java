package camt.se494.course.service;

import camt.se494.course.entity.Student;
import camt.se494.course.entity.StudentReport;

import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */
public interface StudentService {
    List<Student> getStudent();
    List<Student> getStudent(String partial);
    List<Student> getStudentGradeLowerThan(double gpa);
    List<Student> getStudentGradeGreaterThan(double gpa);
    double getStudentGpa(Student student);
    double getStudentGpa(Student student,Integer academicYear);
    StudentReport getStudentReport(Student student);
}
