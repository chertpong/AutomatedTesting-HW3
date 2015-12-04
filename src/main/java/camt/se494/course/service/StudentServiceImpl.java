package camt.se494.course.service;

import camt.se494.course.dao.CourseEnrolmentDao;
import camt.se494.course.dao.StudentDao;
import camt.se494.course.entity.CourseEnrolment;
import camt.se494.course.entity.Student;
import camt.se494.course.entity.StudentReport;
import camt.se494.course.service.util.GradeMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Dto on 10/2/2015.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;
    @Autowired
    CourseEnrolmentDao courseEnrolmentDao;

    public void setGradeMatcher(GradeMatcher gradeMatcher) {
        this.gradeMatcher = gradeMatcher;
    }

    GradeMatcher gradeMatcher ;
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setCourseEnrolmentDao(CourseEnrolmentDao courseEnrolmentDao) {
        this.courseEnrolmentDao = courseEnrolmentDao;
    }

    @Override
    @Transactional
    public List<Student> getStudent() {

        return studentDao.getStudent();
    }

    @Override
    @Transactional
    public List<Student> getStudent(String partial) {
        return studentDao.getStudent(partial);
    }

    @Override
    @Transactional
    public List<Student> getStudentGradeLowerThan(double gpa) {
        return getStudentGradeOnBound(gpa, (n) -> (n < gpa));
    }

    @Override
    @Transactional
    public List<Student> getStudentGradeGreaterThan(double gpa) {
        return getStudentGradeOnBound(gpa, (n) -> (n < gpa));
    }


    private List<Student> getStudentGradeOnBound(double gpa, Predicate<Double> predicate) {
        List<Student> students = studentDao.getStudent();
        List<Student> output = new ArrayList<Student>();
        for (Student student : students) {
            double studentGpa = getStudentGpa(student);
            if (predicate.test(studentGpa)) {
                output.add(student);
            }
        }
        return output;
    }

    ;

    @Override
    public double getStudentGpa(Student student) {
        if (gradeMatcher == null){
            throw new RuntimeException("Grade matcher has not been set!!!");
        }
        double gpa = 0;
        List<CourseEnrolment> courseEnrolments = student.getCourseEnrolments();
        for (CourseEnrolment courseEnrolment : courseEnrolments) {
            gpa += gradeMatcher.getGradeScore(courseEnrolment.getGrade());
        }
        return gpa / courseEnrolments.size();
    }

    @Override
    public double getStudentGpa(Student student, Integer academicYear) {
        GradeMatcher gradeMatcher = new GradeMatcher();
        double gpa = 0;
        int count = 0;
        List<CourseEnrolment> courseEnrolments = student.getCourseEnrolments();
        for (CourseEnrolment courseEnrolment : courseEnrolments) {
            {
                gpa += gradeMatcher.getGradeScore(courseEnrolment.getGrade());
                count++;
            }
        }
        return gpa / count;

    }

    @Override
    @Transactional
    public StudentReport getStudentReport(Student student) {
        StudentReport studentReport = new StudentReport();
        studentReport.setStudent(student);

        List<CourseEnrolment> courseEnrolments = courseEnrolmentDao.getCourseEnrolments(student);
        List<Integer> registerYear = getRegisterYear(courseEnrolments);
        for (Integer academicYear:registerYear){
            List<CourseEnrolment> registerCourses = new ArrayList<>();
            for(CourseEnrolment courseEnrolment:courseEnrolments){
                if (courseEnrolment.getOpenedCourse().getAcademicYear().equals(academicYear)){
                    registerCourses.add(courseEnrolment);
                }
            }
            Collections.sort(registerCourses);
            studentReport.getEnrolmentMap().put(academicYear,registerCourses);
            studentReport.getGpaMap().put(academicYear,getStudentGpa(student,academicYear));
        }

        return studentReport;
    }

    public List<Integer> getRegisterYear(List<CourseEnrolment> courseEnrolments){
        Set<Integer> result = new HashSet<>();
        for(CourseEnrolment courseEnrolment:courseEnrolments){
            result.add(courseEnrolment.getOpenedCourse().getAcademicYear());
        }

        List<Integer> output = new ArrayList<Integer>(result);
       
        return output;

    }
}
