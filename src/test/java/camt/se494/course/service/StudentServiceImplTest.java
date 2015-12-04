package camt.se494.course.service;

import camt.se494.course.dao.CourseDao;
import camt.se494.course.dao.CourseEnrolmentDao;
import camt.se494.course.dao.OpenedCourseDao;
import camt.se494.course.dao.StudentDao;
import camt.se494.course.entity.*;
import camt.se494.course.service.util.GradeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.number.IsCloseTo.*;
/**
 * Created by Dto on 10/2/2015.
 */
public class StudentServiceImplTest {
    StudentServiceImpl studentService;
    CourseDao courseDao;
    CourseEnrolmentDao courseEnrolmentDao;
    GradeMatcher gradeMatcher;
    StudentDao studentDao;
    //    test data
    Student student,student2;
    Course course,course2,course3;
    OpenedCourse openedCourse,openedCourse2;
    CourseEnrolment courseEnrolment,courseEnrolment2,courseEnrolment3,courseEnrolment4;

    @Before
    public void setUp() throws Exception {
        studentService = spy(StudentServiceImpl.class);
        gradeMatcher = mock(GradeMatcher.class);
//        Set gradeMatcher
        when(gradeMatcher.getGradeScore("A")).thenReturn(4.0);
        when(gradeMatcher.getGradeScore("B+")).thenReturn(3.5);
        when(gradeMatcher.getGradeScore("B")).thenReturn(3.0);
        when(gradeMatcher.getGradeScore("C+")).thenReturn(2.5);
        when(gradeMatcher.getGradeScore("C")).thenReturn(2.0);
        when(gradeMatcher.getGradeScore("D+")).thenReturn(1.5);
        when(gradeMatcher.getGradeScore("D")).thenReturn(1.0);
        when(gradeMatcher.getGradeScore("F")).thenReturn(0.0);
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
//          Mock courseEnrolmentDao
        courseEnrolmentDao = mock(CourseEnrolmentDao.class);
        when(courseEnrolmentDao.getCourseEnrolments()).thenReturn(
                Arrays.asList(courseEnrolment,courseEnrolment2,courseEnrolment3,courseEnrolment4)
        );
//         Mock studentDao
        studentDao = mock(StudentDao.class);
        when(studentDao.getStudent()).thenReturn(Arrays.asList(student,student2));
//        set dao to service
        studentService.setCourseEnrolmentDao(courseEnrolmentDao);
        studentService.setGradeMatcher(gradeMatcher);
        studentService.setStudentDao(studentDao);
    }

    @Test
    public void testGetStudent() throws Exception {
        assertThat(studentService.getStudent(),is(Arrays.asList(student,student2)));
        verify(studentDao,times(1)).getStudent();
    }

    @Test
    public void testGetStudentByPartialKeyword() throws Exception {
        when(studentService.getStudent("Krit")).thenReturn(Arrays.asList(student2));
        assertThat(studentService.getStudent("Krit"),is(Arrays.asList(student2)));
        verify(studentDao,times(1)).getStudent("Krit");
    }

    @Test
    public void testGetStudentGradeLowerThan() throws Exception {
        assertThat(studentService.getStudentGradeLowerThan(2.5),is(Arrays.asList(student2)));
    }

    @Test
    public void testGetStudentGradeGreaterThan() throws Exception {
        assertThat(studentService.getStudentGradeGreaterThan(3.0),is(Arrays.asList(student)));
    }

    @Test(expected = RuntimeException.class)
    public void testGetStudentGpaGivenNullGradeMatcher() {
        studentService.setGradeMatcher(null);
        assertThat(studentService.getStudentGpa(student),is(closeTo(4.00,0.001)));
    }

    @Test
    public void testGetStudentReport(){
        List<CourseEnrolment> sortedCourseEnrolment = Arrays.asList(courseEnrolment,courseEnrolment2);
        Collections.sort(sortedCourseEnrolment);
        StudentReport studentReport = new StudentReport();
        studentReport.setStudent(student);
        studentReport.getEnrolmentMap().put(2558,sortedCourseEnrolment);
        studentReport.getGpaMap().put(2558,4.0);

//        when(studentService.getRegisterYear(sortedCourseEnrolment)).thenReturn(Arrays.asList(2557,2558));

        assertThat(studentService.getStudentReport(student),is(studentReport));
    }

    @Test
    public void testGetStudentGpa(){
        Student testStudent = mock(Student.class);
        List<CourseEnrolment> enrolmentsList = new ArrayList<>();
        CourseEnrolment courseEnrolment0 = mock(CourseEnrolment.class);
        when(courseEnrolment0.getGrade()).thenReturn("A");
        enrolmentsList.add(courseEnrolment0);
        CourseEnrolment courseEnrolment1 = mock(CourseEnrolment.class);
        when(courseEnrolment1.getGrade()).thenReturn("B");
        enrolmentsList.add(courseEnrolment1);
        CourseEnrolment courseEnrolment2 = mock(CourseEnrolment.class);
        when(courseEnrolment2.getGrade()).thenReturn("C");
        enrolmentsList.add(courseEnrolment2);
        when(testStudent.getCourseEnrolments()).thenReturn(enrolmentsList);

        GradeMatcher gradeMatcher = mock(GradeMatcher.class);
        when(gradeMatcher.getGradeScore("A")).thenReturn(4.00);
        when(gradeMatcher.getGradeScore("B")).thenReturn(3.00);
        when(gradeMatcher.getGradeScore("C")).thenReturn(2.00);

        // run the test
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setGradeMatcher(gradeMatcher);
        assertThat(studentService.getStudentGpa(testStudent),is(closeTo(3.00,0.001)));

        // verify that the grade matcher has been called
        verify(courseEnrolment0).getGrade();
        verify(courseEnrolment1).getGrade();

        // has it receive what should be?
        verify(gradeMatcher).getGradeScore("A");
        verify(gradeMatcher).getGradeScore("B");


        // verify how many times it has been called
        verify(courseEnrolment0,times(1)).getGrade();
        verify(gradeMatcher,times(3)).getGradeScore(anyString());
        verify(gradeMatcher,atLeast(2)).getGradeScore(anyString());

        //Check the order
        // set up the inorder verification
        InOrder inOrder = inOrder(courseEnrolment0,courseEnrolment1,courseEnrolment2,gradeMatcher);
        inOrder.verify(courseEnrolment0).getGrade();
        inOrder.verify(gradeMatcher).getGradeScore("A");
        inOrder.verify(courseEnrolment1).getGrade();
        inOrder.verify(gradeMatcher).getGradeScore("B");
    }

    @Test
    public void testGetStudentGpaByStudentAndAcademicYear() throws Exception {
        assertThat(studentService.getStudentGpa(student,2558),is(4.0));
        assertThat(studentService.getStudentGpa(student2,2558),is(2.0));

        Student testStudent = mock(Student.class);
        when(testStudent.getCourseEnrolments()).thenReturn(Arrays.asList());
        assertThat(studentService.getStudentGpa(testStudent,2558),is(0.0));
    }
}
