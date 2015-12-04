package camt.se494.course.service;

import camt.se494.course.entity.Course;
import camt.se494.course.entity.CourseEnrolment;
import camt.se494.course.entity.CourseReport;

import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */
public interface CourseService {
    List<Course> getCourse();
    List<Course> getCourse(String partial);
    List<Course> getCourse(Integer academicYear);
    List<Course> getCourse(String partial,Integer academicYear);
    CourseReport getCourseReport(Course course, Integer academicYear);
    double getCourseGpa(List<CourseEnrolment> courseEnrolments);
}
