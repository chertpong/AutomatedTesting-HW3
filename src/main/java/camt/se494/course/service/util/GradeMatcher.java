package camt.se494.course.service.util;

import camt.se494.course.exception.UnAcceptGradeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dto on 10/2/2015.
 */
public class GradeMatcher {
    Map<String,Double> gradeMap;
    public GradeMatcher(){
        gradeMap = new HashMap<String,Double>();
        gradeMap.put("A",4.0);
        gradeMap.put("B+",3.5);
        gradeMap.put("B",3.0);
        gradeMap.put("C+",2.5);
        gradeMap.put("C",2.0);
        gradeMap.put("D+",1.5);
        gradeMap.put("D",1.0);
        gradeMap.put("F",0.0);
    }

    public double getGradeScore(String grade){
        Double value = gradeMap.get(grade);
        if (value != null){
            return value.doubleValue();
        }else
        {
            throw new UnAcceptGradeException();
        }
    }
}
