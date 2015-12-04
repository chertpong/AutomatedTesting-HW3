package camt.se494.course.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dto on 10/1/2015.
 */

@Entity
public class Student {
    // Key for database only\
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        return !(courseEnrolments != null ? !courseEnrolments.equals(student.courseEnrolments) : student.courseEnrolments != null);

    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (courseEnrolments != null ? courseEnrolments.hashCode() : 0);
        return result;
    }

    String name;

    @OneToMany(mappedBy = "student")
    @Cascade(CascadeType.ALL)
    List<CourseEnrolment> courseEnrolments = new ArrayList<CourseEnrolment>();

    public List<CourseEnrolment> getCourseEnrolments() {
        return courseEnrolments;
    }

    public void setCourseEnrolments(List<CourseEnrolment> courseEnrolments) {
        this.courseEnrolments = courseEnrolments;
    }

    public double getGpa() {
        return 0.0;
    }

    ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
