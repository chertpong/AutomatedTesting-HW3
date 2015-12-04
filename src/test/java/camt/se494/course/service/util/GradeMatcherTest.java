package camt.se494.course.service.util;

import camt.se494.course.exception.UnAcceptGradeException;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.number.IsCloseTo.*;

/**
 * Created by Chertpong on 4/12/2558.
 */
public class GradeMatcherTest {
    @Test
    public void testGetGradeScore() throws Exception {
        GradeMatcher gradeMatcher = spy(GradeMatcher.class);
        assertThat(gradeMatcher.getGradeScore("A"),is(4.0));
        assertThat(gradeMatcher.getGradeScore("B+"),is(3.5));
        assertThat(gradeMatcher.getGradeScore("B"),is(3.0));
        assertThat(gradeMatcher.getGradeScore("C+"),is(2.5));
        assertThat(gradeMatcher.getGradeScore("C"),is(2.0));
        assertThat(gradeMatcher.getGradeScore("D+"),is(1.5));
        assertThat(gradeMatcher.getGradeScore("D"),is(1.0));
        assertThat(gradeMatcher.getGradeScore("F"),is(0.0));
    }

    @Test(expected = UnAcceptGradeException.class)
    public void testGetGradeScoreGivenThatGradeIsInvalidShouldReturnException() throws Exception {
        GradeMatcher gradeMatcher = spy(GradeMatcher.class);
        assertThat(gradeMatcher.getGradeScore("Z"),is(0.0));
    }

}
