package camt.se494.course.service;

/**
 * Created by Chertpong on 4/12/2558.
 */
import camt.se494.course.dao.CourseDao;
import camt.se494.course.dao.CourseEnrolmentDao;
import camt.se494.course.dao.OpenedCourseDao;
import camt.se494.course.dao.StudentDao;
import camt.se494.course.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
public class CourseServiceImplTest {

    CourseServiceImpl courseService;
    CourseDao courseDao;
    CourseEnrolmentDao courseEnrolmentDao;
    OpenedCourseDao openedCourseDao;
//    test data
    Student student,student2;
    Course course,course2,course3;
    OpenedCourse openedCourse,openedCourse2;
    CourseEnrolment courseEnrolment,courseEnrolment2,courseEnrolment3,courseEnrolment4;

    @Before
    public void setUp() throws Exception {
        courseService = spy(CourseServiceImpl.class);
//        Mock students
        student = mock(Student.class);
        when(student.getId()).thenReturn(1l);
        when(student.getGpa()).thenReturn(4.00);
        when(student.getName()).thenReturn("Chertpong");
        when(student.getStudentId()).thenReturn("562115014");

        student2 = mock(Student.class);
        when(student.getId()).thenReturn(2l);
        when(student.getGpa()).thenReturn(2.00);
        when(student.getName()).thenReturn("Krit");
        when(student.getStudentId()).thenReturn("562115015");


//        Mock courses
        course = mock(Course.class);
        when(course.getId()).thenReturn(1l);
        when(course.getCourseId()).thenReturn("953555");
        when(course.getCourseName()).thenReturn("Software binary");

        course2 = mock(Course.class);
        when(course2.getId()).thenReturn(2l);
        when(course2.getCourseId()).thenReturn("953666");
        when(course2.getCourseName()).thenReturn("Big data implementation");

        course3 = mock(Course.class);
        when(course3.getId()).thenReturn(3l);
        when(course3.getCourseId()).thenReturn("953777");
        when(course3.getCourseName()).thenReturn("Manipulating large VM");

//        Mock opened courses
        openedCourse = mock(OpenedCourse.class);
        when(openedCourse.getId()).thenReturn(1l);
        when(openedCourse.getCourse()).thenReturn(course);
        when(openedCourse.getAcademicYear()).thenReturn(2558);
        openedCourse2 = mock(OpenedCourse.class);
        when(openedCourse2.getId()).thenReturn(2l);
        when(openedCourse2.getCourse()).thenReturn(course2);
        when(openedCourse2.getAcademicYear()).thenReturn(2558);
//        Mock course enrolments
//          Mock course enrolments for student
        courseEnrolment = mock(CourseEnrolment.class);
        when(courseEnrolment.getId()).thenReturn(1l);
        when(courseEnrolment.getOpenedCourse()).thenReturn(openedCourse);
        when(courseEnrolment.getStudent()).thenReturn(student);
        when(courseEnrolment.getGrade()).thenReturn("A");

        courseEnrolment2 = mock(CourseEnrolment.class);
        when(courseEnrolment2.getId()).thenReturn(2l);
        when(courseEnrolment2.getOpenedCourse()).thenReturn(openedCourse2);
        when(courseEnrolment2.getStudent()).thenReturn(student);
        when(courseEnrolment2.getGrade()).thenReturn("A");

        when(student.getCourseEnrolments()).thenReturn(Arrays.asList(courseEnrolment,courseEnrolment2));
//          Mock course enrolment for student2
        courseEnrolment3 = mock(CourseEnrolment.class);
        when(courseEnrolment3.getId()).thenReturn(3l);
        when(courseEnrolment3.getOpenedCourse()).thenReturn(openedCourse);
        when(courseEnrolment3.getStudent()).thenReturn(student2);
        when(courseEnrolment3.getGrade()).thenReturn("A");

        courseEnrolment4 = mock(CourseEnrolment.class);
        when(courseEnrolment4.getId()).thenReturn(4l);
        when(courseEnrolment4.getOpenedCourse()).thenReturn(openedCourse2);
        when(courseEnrolment4.getStudent()).thenReturn(student2);
        when(courseEnrolment4.getGrade()).thenReturn("F");

        when(student2.getCourseEnrolments()).thenReturn(Arrays.asList(courseEnrolment3,courseEnrolment4));

//        Mock dao
//          Mock courseDao
        courseDao = mock(CourseDao.class);
        when(courseDao.getCourse()).thenReturn(Arrays.asList(course,course2,course3));
//          Mock courseEnrolmentDao
        courseEnrolmentDao = mock(CourseEnrolmentDao.class);
        when(courseEnrolmentDao.getCourseEnrolments()).thenReturn(
                Arrays.asList(courseEnrolment,courseEnrolment2,courseEnrolment3,courseEnrolment4)
        );
//          Mock OpenedCourseDao
        openedCourseDao = mock(OpenedCourseDao.class);
        when(openedCourseDao.getOpenedCourse()).thenReturn(
                Arrays.asList(openedCourse,openedCourse2)
        );
//        set dao to service
        courseService.setCourseDao(courseDao);
        courseService.setCourseEnrolmentDao(courseEnrolmentDao);
        courseService.setOpenedCourseDao(openedCourseDao);
    }

    @Test
    public void testGetCourse() throws Exception {
        assertThat(courseService.getCourse().size(),is(3));
        verify(courseDao,times(1)).getCourse();
        assertThat(courseService.getCourse(),is(Arrays.asList(course,course2,course3)));
    }

    @Test
    public void testGetCourseByPartialKeyword() throws Exception {
        when(courseDao.getCourse("Big data")).thenReturn(Arrays.asList(course2));
        assertThat(courseService.getCourse("Big data"),is(Arrays.asList(course2)));
        verify(courseService,times(1)).getCourse("Big data");
    }

    @Test
    public void testGetCourseByAcademicYear() throws Exception {
        when(openedCourseDao.getOpenedCourse(2558)).thenReturn(Arrays.asList(openedCourse,openedCourse2));
        assertThat(courseService.getCourse(2558),is(Arrays.asList(course,course2)));
        InOrder inOrder = inOrder(openedCourseDao,openedCourse,openedCourse2);
        inOrder.verify(openedCourseDao,times(1)).getOpenedCourse(2558);
        inOrder.verify(openedCourse,times(1)).getCourse();
        inOrder.verify(openedCourse2,times(1)).getCourse();
    }

    @Test
    public void testGetCourseByAcademicYearAndPartialKeyword() throws Exception {
        when(openedCourseDao.getOpenedCourse(2558)).thenReturn(Arrays.asList(openedCourse,openedCourse2));
        assertThat(courseService.getCourse("953555",2558),is(Arrays.asList(course)));
        verify(course,times(1)).getCourseId();
        verify(openedCourse,times(2)).getCourse();
        verify(course2,times(1)).getCourseId();
        verify(course2,times(1)).getCourseName();
        assertThat(courseService.getCourse("binary",2558),is(Arrays.asList(course)));
    }

    @Test
    public void testGetCourseReport() throws Exception {
        CourseReport courseReport = new CourseReport();
        courseReport.setCourse(course);
        courseReport.setStudents(Arrays.asList(student,student2));
        courseReport.setTotalStudent(2.00);
        courseReport.setAverageGpa(4.00);

        when(courseEnrolmentDao.getCourseEnrolments(course,2558)).thenReturn(Arrays.asList(courseEnrolment,courseEnrolment3));
        assertThat(courseService.getCourseReport(course,2558),is(courseReport));


    }
}
