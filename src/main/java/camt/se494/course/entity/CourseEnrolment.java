package camt.se494.course.entity;

import javax.persistence.*;

/**
 * Created by Dto on 10/1/2015.
 */
@Entity
public class CourseEnrolment  implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne
    Student student;

    public OpenedCourse getOpenedCourse() {
        return openedCourse;
    }

    public void setOpenedCourse(OpenedCourse openedCourse) {
        this.openedCourse = openedCourse;
    }

    @ManyToOne
    OpenedCourse openedCourse;
    String grade;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEnrolment that = (CourseEnrolment) o;

        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        if (openedCourse != null ? !openedCourse.equals(that.openedCourse) : that.openedCourse != null) return false;
        return !(grade != null ? !grade.equals(that.grade) : that.grade != null);

    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (openedCourse != null ? openedCourse.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }


    @Override
    public int compareTo(Object o) {
        CourseEnrolment c = (CourseEnrolment)o;
        int yearCompare = this.getOpenedCourse().getAcademicYear().compareTo(c.getOpenedCourse().getAcademicYear());
        if ( yearCompare != 0){
            return yearCompare;
        }else
        {
            return this.getOpenedCourse().getCourse().getCourseId().compareTo(c.getOpenedCourse().getCourse().getCourseId());
        }
    }
}
