package camt.se494.course.dao;

import camt.se494.course.entity.Course;
import camt.se494.course.entity.CourseEnrolment;
import camt.se494.course.entity.Student;
import camt.se494.course.repository.CourseEnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
@Repository
public class CourseEnrolmentDaoImpl  implements  CourseEnrolmentDao{
    @Autowired
    CourseEnrolmentRepository courseEnrolmentRepository;
    @Override
    public List<CourseEnrolment> getCourseEnrolments() {
        return courseEnrolmentRepository.findAll();
    }

    @Override
    public List<CourseEnrolment> getCourseEnrolments(int academicYear) {

        return courseEnrolmentRepository.findByOpenedCourseAcademicYear(academicYear);
    }

    @Override
    public List<CourseEnrolment> getCourseEnrolments(Course course, int academicYear) {
        return courseEnrolmentRepository.findByOpenedCourseCourseAndOpenedCourseAcademicYear(course,academicYear);
    }

    @Override
    public List<CourseEnrolment> getCourseEnrolments(Student student) {
        return courseEnrolmentRepository.findByStudent(student);
    }

    @Override
    public List<CourseEnrolment> getCourseEnrolments(Student student, int academicYear) {
        return courseEnrolmentRepository.findByStudentAndOpenedCourseAcademicYear(student,academicYear);
    }
}
