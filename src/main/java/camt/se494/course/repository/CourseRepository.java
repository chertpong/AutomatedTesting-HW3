package camt.se494.course.repository;

import camt.se494.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */
public interface CourseRepository  extends JpaRepository<Course,Long>{
    List<Course> findByCourseIdContainingIgnoreCaseOrCourseNameContainingIgnoreCase(String courseId,String courseName);
}
