package camt.se494.course.repository;

import camt.se494.course.entity.Course;
import camt.se494.course.entity.OpenedCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
public interface OpenedCourseRepository extends JpaRepository<OpenedCourse,Long> {
    List<OpenedCourse> findByAcademicYear(Integer academicYear);
    OpenedCourse findByCourseAndAcademicYear(Course course,Integer academicYear);
}
