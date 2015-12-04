package camt.se494.course.service;

import camt.se494.course.dao.CourseDao;
import camt.se494.course.dao.CourseEnrolmentDao;
import camt.se494.course.dao.OpenedCourseDao;
import camt.se494.course.entity.*;
import camt.se494.course.service.util.GradeMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dto on 10/2/2015.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    OpenedCourseDao openedCourseDao;
    @Autowired
    CourseEnrolmentDao courseEnrolmentDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setOpenedCourseDao(OpenedCourseDao openedCourseDao) {
        this.openedCourseDao = openedCourseDao;
    }

    public void setCourseEnrolmentDao(CourseEnrolmentDao courseEnrolmentDao) {
        this.courseEnrolmentDao = courseEnrolmentDao;
    }

    @Override
    @Transactional
    public List<Course> getCourse() {
        return courseDao.getCourse();
    }

    @Override
    @Transactional
    public List<Course> getCourse(String partial) {
        return courseDao.getCourse(partial);
    }

    @Override
    @Transactional
    public List<Course> getCourse(Integer academicYear) {
        List<OpenedCourse> openedCourses = openedCourseDao.getOpenedCourse(academicYear);
        List<Course> output = new ArrayList<Course>();
        for(OpenedCourse openedCourse:openedCourses){
            output.add(openedCourse.getCourse());
        }
        return output;

    }


    @Override
    @Transactional
    public List<Course> getCourse(String partial, Integer academicYear) {
        List<OpenedCourse> openedCourses = openedCourseDao.getOpenedCourse(academicYear);
        List<Course> output = new ArrayList<Course>();
        String partialLowerCase = partial.toLowerCase();
        for(OpenedCourse openedCourse:openedCourses){
            if (openedCourse.getCourse().getCourseId().toLowerCase().contains(partialLowerCase) ||
                    openedCourse.getCourse().getCourseName().contains(partialLowerCase) ) {
                output.add(openedCourse.getCourse());
            }
        }
        return output;

    }

    @Override
    @Transactional
    public CourseReport getCourseReport(Course course, Integer academicYear) {
        CourseReport courseReport = new CourseReport();
        courseReport.setCourse(course);
        // getStudents
        // get all the courseEnrolmentObject first
        List<CourseEnrolment> courseEnrolments = courseEnrolmentDao.getCourseEnrolments(course,academicYear);
        //get List of Student
        List<Student> students = new ArrayList<>();
        for (CourseEnrolment courseEnrolment:courseEnrolments){
            students.add(courseEnrolment.getStudent());
        }

        courseReport.setTotalStudent(students.size());
        courseReport.setAverageGpa(getCourseGpa(courseEnrolments));
        return courseReport;
    }

    @Override
    public double getCourseGpa(List<CourseEnrolment> courseEnrolments) {
        GradeMatcher gradeMatcher = new GradeMatcher();
        double totalGpa = 0;
        for(CourseEnrolment courseEnrolment:courseEnrolments){
            totalGpa += gradeMatcher.getGradeScore(courseEnrolment.getGrade());
        }
        return totalGpa/courseEnrolments.size();
    }
}
