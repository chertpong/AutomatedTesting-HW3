package camt.se494.course.dao;

import camt.se494.course.entity.Course;
import camt.se494.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
@Repository
public class CourseDaoImpl  implements CourseDao{
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<Course> getCourse() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCourse(String partial) {
        return courseRepository.findByCourseIdContainingIgnoreCaseOrCourseNameContainingIgnoreCase(partial,partial);
    }


}
