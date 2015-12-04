package camt.se494.course.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */
public class CourseReport {
    Course course;

    public Course getCourse() {

        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    List<Student> students  = new ArrayList<Student>();
    double averageGpa;
    double totalStudent;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public double getAverageGpa() {
        return averageGpa;
    }

    public void setAverageGpa(double averageGpa) {
        this.averageGpa = averageGpa;
    }

    public double getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(double totalStudent) {
        this.totalStudent = totalStudent;
    }
}
