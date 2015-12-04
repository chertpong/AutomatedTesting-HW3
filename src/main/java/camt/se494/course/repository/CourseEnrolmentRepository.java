package camt.se494.course.repository;

import camt.se494.course.entity.Course;
import camt.se494.course.entity.CourseEnrolment;
import camt.se494.course.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */
public interface CourseEnrolmentRepository extends JpaRepository<CourseEnrolment,Long>{
    List<CourseEnrolment> findByOpenedCourseAcademicYear(Integer academicYear);
    List<CourseEnrolment> findByOpenedCourseCourseAndOpenedCourseAcademicYear(Course course,Integer academicYear);
    List<CourseEnrolment> findByStudent(Student student);
    List<CourseEnrolment> findByStudentAndOpenedCourseAcademicYear(Student student,Integer academicYear);
}
