package camt.se494.course.dao;

import camt.se494.course.entity.Course;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
public interface CourseDao {
    List<Course> getCourse();
    List<Course> getCourse(String partial);
}
