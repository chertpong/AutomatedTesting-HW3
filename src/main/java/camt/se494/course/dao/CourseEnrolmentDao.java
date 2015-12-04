package camt.se494.course.dao;

import camt.se494.course.entity.Course;
import camt.se494.course.entity.CourseEnrolment;
import camt.se494.course.entity.Student;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
public interface CourseEnrolmentDao {
    List<CourseEnrolment> getCourseEnrolments();
    List<CourseEnrolment> getCourseEnrolments(int academicYear);
    List<CourseEnrolment> getCourseEnrolments(Course course, int academicYear);
    List<CourseEnrolment> getCourseEnrolments(Student student);
    List<CourseEnrolment> getCourseEnrolments(Student student, int academicYear);

}
