package camt.se494.course.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by Dto on 10/2/2015.
 */
@Entity
public class OpenedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    Course course;
    Integer academicYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenedCourse that = (OpenedCourse) o;

        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        return !(academicYear != null ? !academicYear.equals(that.academicYear) : that.academicYear != null);

    }

    @Override
    public int hashCode() {
        int result = course != null ? course.hashCode() : 0;
        result = 31 * result + (academicYear != null ? academicYear.hashCode() : 0);
        return result;
    }
}
