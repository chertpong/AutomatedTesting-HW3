package camt.se494.course.dao;

import camt.se494.course.entity.OpenedCourse;
import camt.se494.course.repository.OpenedCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
@Repository
public class OpenedCourseDaoImpl implements OpenedCourseDao {

    @Autowired
    OpenedCourseRepository openedCourseRepository ;
    @Override
    public List<OpenedCourse> getOpenedCourse() {
        return openedCourseRepository.findAll();
    }

    @Override
    public List<OpenedCourse> getOpenedCourse(Integer academicYear) {
        return openedCourseRepository.findByAcademicYear(academicYear);
    }
}
