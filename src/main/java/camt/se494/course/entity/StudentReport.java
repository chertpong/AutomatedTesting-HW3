package camt.se494.course.entity;

import java.util.*;

/**
 * Created by Dto on 10/1/2015.
 */
public class StudentReport {
    Student student;
    Map<Integer,List<CourseEnrolment>> enrolmentMap = new TreeMap<>();
    Map<Integer,Double> gpaMap = new TreeMap<>();

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map<Integer, List<CourseEnrolment>> getEnrolmentMap() {
        return enrolmentMap;
    }

    public void setEnrolmentMap(Map<Integer, List<CourseEnrolment>> enrolmentMap) {
        this.enrolmentMap = enrolmentMap;
    }

    public Map<Integer, Double> getGpaMap() {
        return gpaMap;
    }

    public void setGpaMap(Map<Integer, Double> gpaMap) {
        this.gpaMap = gpaMap;
    }
}
