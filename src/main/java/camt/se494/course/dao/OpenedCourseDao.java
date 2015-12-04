package camt.se494.course.dao;

import camt.se494.course.entity.OpenedCourse;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
public interface OpenedCourseDao {
    List<OpenedCourse> getOpenedCourse();
    List<OpenedCourse> getOpenedCourse(Integer academicYear);
}
