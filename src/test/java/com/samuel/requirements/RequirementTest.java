package com.samuel.requirements;

import com.samuel.builders.IntervalBuilder;
import com.samuel.models.Interval;
import com.samuel.models.Knot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequirementTest {
    private Interval interval_10_100;
    private Interval interval_20_30;
    private Interval interval_10_19;
    private Interval interval_31_100;
    private Interval interval_50_5000;
    private Interval interval_10_5000;
    private Interval interval_200_300;
    private Interval interval_95_205;
    private Interval interval_10_94;
    private Interval interval_206_300;

    private List<Interval> included;
    private List<Interval> excluded;
    private List<Interval> result;

    private IntervalBuilder intervalBuilder = new IntervalBuilder();

    @Before
    public void init(){
        interval_10_100 = new Interval(10,100);
        interval_20_30 = new Interval(20,30);
        interval_10_19 = new Interval(10,19);
        interval_31_100 = new Interval(31,100);
        interval_50_5000 = new Interval(50,5000);
        interval_10_5000 = new Interval(10,5000);
        interval_200_300 = new Interval(200,300);
        interval_95_205 = new Interval(95,205);
        interval_10_94 = new Interval(10,94);
        interval_206_300 = new Interval(206,300);
        included = new ArrayList<>();
        excluded = new ArrayList<>();
        result = new ArrayList<>();
    }

    @Test
    public void ex1() {
        included.add(interval_10_100);
        excluded.add(interval_20_30);
        result.add(interval_10_19);
        result.add(interval_31_100);

        List<Knot> knots = intervalBuilder.createKnottedRope(included,excluded);
        assertEquals(result,intervalBuilder.resolve(knots));
    }

    @Test
    public void ex2() {

    }

    @Test
    public void ex3() {

    }

    @Test
    public void ex4() {

    }

}